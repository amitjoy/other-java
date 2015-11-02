package com.amitinside.dao.impl;

import com.google.common.collect.Lists;
import com.amitinside.dao.ItemDao;
import com.amitinside.item.Item;
import java.util.List;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
public class AnotherItemDao implements ItemDao {

    @Override
    public List<Item> fetchItems() {
        List<Item> results = Lists.newArrayList();
        results.add(new Item(99, 9));
        return results;
    }
}