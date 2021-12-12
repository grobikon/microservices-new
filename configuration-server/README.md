# Хранение секретов в Hashicorp Vault
Vault - это система управления секретами, позволяющая хранить конфиденциальные данные в зашифрованном виде. Он идеально подходит для хранения конфиденциальных данных конфигурации, таких как пароли, ключи шифрования, ключи API.

Vault - это инструмент от Hashicorp, который используется для хранения секретов 
нашего приложения и доступа к ним. Вы можете загрузить программное обеспечение 
на свой компьютер со страницы «Загрузки» - https://www.vaultproject.io/downloads

После установки Vault вы можете запустить сервер хранилища в режиме разработки, используя следующую команду: 
###vault server -dev

потом устанавливаем токен в property микросервисов Root Token: пример: s.2XUOc0dHu0NNUx2YAzILUz8H
или
###vault server --dev --dev-root-token-id = "00000000-0000-0000-0000-000000000000"

И теперь в консоли вы увидите команду для установки Vault Address в качестве переменной среды, 
вы можете выполнить команду в зависимости от вашей операционной системы, для Windows 
отображаются следующие команды:
### PowerShell:
   * $env:VAULT_ADDR="http://127.0.0.1:8200"
###  cmd.exe:
   * set VAULT_ADDR=http://127.0.0.1:8200


order-service-credentials.json

{
"spring.datasource.username": "root",
"spring.datasource.password": "mysql"
}

###Теперь нам нужно записать эти json-файлы в Vault Store, используя следующие команды:

* vault kv put secret/order-service @order-service.json
* vault kv put secret/product-service @product-service.json
* vault kv put secret/inventory-service @inventory-service.json

###Чтобы удалить данные из Vault, нужно вызвать команду:

* vault kv delete secret/order-service
* vault kv delete secret/product-service
* vault kv delete secret/inventory-service

###Чтобы посмотреть данные из Vault, нужно вызвать команду:

* vault kv get secret/order-service
* vault kv get secret/product-service
* vault kv get secret/inventory-service