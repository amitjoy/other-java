package com.amitinside.beans;

import java.util.List;

import com.amitinside.item.Item;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
public interface ItemProcessorSvc {

    public void execute();
    
    public void observeItemEvent(Item item);
    
    public List<Item> getItemList();
}
