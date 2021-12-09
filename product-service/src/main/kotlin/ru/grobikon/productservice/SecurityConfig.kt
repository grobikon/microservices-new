package ru.grobikon.productservice

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * WebSecurityConfigurerAdapter Создает экземпляр с включенной конфигурацией по умолчанию.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    /**
     * Все запросы должны быть аутентифицированы с помощью механизма oauth2
     */
    override fun configure(security: HttpSecurity) {
        security.authorizeRequests { authorize -> authorize.anyRequest().authenticated() }
            .oauth2ResourceServer{ oAuth2ResourceServerConfigurer -> oAuth2ResourceServerConfigurer.jwt() }
    }
}