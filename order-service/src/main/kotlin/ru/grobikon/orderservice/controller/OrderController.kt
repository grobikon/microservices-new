package ru.grobikon.orderservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grobikon.orderservice.dto.OrderDto
import ru.grobikon.orderservice.model.Order
import ru.grobikon.orderservice.repository.OrderRepository
import java.util.*

@RestController
@RequestMapping("/api/order")
class OrderController(
    val orderRepository: OrderRepository
) {

    @PostMapping
    fun placeOrder(@RequestBody orderDto: OrderDto): String {
        //Создаём новый заказ
        val order = Order()
        order.orderListItems = orderDto.orderLineItemsList
        order.orderNumber = UUID.randomUUID().toString()
        orderRepository.save(order)

        return "Заказ успешно отправлен"
    }
}