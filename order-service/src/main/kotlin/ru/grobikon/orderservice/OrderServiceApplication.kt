package ru.grobikon.orderservice

import feign.RequestInterceptor
import org.springframework.beans.factory.BeanFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService
import org.springframework.context.annotation.Bean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
class OrderServiceApplication(
    val beanFactory: BeanFactory
){

    /**
     * Перехватываем запросы
     */
    @Bean
    fun requestTokenBearerInterceptor(): RequestInterceptor {
        return RequestInterceptor{ requestTemplate ->
            val token = SecurityContextHolder.getContext().authentication as JwtAuthenticationToken
            //проверяем токен
            requestTemplate.header("Authorization", "Bearer " + token.token.tokenValue)
        }
    }

    /**
     * Для отслеживания трассировки заказов
     */
    @Bean
    fun traceableExecutorService(): ExecutorService {
        val executorService = Executors.newCachedThreadPool()
        return TraceableExecutorService(beanFactory, executorService)
    }
}

fun main(args: Array<String>) {
    runApplication<OrderServiceApplication>(*args)
}
