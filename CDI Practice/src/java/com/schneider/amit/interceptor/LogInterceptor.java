package com.schneider.amit.interceptor;

import com.schneider.amit.annotation.Log;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class LogInterceptor {
    
    @AroundInvoke
    public void log(InvocationContext context){
        System.out.println("Interceptor: " + context.getMethod().getClass());
    }
}
