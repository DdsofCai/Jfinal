package com.demo.blog;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;


public class UserInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        System.out.println("Before User Interceptor");
        inv.invoke();
        System.out.println("After User Interceptor");
    }
}
