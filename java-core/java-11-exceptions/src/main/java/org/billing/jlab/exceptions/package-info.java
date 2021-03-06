/**
  Рассматриваются:
    - обработка исключений (механизм перехвата ошибок)
    - применение средств диагностических утверждений (удобны на время тестирования)
    - средства протоколирования (для регистрации ошибки для последующего анализа)

 Все исключения являются наследниками класса Throwable (см. картинку Иерархии Наследования).
 Иерархия класса Error описывает внутренние ошибки (нехватка ресурсов в Java, внутренняя ошибка jvm и пр.). Объекты данного класса сгенерировать
 самостоятельно нельзя!!! Это так называемые необрабатываемые исключения.
 Вторая ветка это Exception - исключения (см. ExceptionHandling.java)

 */
package org.billing.jlab.exceptions;