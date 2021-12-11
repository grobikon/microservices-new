package ru.grobikon.inventoryservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grobikon.inventoryservice.model.Inventory
import ru.grobikon.inventoryservice.repository.InventoryRepository
import java.util.logging.Logger


@RestController
@RequestMapping("/api/inventory")
class InventoryRestController(
    val inventoryRepository: InventoryRepository
) {

    companion object {
        val LOG = Logger.getLogger(InventoryRestController::class.java.name)
    }

    @GetMapping("/{skuCode}")
    fun isInStock(@PathVariable skuCode: String): Boolean {
        LOG.info("Проверка наличия товара на складе с помощью skucode: $skuCode")
        val inventory: Inventory = inventoryRepository.findBySkuCode(skuCode)
            .orElseThrow{ RuntimeException("Не можем найти продукт с кодом: $skuCode") }
        return inventory.stock != null && inventory.stock!! > 0
    }
}