package com.amitinside.validator.impl;

import com.amitinside.validator.ItemValidator;
import com.amitinside.item.Item;
import javax.enterprise.inject.Alternative;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
@Alternative
public class RelaxedItemValidator implements ItemValidator {

    @Override
    public boolean isValid(Item item) {
        return item.getValue() < (item.getLimit() * 2);
    }
}