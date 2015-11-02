package com.schneider.amit.dao.impl;

import com.google.common.collect.Lists;
import com.schneider.amit.dao.ItemDao;
import com.schneider.amit.item.Item;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Default;

@Default
public class AnotherItemDao implements ItemDao {

    @Override
    public List<Item> fetchItems() {
        List<Item> results = Lists.newArrayList();
        results.add(new Item(99, 9));
        return results;
    }

}