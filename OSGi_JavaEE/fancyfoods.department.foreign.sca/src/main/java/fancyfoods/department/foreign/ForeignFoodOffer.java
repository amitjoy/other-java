package fancyfoods.department.foreign;

import java.util.List;

import fancyfoods.food.Food;
import fancyfoods.food.Inventory;
import fancyfoods.offers.SpecialOffer;

 
public class ForeignFoodOffer implements SpecialOffer, LowTechOffer {

	private Inventory inventory;

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public String getDescription() {
		return "Something exotic to spice up your weekend.";
	}

	@Override
	public Food getOfferFood() {
		List<Food> foods = inventory.getFoodsWhoseNameContains("Foreign", 1);
		Food mostStocked = foods.get(0);
		return mostStocked;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return 666;
	}
} 

