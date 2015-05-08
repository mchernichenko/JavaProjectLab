/**
 * 1. создать объект указанный в replace, реализующий интерфейс MethodReplace
 * 2. Spring синтезирует создание прокси-объекта, в него передаётся бин replace, переопределяющий метод formatMessage
 * 3. При вызове метода formatMessage вызывается метод reimplement
 */
package org.billing.jlab.spring.ch4.mi;