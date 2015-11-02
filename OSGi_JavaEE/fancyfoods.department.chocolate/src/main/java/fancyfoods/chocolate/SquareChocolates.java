package fancyfoods.chocolate;

import fancyfoods.food.Food;

public class SquareChocolates implements Food {

	@Override
	public String getName() {
		return "Normal chocolates";
	}

	@Override
	public double getPrice() {
		return 6.99;
	}

	@Override
	public int getQuantityInStock() {
		return 20;
	}
}
