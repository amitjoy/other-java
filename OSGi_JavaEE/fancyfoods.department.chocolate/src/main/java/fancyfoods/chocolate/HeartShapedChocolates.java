package fancyfoods.chocolate;

import fancyfoods.food.Food;

public class HeartShapedChocolates implements Food {

	@Override
	public String getName() {
		return "Heart-shaped chocolates";
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
