# Хранение секретов в Hashicorp Vault
Vault - это инструмент от Hashicorp, который используется для хранения секретов 
нашего приложения и доступа к ним. Вы можете загрузить программное обеспечение 
на свой компьютер со страницы «Загрузки» - https://www.vaultproject.io/downloads

После установки Vault вы можете запустить сервер хранилища в режиме разработки, используя следующую команду: 
###vault server -dev

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

Теперь нам нужно записать эти json-файлы в Vault Store, используя следующие команды:

* vault kv put secret/order-service @order-service.json
* vault kv put secret/product-service @product-service.json