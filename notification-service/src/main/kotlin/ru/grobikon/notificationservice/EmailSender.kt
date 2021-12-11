package ru.grobikon.notificationservice

import org.springframework.stereotype.Service

@Service
class EmailSender {

    /**
     * Имитируем отправку Email
     */
    fun sendEmail(orderNumber: String) {
        println("Заказ размещен успешно - номер заказа $orderNumber")
        println("Отправить на Email")
    }
}