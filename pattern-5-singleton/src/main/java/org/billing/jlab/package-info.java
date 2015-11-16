/**
 * Варианты реализации паттерна Одиночка -  гарантирующего, что класс имеет только один экземпляр:
 * 1. Условная блокировка
 * 2. статическая переменная (Это потоко-небезопасный код создания одиночного объекта, т.е. плохой вариант)
 * 3. синхронизация
 * 4. раннее создание экземпляра
 *
 *  dcl - реализация паттерна с использованием условной блокировки, т.е. конструкции volatile - минимизирующей количество синхронизаций.
 *  classic (BAD) - классическая реализация паттерна Одиночка, с использованием статической переменной для хранения единственного экземпляра. Это потоко-небезопасный код создания одиночного объекта
 *  threadsafe - реализация паттерна с помощью синхронизации метода getInstance
 *  stat - реализация паттерна методом раннего создания экземпляра, с использованием статического блока (потоковая безовасность здесь гарантирована, т.к. экзампляр будет создан до того, когда
 *         какой-либо поток сможет обратиться к статической переменной)
 *
 *  chocolate -  просто пример классической реализация паттерна (неправильно будет в многопоточной среде)
 */

package org.billing.jlab;