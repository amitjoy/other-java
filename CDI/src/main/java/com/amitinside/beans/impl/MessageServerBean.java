package com.amitinside.beans.impl;

import com.amitinside.annotation.Date;
import com.amitinside.annotation.Log;
import com.amitinside.annotation.TimeStamp;
import com.amitinside.beans.MessageServerBeanSvc;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
@Dependent
@Stateless
//@MessageBean
@TimeStamp
@Local
@Log
public class MessageServerBean implements MessageServerBeanSvc {

    @Inject
    @Date
    public int month;
    
    public String getMessage() {
        return "Hello Weld!" + month;
    }
}
