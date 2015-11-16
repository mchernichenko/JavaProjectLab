package org.billing.jlab.starbuzz;

/**
 * Готовим кофе с добавками и рассчитываем итьоговую стоимость
 */

public class StarbuzzCoffee {

    public static void main(String args[]) {

        // Обычный эспрессо без добавок , Price:
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        // Готовим DarkRoast с двойной шаколадной добавкой и сливками
        beverage = new Whip(new Mocha(new Mocha(new DarkRoast())));
        System.out.println(beverage.getDescription() + ", Price: $" + beverage.cost());

        beverage = new Whip(new Mocha(new Soy(new HouseBlend())));
        System.out.println(beverage.getDescription() + ", Price: $" + beverage.cost());
    }
}
