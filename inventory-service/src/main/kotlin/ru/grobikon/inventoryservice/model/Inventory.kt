package ru.grobikon.inventoryservice.model

import javax.persistence.*

@Entity
@Table(name = "inventory")
data class Inventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var skuCode: String,
    var stock: Int
)
