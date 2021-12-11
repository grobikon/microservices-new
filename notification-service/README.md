##Чтобы включить панель управления RabbitMQ, нужно выполнить следующую команду

* rabbitmq-plugins enable rabbitmq_management

После выполнения команды переходим в браузер localhost:15672

###логин и пароль по умолчанию = guest

##ZIPKIN
###Установка
curl -sSL https://zipkin.io/quickstart.sh | bash -s

java -jar zipkin.jar

localhost:9411
