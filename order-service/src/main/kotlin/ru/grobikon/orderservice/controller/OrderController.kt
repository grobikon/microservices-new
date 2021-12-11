package ru.grobikon.orderservice.controller

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grobikon.orderservice.client.InventoryClient
import ru.grobikon.orderservice.dto.OrderDto
import ru.grobikon.orderservice.model.Order
import ru.grobikon.orderservice.repository.OrderRepository
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.function.Supplier
import java.util.logging.Logger


@RestController
@RequestMapping("/api/order")
class OrderController(
    val orderRepository: OrderRepository,
    val inventoryClient: InventoryClient,
    val circuitBreakerFactory: Resilience4JCircuitBreakerFactory,
    val streamBridge: StreamBridge,
    val traceableExecutorService: ExecutorService        //для отслеживания трассировки запросов
) {
    companion object {
        val LOG = Logger.getLogger(OrderController::class.java.name)
    }

    @PostMapping
    fun placeOrder(@RequestBody orderDto: OrderDto): String {
        //Проверяем все товары в наличии
        try {
            circuitBreakerFactory.configureExecutorService(traceableExecutorService)    //передаем имя отслеживаемой службы исполнитель
            val circuitBreaker: Resilience4JCircuitBreaker = circuitBreakerFactory.create("inventory")
            if (orderDto.orderLineItemsList.isNullOrEmpty()) return "Заказ не удался, попробуйте ещё раз."

            val booleanSupplier = Supplier<Boolean> {
                orderDto.orderLineItemsList!!.stream()
                    .allMatch { lineItem ->
                        LOG.info("Выполнение вызова Службы инвентаризации для SkuCode ${lineItem.skuCode}")
                        inventoryClient.checkStock(lineItem.skuCode!!)
                    }
            }
            val productsInStock = circuitBreaker.run(booleanSupplier) { handleErrorCase() }

            if (productsInStock) {
                //Создаём новый заказ
                val order = Order()
                order.orderListItems = orderDto.orderLineItemsList
                order.orderNumber = UUID.randomUUID().toString()

                orderRepository.save(order)
                println("Отправка деталей заказа в службу уведомлений ${order.id!!}")
                streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(order.id!!).build())

                return "Заказ успешно отправлен"
            }

            return "Заказ не удался, попробуйте ещё раз."
        }catch (e: Exception) {
            println(e.message.toString())
            return "Заказ не удался, попробуйте ещё раз."
        }
    }

    private fun handleErrorCase(): Boolean {
        return false
    }

/*    @PostMapping
    fun placeOrderLast(@RequestBody orderDto: OrderDto): String {
        //Проверяем все товары в наличии
        try {
            val allProductsInStock = orderDto.orderLineItemsList?.stream()
                ?.allMatch { orderLineItems ->
                    if (orderLineItems.skuCode != null) inventoryClient.checkStock(orderLineItems.skuCode!!) else false
                }?:false

            if (allProductsInStock) {
                //Создаём новый заказ
                val order = Order()
                order.orderListItems = orderDto.orderLineItemsList
                order.orderNumber = UUID.randomUUID().toString()
                orderRepository.save(order)

                return "Заказ успешно отправлен"
            }

            return "Заказ не удался, попробуйте ещё раз."
        }catch (e: Exception) {
            println(e.message.toString())
            return "Заказ не удался, попробуйте ещё раз."
        }
    }*/
}