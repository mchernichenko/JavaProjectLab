package org.billing.jlab.factory.pizzafm;

public abstract class PizzaStoreNew {

    abstract Pizza createPizzaNew(String style, String type);

    /**
     * Метод приготовления пиццы
     * @param type
     * @return
     */
    public Pizza orderPizza(String style, String type) {
        Pizza pizza = createPizzaNew(style, type);
        System.out.println("--- Готовим " + pizza.getName() + " ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
