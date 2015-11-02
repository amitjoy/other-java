package com.schneider.amit.beans;

import com.schneider.amit.annotation.Demo;
import com.schneider.amit.dao.ItemDao;
import com.schneider.amit.item.Item;
import com.schneider.amit.errorHandler.ItemErrorHandler;
import com.schneider.amit.annotation.Notify;
import com.schneider.amit.validator.ItemValidator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ItemProcessor {

    @Inject @Demo
    private ItemDao itemDao;

    @Inject
    private ItemValidator itemValidator;

    @Inject @Notify
    private ItemErrorHandler itemErrorHandler;

    @Inject
    private Event<Item> eventProcessor;

    public void observeItemEvent(@Observes Item item) {
        System.out.println("Item event observed for item " + item);
    }

    public void execute() {
        List<Item> items = itemDao.fetchItems();
        for (Item item : items) {
            eventProcessor.fire(item);
            if (!itemValidator.isValid(item)) {
                itemErrorHandler.handleItem(item);
            }
        }
    }
}
