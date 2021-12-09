package ru.grobikon.orderservice.model

import javax.persistence.*

@Entity
@Table(name = "t_orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var orderNumber: String? = null,
    @OneToMany(cascade = [CascadeType.ALL])
    var orderListItems: List<OrderLineItems>? = null
)
