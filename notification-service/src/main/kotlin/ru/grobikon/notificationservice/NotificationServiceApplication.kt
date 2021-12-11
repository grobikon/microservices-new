package ru.grobikon.notificationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import java.util.function.Consumer

@SpringBootApplication
@EnableEurekaClient
class NotificationServiceApplication{

    /**
     * Может быть Function(Служба), Supplier(Поставщик), Consumer(Потребитель)
     *
     * Consumer Представляет операцию, которая принимает один входной аргумент и не возвращает результата.
     * Ожидается, что в отличие от большинства других функциональных интерфейсов, Consumer будет работать с побочными эффектами.
     */
    @Bean
    fun notificationEventSupplier(): Consumer<String> {
        return Consumer<String> { message -> EmailSender().sendEmail(message) }
    }

/*    @Bean
    fun notificationEventSupplier2(): Consumer<Message<String>> {
        return Consumer<Message<String>> { message: Message<String> ->
            EmailSender().sendEmail(
                message.payload
            )
        }
    }*/

}

fun main(args: Array<String>) {
    runApplication<NotificationServiceApplication>(*args)
}
