package ru.grobikon.inventoryservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grobikon.inventoryservice.repository.InventoryRepository
import java.lang.RuntimeException

@RestController
@RequestMapping("/api/inventory")
class InventoryRestController(
    val inventoryRepository: InventoryRepository
) {

    @GetMapping("/{skuCode}")
    fun isInStock(@PathVariable skuCode: String): Boolean {
        val inventory = inventoryRepository.findBySkuCode(skuCode)
            .orElseThrow{ RuntimeException("Не можем найти продукт с кодом: $skuCode") }
        return inventory.stock > 0
    }
}