package ru.grobikon.inventoryservice.model

import javax.persistence.*

@Entity
@Table(name = "inventory")
data class Inventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var skuCode: String? = null,
    var stock: Int? = null
)
