package ru.grobikon.orderservice.dto

import ru.grobikon.orderservice.model.OrderLineItems

data class OrderDto(
    var orderLineItemsList: List<OrderLineItems>? = null        //позиции заказа
)
