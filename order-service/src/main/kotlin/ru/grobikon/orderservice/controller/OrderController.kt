package ru.grobikon.orderservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grobikon.orderservice.client.InventoryClient
import ru.grobikon.orderservice.dto.OrderDto
import ru.grobikon.orderservice.model.Order
import ru.grobikon.orderservice.repository.OrderRepository
import java.util.*

@RestController
@RequestMapping("/api/order")
class OrderController(
    val orderRepository: OrderRepository,
    val inventoryClient: InventoryClient
) {

    @PostMapping
    fun placeOrder(@RequestBody orderDto: OrderDto): String {
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
    }
}