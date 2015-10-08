/*
Пример на утках:
1.  Определяем постоянное поведение: swim(), display()
    Определяем поведение, которое м.б. уникальным для объекта: fly(), quack()
    Наследование не решает проблем т.к. не всем уткам требуется поведение fly() и quack(), а реализация классом Duck интерфейса Flyable или Quackable исключает возможность повторного использования кода.
2. Инкапсулируем изменяемые аспекты, используя принцип:
        программируйте на уровне интерфейса. Т.е. создаём интерфейсы: FlyBahavoir c методом fly() и QuackBehavoir() c методом quack(). Создаём конкретные реализации данных интерфейсов
3. Композиция:
        объявляются переменные с интерфейсным типом, которые инициализируются на этапе выполнения конкретными полиморфными значениями,
        соответствующие конкретному типу поведения. Т.е. каждаю утка содержит экземпляры FlyBahavoir и QuackBehavoir. Такой поход и есть композиция.
4. Делегируем выполнение изменяемых поведений:
        Определяются методы  performFly(), performQuack() в которых вызываются методы fly() или quack() через интерфейсные переменные п.3,
        т.е. здесь происходит само делегирование выполнения операции. Метод конкретного объекта, который будет вызван, будет зависеть от реальных типов объектов,
        которыми будут инициализированы переменные интерфейсного типа п.3 в set-методах
 */

package org.billing.jlab.strategy;