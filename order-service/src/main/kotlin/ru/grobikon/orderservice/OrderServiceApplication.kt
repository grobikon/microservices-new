package ru.grobikon.orderservice

import feign.RequestInterceptor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
class OrderServiceApplication{

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
}

fun main(args: Array<String>) {
    runApplication<OrderServiceApplication>(*args)
}
