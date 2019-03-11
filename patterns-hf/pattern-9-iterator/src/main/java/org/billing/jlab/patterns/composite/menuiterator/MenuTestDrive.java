package org.billing.jlab.patterns.composite.menuiterator;

import java.util.Comparator;
import java.util.function.Function;

/**
 *  Аналогично, создание древовидной структуры меню, которое в свою очередь может содержать подменю
 */

public class MenuTestDrive {
	public static void main(String args[]) {

		// Объявление набора менюх
		MenuComponent menu1 = new Menu("Menu1", "Breakfast");
		MenuComponent menu2 = new Menu("Menu2", "Lunch");
		MenuComponent menu3 = new Menu("Menu3", "Dinner");
		MenuComponent menu11 = new Menu("Menu11", "Dessert of course!");
		MenuComponent menu22 = new Menu("Menu22", "Stuff to go with your afternoon coffee");

		// Объявление главного меню. Т.е. это общее меню для объединения других меню. Оно не будет сожержать конткетные элементы, т.е. блюда
		MenuComponent mainMenu = new Menu("MainMenu", "All menus combined");

		// СОЗДАНИЕ ДЕРЕВА МЕНЮ
		mainMenu.add(menu1);
		mainMenu.add(menu2);
		mainMenu.add(menu3);
		menu1.add(menu11);
		menu2.add(menu22);

		// НАПОЛНЕНИЕ КОНКРЕТНЫХ МЕНЮ КОНКРЕТНЫМИ БЛЮДАМИ

		menu1.add(new MenuItem("manuItem1_1", "Pancakes with scrambled eggs, and toast", true, 2.99));
		menu1.add(new MenuItem("menuItem1_2","Pancakes with fried eggs, sausage",false,	2.99));
	/*	menu1.add(new MenuItem("menuItem1_3", "Pancakes made with fresh blueberries, and blueberry syrup", true, 3.49));
		menu1.add(new MenuItem("menuItem1_4", "Waffles, with your choice of blueberries or strawberries", true, 3.59));*/

		menu2.add(new MenuItem("menuItem2_1", "(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99));
		menu2.add(new MenuItem("menuItem2_2", "Bacon with lettuce & tomato on whole wheat", false, 2.99));
	/*	menu2.add(new MenuItem("menuItem2_3", "A bowl of the soup of the day, with a side of potato salad", false, 3.29));
		menu2.add(new MenuItem("menuItem2_4", "A hot dog, with saurkraut, relish, onions, topped with cheese", false, 3.05));
		menu2.add(new MenuItem("menuItem2_5", "Steamed vegetables over brown rice", true, 3.99));
		menu2.add(new MenuItem("menuItem2_6", "Spaghetti with Marinara Sauce, and a slice of sourdough bread", true, 3.89));*/

		menu11.add(new MenuItem("menuItem11_1", "Apple pie with a flakey crust, topped with vanilla icecream", true, 1.59));
		menu11.add(new MenuItem("menuItem11_2", "Creamy New York cheesecake, with a chocolate graham crust", true, 1.99));
	//	menu11.add(new MenuItem("menuItem11_3", "A scoop of raspberry and a scoop of lime", true, 1.89));

		menu3.add(new MenuItem("menuItem3_1", "Veggie burger on a whole wheat bun, lettuce, tomato, and fries", true, 3.99));
		menu3.add(new MenuItem("menuItem3_2", "A cup of the soup of the day, with a side salad", false, 3.69));
	//	menu3.add(new MenuItem("menuItem3_3", "A large burrito, with whole pinto beans, salsa, guacamole", true, 4.29));

		menu22.add(new MenuItem("menuItem22_1", "Crumbly cake topped with cinnamon and walnuts", true, 1.59));
		menu22.add(new MenuItem("menuItem22_2", "Flavors include sesame, poppyseed, cinnamon raisin, pumpkin", false, 0.69));
	//	menu22.add(new MenuItem("menuItem22_3", "Three almond or hazelnut biscotti cookies", true, 0.89));

		// добавляем меню в клиента-потребителя меню, в данном случае официантка и печатаем только вегитарианские блюда используя итератор
		Waitress waitress = new Waitress(mainMenu);
	//	waitress.printVegetarianMenu();
		waitress.printMenu();

	}
}
