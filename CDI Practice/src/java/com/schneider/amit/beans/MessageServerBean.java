package com.schneider.amit.beans;

import com.google.common.collect.Lists;
import com.schneider.amit.annotation.Date;
import com.schneider.amit.annotation.Log;
import com.schneider.amit.annotation.MessageBean;
import com.schneider.amit.annotation.TimeStamp;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Stateless
//@MessageBean
@TimeStamp
public class MessageServerBean {

    @Inject
    @Date
    public int month;
    
    public String getMessage() {
        return "Hello Weld!" + month;
    }
}
