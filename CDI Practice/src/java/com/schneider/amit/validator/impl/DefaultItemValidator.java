package com.schneider.amit.validator.impl;

import com.schneider.amit.validator.ItemValidator;
import com.schneider.amit.item.Item;
import javax.enterprise.inject.Alternative;

@Alternative
public class DefaultItemValidator implements ItemValidator {

    @Override
    public boolean isValid(Item item) {
        return item.getValue() < item.getLimit();
    }
}
