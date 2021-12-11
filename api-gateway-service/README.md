# Шлюз Spring Cloud Gateway
Эта библиотека помогает нам реализовать шаблон шлюза API, скрывая сложность наших микросервисов от внешних клиентов.

Если клиент хочет подключиться к одной из наших служб, весь трафик будет проходить через шлюз API, и шлюз направит запрос в соответствующую службу.

Эта библиотека также решает общие проблемы, такие как безопасность, мониторинг, ограничение скорости и отказоустойчивость. Мы будем защищать наши микросервисы, используя Keycloak в качестве сервера авторизации вместе со Spring Cloud Gateway.

### Spring Cloud Netflix: Ribbon по-русски
Ribbon — это балансировщик нагрузки.
https://medium.com/@kirill.sereda/spring-cloud-netflix-ribbon-%D0%BF%D0%BE-%D1%80%D1%83%D1%81%D1%81%D0%BA%D0%B8-2ded121e1377


# Keycloak
### C:\Users\grobi\Downloads\keycloak-15.0.2\keycloak-15.0.2\bin
###standalone.bat -Djboss.http.port=8180

##Чтобы проверить запросы в Postman
Нужно вызвать http://localhost:8080/
в ApiGatewayServiceApplication class прописан метод:
     @GetMapping("/")
     fun home(webSession: WebSession): Mono<String> {
        return Mono.just(webSession.id)
     }

и получить SESSION пример 82e9094b-2fda-499b-803b-7dce0211d300
и прописать его в Cookie SESSION=36abb603-cbf0-4f9f-ab0c-fa237010b1f0
