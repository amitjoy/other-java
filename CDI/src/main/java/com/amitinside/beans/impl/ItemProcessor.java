package com.amitinside.beans.impl;

import com.amitinside.dao.ItemDao;
import com.amitinside.annotation.Demo;
import com.amitinside.annotation.Log;
import com.amitinside.item.Item;
import com.amitinside.errorHandler.ItemErrorHandler;
import com.amitinside.annotation.Notify;
import com.amitinside.annotation.TimeStamp;
import com.amitinside.beans.ItemProcessorSvc;
import com.amitinside.validator.ItemValidator;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
@Dependent
@Log
@TimeStamp
@Stateless
public class ItemProcessor implements ItemProcessorSvc {

    @Inject
    @Demo
    private ItemDao itemDao;
    @Inject
    private ItemValidator itemValidator;
    @Inject
    @Notify
    private ItemErrorHandler itemErrorHandler;
    @Inject
    private Event<Item> eventProcessor;

    public void observeItemEvent(@Observes Item item) {
        System.out.println("Item event observed for item " + item);
    }

    @Override
    public void execute() {
        List<Item> items = itemDao.fetchItems();
        for (Item item : items) {
            eventProcessor.fire(item);
            if (!itemValidator.isValid(item)) {
                itemErrorHandler.handleItem(item);
            }
        }
    }

    @Override
    public List<Item> getItemList() {
        return itemDao.fetchItems();
    }
}
