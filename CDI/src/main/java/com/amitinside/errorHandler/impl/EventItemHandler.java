package com.amitinside.errorHandler.impl;

import com.amitinside.annotation.Notify;
import com.amitinside.errorHandler.ItemErrorHandler;
import com.amitinside.item.Item;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
@Notify
public class EventItemHandler implements ItemErrorHandler {

    @Inject
    private Event<Item> itemEvent;

    @Override
    public void handleItem(Item item) {
        System.out.println("Firing Event");
        itemEvent.fire(item);
    }
}