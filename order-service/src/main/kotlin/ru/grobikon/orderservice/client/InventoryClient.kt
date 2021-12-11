package ru.grobikon.orderservice.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * Будем отправлять запрос на склад для проверки наличия товара
 * @FeignClient(name = "inventory-service") name - равен значению из bootstrap.yml.txt
 */
@FeignClient(name = "inventory-service")
interface InventoryClient {

    @GetMapping("/api/inventory/{skuCode}")
    fun checkStock(@PathVariable skuCode: String): Boolean
}