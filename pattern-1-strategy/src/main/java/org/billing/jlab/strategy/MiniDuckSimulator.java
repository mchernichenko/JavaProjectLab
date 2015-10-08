package org.billing.jlab.strategy;

public class MiniDuckSimulator {
 
	public static void main(String[] args) {
 
		Duck mallard = new MallardDuck(new FlyWithWings(), new Quack()); // утка умеет летать и крякать
		Duck rubberDuckie = new RubberDuck(new FlyNoWay(), new Squeak()); // утка не умеет летать и пищит
		Duck decoy = new DecoyDuck(new FlyNoWay(), new MuteQuack());
		Duck model = new ModelDuck(new FlyNoWay(), new Quack());

		// разные объекты реагируют на алгоритм кряканья по-разному
		mallard.performQuack();
		rubberDuckie.performQuack();
		decoy.performQuack();

		System.out.println("-- Пример динамической смены алгоритма --");
		model.performFly();	
		model.setFlyBehavior(new FlyRocketPowered());
		System.out.println("А теперь?");
		model.performFly();
	}
}
