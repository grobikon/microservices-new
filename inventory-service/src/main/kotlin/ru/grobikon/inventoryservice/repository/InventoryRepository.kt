package ru.grobikon.inventoryservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.grobikon.inventoryservice.model.Inventory
import java.util.*

interface InventoryRepository: JpaRepository<Inventory, Long> {

    /**
     * Optional Контейнерный объект, который может содержать или не содержать ненулевое значение. Если значение присутствует, isPresent () возвращает true. Если значение отсутствует, объект считается пустым, а isPresent () возвращает false.
     * Предоставляются дополнительные методы, которые зависят от наличия или отсутствия содержащегося значения, например orElse () (возвращает значение по умолчанию, если значение отсутствует) и ifPresent () (выполняет действие, если значение присутствует).
     */
    fun findBySkuCode(skuCode: String): Optional<Inventory>
}