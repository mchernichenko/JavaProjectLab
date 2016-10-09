package org.billing.jlab.starbuzz;

public abstract class Beverage {
	String description = "Безымянный напиток";
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
