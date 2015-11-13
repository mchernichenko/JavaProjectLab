package org.billing.jlab.starbuzz;

public class StarbuzzCoffee {

    public static void main(String args[]) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        beverage = new Whip(new Mocha(new Mocha(new DarkRoast())));
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        beverage = new Whip(new Mocha(new Soy(new HouseBlend())));
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
    }
}
