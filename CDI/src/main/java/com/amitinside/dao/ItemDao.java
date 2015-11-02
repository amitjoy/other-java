package com.amitinside.dao;

import com.amitinside.item.Item;
import java.util.List;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
public interface ItemDao {

    List<Item> fetchItems();

}
