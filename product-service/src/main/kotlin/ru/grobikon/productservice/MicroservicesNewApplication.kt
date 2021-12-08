package ru.grobikon.productservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class MicroservicesNewApplication

fun main(args: Array<String>) {
    runApplication<MicroservicesNewApplication>(*args)
}
