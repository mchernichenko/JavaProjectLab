/**
 * Варианты реализации паттерна Одиночка -  гарантирующего, что класс имеет только один экземпляр:
 * Все варианты основаны на приватном конструкторе и статической переменой.
 * 1. Условная блокировка - самый хороший и универсальный способ, хоть и немного навороченный
 * 2. статическая переменная (Это потоко-небезопасный код создания одиночного объекта, т.е. плохой вариант)
 * 3. синхронизация - хороший вариант, если не критично быстродействие
 * 4. раннее создание экземпляра (non laizy) - хороший вариант, особенно если экземпляр необходим всегда (Ограничание: загрузчик классов должен использоваться один!!!)
 * 5. рекомендуемый Oracle вариант (laizy) c использованием дополнительного статического внутреннего класса, которому доступен private конструктор класса
 *    или доп. класса в этом же пакете, но конструктор придется делать packege lock, т.е. уровень доступа - default
 *    создающего экзампляр класса по требованию.
 *
 *  dcl - реализация паттерна с использованием условной блокировки, т.е. конструкции volatile - минимизирующей количество синхронизаций.
 *  classic (BAD) - классическая реализация паттерна Одиночка, с использованием статической переменной для хранения единственного экземпляра. Это потоко-небезопасный код создания одиночного объекта
 *  threadsafe - реализация паттерна с помощью синхронизации метода getInstance
 *  stat - реализация паттерна методом раннего создания экземпляра, с использованием статического блока (потоковая безопасность здесь гарантирована, т.к. экзампляр будет создан до того, когда
 *         какой-либо поток сможет обратиться к статической переменной). Non Laizy реализация, т.е. объест создастся до его реального возможного использования.
 *  statlaizy - Laizy реализация паттерна рекомендуемая Oracle.
 *
 *  chocolate -  просто пример классической реализация паттерна (неправильно будет в многопоточной среде)
 *
 *  ОТВЕТЫ на не дурацкие вопросы:
 *  1. Проблемы с субклассированием синглетного класса т.к. конструктор приватный => наследовать синглетный класс большого смысла нет
 *  в subclass приведён пример, где производные классы всегда ссылаются на одну переменую.
 *  2. Есть вероятность получить 2 экзампляра объекта при раннем создании экземпляра, если используются 2 и более загрузчиков классов (по одному классу для каждого загрузчика)
 *  3. Можно использовать глобальную переменную
 *
 *  Разобраться с Singletone на базе enum.
 *  Для сертификации: docs.oracle.com/javase/tutorial/extra/certification/javase-7-programmer2.html
 */

package org.billing.jlab.pattern.singleton;