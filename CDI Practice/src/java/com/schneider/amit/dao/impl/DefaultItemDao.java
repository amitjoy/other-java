package com.schneider.amit.dao.impl;

import com.google.common.collect.Lists;
import com.schneider.amit.dao.ItemDao;
import com.schneider.amit.annotation.Demo;
import com.schneider.amit.item.Item;
import java.util.ArrayList;
import java.util.List;

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
