package com.amitinside.dao.impl;

import com.google.common.collect.Lists;
import com.amitinside.dao.ItemDao;
import com.amitinside.annotation.Demo;
import com.amitinside.item.Item;
import java.util.List;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
@Demo
public class DefaultItemDao implements ItemDao {

    @Override
    public List<Item> fetchItems() {
        List<Item> results = Lists.newArrayList();
        results.add(new Item(34, 7));
        results.add(new Item(4, 37));
        results.add(new Item(24, 19));
        results.add(new Item(89, 32));
        return results;
    }
}
