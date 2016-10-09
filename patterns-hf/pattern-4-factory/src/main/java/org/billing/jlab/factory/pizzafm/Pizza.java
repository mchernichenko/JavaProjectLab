package org.billing.jlab.factory.pizzafm;

import java.util.ArrayList;

/**
 * Абстрактный класс в котором определяются все атрибуты пиццы и основные действия
 */
public abstract class Pizza {
	String name;  // имя
	String dough; // тесто
	String sauce; // соус
	ArrayList toppings = new ArrayList(); // специи

	/**
	 * Приготовление пиццы состоит из нескольких шагов выполняемых в определённой последовательности
	 */
	void prepare() {
		System.out.println("Приготавление " + name);
		System.out.println("Выпекаем тесто...");
		System.out.println("Добавляем соус...");
		System.out.println("Добавляем специи: ");
		for (int i = 0; i < toppings.size(); i++) {
			System.out.println("   " + toppings.get(i));
		}
	}
  
	void bake() {
		System.out.println("Выпекать 25 минут на 350С");
	}
 
	void cut() {
		System.out.println("Нарезать пиццу на стандартные куски");
	}
  
	void box() {
		System.out.println("Упаковать пиццу в стандатную коробку PizzaStore");
	}
 
	public String getName() {
		return name;
	}

	public String toString() {
		StringBuffer display = new StringBuffer();
		display.append("---- " + name + " ----\n");
		display.append(dough + "\n");
		display.append(sauce + "\n");
		for (int i = 0; i < toppings.size(); i++) {
			display.append((String )toppings.get(i) + "\n");
		}
		return display.toString();
	}
}

 
 
