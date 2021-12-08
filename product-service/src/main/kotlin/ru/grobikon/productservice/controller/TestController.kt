package ru.grobikon.productservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
@RefreshScope
class TestController {

    @Value("\${test.name}")
    lateinit var name: String

    @GetMapping
    fun test(): String {
        return name
    }
}