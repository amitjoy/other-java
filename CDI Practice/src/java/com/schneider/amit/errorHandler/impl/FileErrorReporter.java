package com.schneider.amit.errorHandler.impl;

import com.schneider.amit.errorHandler.ItemErrorHandler;
import com.schneider.amit.item.Item;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
