package ru.grobikon.productservice.service

import org.springframework.stereotype.Service
import ru.grobikon.productservice.model.Product
import ru.grobikon.productservice.repository.ProductRepository

@Service
class ProductServiceImp(
    val productRepository: ProductRepository
): ProductService {


    override fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    override fun createProduct(product: Product) {
        productRepository.save(product)
    }
}