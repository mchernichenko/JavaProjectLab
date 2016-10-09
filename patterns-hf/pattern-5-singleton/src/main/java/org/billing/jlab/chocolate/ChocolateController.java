package org.billing.jlab.chocolate;
 
public class ChocolateController {
	public static void main(String args[]) {
		ChocolateBoiler boiler = ChocolateBoiler.getInstance();
		boiler.fill();
		boiler.boil();
		boiler.drain();

		// будет возвращён сущетвующий экземпляр
		ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();
	}
}
