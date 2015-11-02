/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schneider.amit.interceptor;

import com.schneider.amit.annotation.TimeStamp;
import java.sql.Timestamp;
import java.util.Date;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@TimeStamp
@Interceptor
public class TimerInterceptor {
    
    private static final Timestamp timestamp;
    
    static {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    @AroundInvoke
    public void getTime(InvocationContext context){
        System.out.println("------------------- "+timestamp+ " -------------------");
    }
}

