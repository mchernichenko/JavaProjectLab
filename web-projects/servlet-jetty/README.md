
* [По мотивам видоса](https://www.youtube.com/watch?v=Jnd4PQt44j0&list=PLU2ftbIeotGoQGD51e0qb98lE0xhgNDF1&ab_channel=letsCode)

Приложение для отладки запускается через таску `jetty:run` подключённого плагина `jetty-maven-plugin`
где 
* `localhost:8080` стандартный host:port на котором стартуем jetty
* `/my-app` - контекст приложения, к котором зареган сервлет и указывается настройке плагина
* `/my-servlet` - зареганный сервлет в web.xml

При запуске `jetty:run` сервлет доступен по URL http://localhost:8080/my-app/my-servlet