package ru.grobikon.apigatewayservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    /**
     *
     * "SecurityWebFilterChain" Определяет цепочку фильтров, которую можно сопоставить с ServerWebExchange,
     *                          чтобы решить, применяется ли она к этому запросу.
     *
     * "ServerWebExchange" Контракт на взаимодействие HTTP-запрос-ответ. Предоставляет доступ к HTTP-запросу
     *                     и ответу, а также предоставляет дополнительные свойства и функции,
     *                     связанные с обработкой на стороне сервера, такие как атрибуты запроса.
     *
     * @param http ServerHttpSecurity похож на HttpSecurity Spring Security, но для WebFlux.
     *             Он позволяет настроить безопасность в Интернете для определенных HTTP-запросов.
     *             По умолчанию он будет применяться ко всем запросам, но его можно ограничить
     *             с помощью securityMatcher (ServerWebExchangeMatcher) или других подобных методов.
     */
    @Bean
    fun springSecurityWebFilterChin(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.authorizeExchange{ exchange -> exchange.anyExchange().authenticated() }
            .oauth2Login(Customizer.withDefaults())
        http.csrf().disable()                       //Подделка межсайтовых запросов отключаем
        return http.build()
    }
}