package com.amitinside.errorHandler.impl;

import com.amitinside.errorHandler.ItemErrorHandler;
import com.amitinside.item.Item;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
public class FileErrorReporter implements ItemErrorHandler {

    @PostConstruct
    public void init() {
        System.out.println("Creating file error reporter");
    }

    @PreDestroy
    public void release() {
        System.out.println("Closing file error reporter");
    }

    @Override
    public void handleItem(Item item) {
        System.out.println("Saving " + item + " to file");
    }
}
