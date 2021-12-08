package ru.grobikon.productservice.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.grobikon.productservice.model.Product
import ru.grobikon.productservice.service.ProductService

@RestController
@RequestMapping("/api/product")
class ProductController(
    val productService: ProductService
) {

    /**
     * Список продуктов которые есть в БД
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<Product> {
        return productService.getAllProducts()
    }

    /**
     * Создаём продукт
     * @param product Продукт, который передаем в формате json, сохраняем его в БД
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody product: Product) {
        productService.createProduct(product = product)
    }
}