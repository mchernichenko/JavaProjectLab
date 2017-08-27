package org.billing.jlab.pattern.singleton.chocolate;
 
public class ChocolateController {
	public static void main(String args[]) {
		ChocolateBoiler boiler = ChocolateBoiler.getInstance();
		boiler.fill();
		boiler.boil();
		boiler.drain();

		// будет возвращён существующий экземпляр
		ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();
	}
}
