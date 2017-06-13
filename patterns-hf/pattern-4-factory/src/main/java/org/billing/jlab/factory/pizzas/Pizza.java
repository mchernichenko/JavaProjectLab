package org.billing.jlab.factory.pizzas;

import java.util.ArrayList;

/**
 * Родительский класс для всех конкретных классов пиццы.
 * Общие атрибуты класса объявлены здесь и определяются в подклассах.
 * Класс объявлен абстрактным, чтобы было нельзя создать его экзампляр т.е. new Pizza(), только через субкласс.
 */

abstract public class Pizza {
	String name;
	String dough;
	String sauce;
	ArrayList toppings = new ArrayList();

	public String getName() {
		return name;
	}

	public void prepare() {
		System.out.println("Делаем пицу " + name);
	}

	public void bake() {
		System.out.println("Выпекаем " + name);
	}

	public void cut() {
		System.out.println("Нарезаем " + name);
	}

	public void box() {
		System.out.println("Упаковываем " + name);
	}

	public String toString() {
		// code to display pizza name and ingredients
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

