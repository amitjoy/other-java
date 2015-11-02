package com.schneider.amit.dao;

import com.schneider.amit.item.Item;
import java.util.List;

public interface ItemDao {

    List<Item> fetchItems();

}
