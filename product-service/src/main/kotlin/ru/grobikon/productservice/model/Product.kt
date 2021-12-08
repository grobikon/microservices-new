package ru.grobikon.productservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "product")
data class Product(
    @Id
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var price: String? = null
)
