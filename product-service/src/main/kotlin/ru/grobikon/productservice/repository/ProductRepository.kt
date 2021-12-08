package ru.grobikon.productservice.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.grobikon.productservice.model.Product

interface ProductRepository: MongoRepository<Product, String> {
}