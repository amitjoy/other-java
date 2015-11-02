package com.amitinside.interceptor;

import com.amitinside.annotation.TimeStamp;
import java.sql.Timestamp;
import java.util.Date;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
@TimeStamp
@Interceptor
public class TimerInterceptor {

    private static final Timestamp timestamp;

    static {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    @AroundInvoke
    public Object getTime(InvocationContext ctx) throws Exception {
        System.out.println("------------------- " + timestamp + " -------------------");
        return ctx.proceed();
    }
}
