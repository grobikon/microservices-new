package ru.grobikon.productservice.service

import ru.grobikon.productservice.model.Product

interface ProductService {
    fun getAllProducts(): List<Product>
    fun createProduct(product: Product)
}