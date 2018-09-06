package com.example.proxy.CGLibProxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class DemoMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("begin in cglib");
		Object result = null;
		try{
			result = proxy.invokeSuper(obj,args);
		}catch(Exception e){
			System.out.println("get ex:" + e.getMessage());
		}finally{
			System.out.println("after in cglib");
		}
		return result;
	}

}
