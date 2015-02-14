/**
 * НАСЛЕДОВАНИЕ.
 * Основные моменты:
 * - Доступ.
 *      1. private - область действия ограничивается классом, т.е. подкласс не может обращаться к private полям суперкласса. Только методы самого класса имеют доступ к private полям/методам.
 *      2. protected - область действия ограничивается пакетом и всеми подклассами, т.е. применяется когда требуется доступ к полям/методам непосредственно из подклассов.
 *      Например: Если B extends A, то дополнительно в экземпляре В доступны protected-члены родительского класса этого же экземпляра (а не любого экземпляра родительского класса!).
 *      3. не указан - область действия ограничивается только пакетом
 *      Прим. При переопределении область действия метода из подкласса д.б. не меньше области действия метода из суперкласса.
 * - super - ключевое слово, не означающее ссылку на объект, т.е. по нему нельзя присвоить значение переменной. Это слово
 *      указывает компилятору, что нужно вызвать метод из суперкласса
 * - конструктор потомка не имеет доступа к закрытым полям суперкласса => необходимо вызывать конструктор суперкласса с помощью supper
 *      вызов super т.е. вызов конструктора в вызывающем конструкторе д.б. первым!
 *      Если явного вызова конструктора нет, то автоматически вызывается super()-без аргументов. При этом, если он не находится, то ошибка.
 * - Полиморфизм - способность переменной ссылаться на объекты, имеющие разные фактические типы.
 * - В массив созданный с помощью new может содержать только объекты с указанным типом или его потомков
 * - Динамическое связывание x.func(param):
 *      1. Составляется список всех func входящех в состав класса x и всех его суперклассов
 *      2. Проведеряются типы param указанных при вызове
 *          Важно: возвращаемый тип не относится к сигнатуре метода, по при переопределении нужно сохранить совместимость возвращаемых типов
 *      3. Если метод private/static/final или конструктор, то JVM знает, что его нужно вызвать их класса x
 *      4. Иначе, используется динамическое сзязывание. JVM вызывает версию метода соответствующую фактическому типу объекта.
 *          если в x определён метод func, то он вызывается, иначе поиск осуществляется в суперклассе и т.д.
 *      Динамическое связывание позволяет изменять программы без перекомпиляции.
 *      При переопределении область действия метода из подкласса д.б. не меньше области действия метода из суперкласса.
 *  - final - предоствращает наследование методов всего класса или отдельного класса.
 *      Если класс финальный, то финальными автоматически становятся его мегоды, но не поля!
 *  - Переопределение. Основные правила:
 *      1. преведение типов возможно только в иерархии наследования
 *      2. для проверки корректномти переопределения следует выполнить операция instanceof
 *  - equals() - равнозначночть объектов. Спецификация Java требует, чтобы метод обладал хар-ми:
 *      1. Рефлексивность. x.equals(x) == true, при x != null
 *      2. Симметричность. x.equals(y) ==  y.equals(x) == true
 *      3. Транзитивность. x.equals(y) == true и y.equals(z) == true, то и x.equals(z) д.б. == true
 *      4. Cогласованность. Повторный вызов x.equals(y) == true, если x,y не изменялись.
 *      5. x.equals(null) == false, при x != null
 *
 *      Для поддержки симметричности есть 2 сценария:
 *      1. если проверка на равнозначность реализовани в подклассе, правило симметрии требует использование getClass,
 *          т.е. объекты должны быть объектами одного класса
 *          При переопределении equals в метод следует включить вызов super.equals(), см. Manager.equals()
 *      2. если проверка производится методе суперкласса и семантика проверки на равнозначность в подклассе не изменится,
 *          то можно выполныть операцию instanceof. В этом случае возможна ситуация, когда
 *          два объекта разных классов, но в одной иерархии наследования, будут призваны разнозначными.
 *          Важно объявить пр этом метод equals в суперклассе как final !!!
 *          иначе можно нарушить правило симметричности, если переопределить метод equals в подклассе и расширить семантику проверки,
 *          например, для Managers добавить проверку на равенство бонусов.
 *  - hashCode - целое число, м.б. отрицательным и генерируемое на основе конкретного объекта. Если у объектов  хэш-код разный, то они разные,
 *      если хэш-код совпадает, то с большей степенью вероятности объекты тоже одинаковые, но гарантий нет!!!
 *      Хеш-код вычисляется по области памяти занимаемой объектом.
 *      Методы hashCode и equals должны быть совместимы. Если объекты равнозначны, то и hashCode этих объектов должен совпадать, т.е.
 *      должен быть переопределён на основе соответствующих полей.
 *
 *  - toString() - возвращает объект c именем getClass().getName() в виде символьнй строки.
 *      Например: Manager{name=..., salary=..., hireDay=...}{bonus=...}
 *    Вместо obj.toString можно использовать "" + obj. Это сцепление равнозначно вызову toString
 *
 * *** ИНТЕРФЕЙСЫ ***
 *    1. Все методы интерфейса автоматически считаются открытыми. public указывать не обязательно,  но при реализации интерфейса модификатор обязателен
 *    2. Интерфейсы не могут реализовываться ввиде объектов, т.к. не имеет ни полей ни тела методов.
 *    3.
 */
package org.billing.jlab.oop;