package com.schneider.amit.errorHandler.impl;

import com.schneider.amit.annotation.Notify;
import com.schneider.amit.errorHandler.ItemErrorHandler;
import com.schneider.amit.item.Item;
import javax.enterprise.event.Event;
import javax.inject.Inject;

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