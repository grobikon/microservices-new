package ru.grobikon.orderservice.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder

import javax.annotation.PostConstruct




@Configuration
@EnableWebSecurity
class SecurityConfig {

    @PostConstruct
    fun enableAuthenticationContextOnSpawnedThreads() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
    }
}