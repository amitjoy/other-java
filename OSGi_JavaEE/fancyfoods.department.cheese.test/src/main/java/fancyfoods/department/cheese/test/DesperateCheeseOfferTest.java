package fancyfoods.department.cheese.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fancyfoods.department.cheese.offers.DesperateCheeseOffer;
import fancyfoods.food.Food;
import fancyfoods.food.Inventory;

 
public class DesperateCheeseOfferTest {

	@Test
	public void testOfferReturnsCorrectFood() {
		Food food = mock(Food.class);
		when(food.getName()).thenReturn("Green cheese");
		Inventory inventory = mock(Inventory.class);
		List<Food> foods = new ArrayList<Food>();
		foods.add(food);
		when(inventory.getFoodsWhoseNameContains("cheese", 1))
				.thenReturn(foods);

		DesperateCheeseOffer offer = new DesperateCheeseOffer();
		offer.setInventory(inventory);

		assertNotNull(offer.getOfferFood());
		assertEquals("Green cheese", offer.getOfferFood().getName());
	}
}

