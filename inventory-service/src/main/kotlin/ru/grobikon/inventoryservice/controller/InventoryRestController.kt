package ru.grobikon.inventoryservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grobikon.inventoryservice.model.Inventory
import ru.grobikon.inventoryservice.repository.InventoryRepository


@RestController
@RequestMapping("/api/inventory")
class InventoryRestController(
    val inventoryRepository: InventoryRepository
) {

    @GetMapping("/{skuCode}")
    fun isInStock(@PathVariable skuCode: String): Boolean {
        println("Проверка наличия товара на складе с помощью skucode: $skuCode")
        val inventory: Inventory = inventoryRepository.findBySkuCode(skuCode)
            .orElseThrow{ RuntimeException("Не можем найти продукт с кодом: $skuCode") }
        return inventory.stock != null && inventory.stock!! > 0
    }
}