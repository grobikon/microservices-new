package ru.grobikon.apigatewayservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.WebSession
import reactor.core.publisher.Mono


@SpringBootApplication
@EnableEurekaClient
@RestController
class ApiGatewayServiceApplication{

    @GetMapping("/")
    fun home(webSession: WebSession): Mono<String> {
        return Mono.just(webSession.id)
    }
}

fun main(args: Array<String>) {
    runApplication<ApiGatewayServiceApplication>(*args)
}
