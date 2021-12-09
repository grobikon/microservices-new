package ru.grobikon.orderservice.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "order_line_items")
data class OrderLineItems(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var skuCode: String? = null,
    var price: BigDecimal? = null,
    var quantity: Int? = null

)
