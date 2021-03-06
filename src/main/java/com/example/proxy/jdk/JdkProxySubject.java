package com.example.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.example.proxy.basic.RealSubject;
//代理类jdkProxySubject
public class JdkProxySubject implements InvocationHandler {
	private RealSubject realSubject;
	
	public JdkProxySubject(RealSubject realSubject){
		this.realSubject = realSubject;
	}
	
	/**
	 * invoke 方法参数解析
	 * Object prox :指被代理的对象
	 * Method method>:要调用的方法
	 * Object[] args:方法调用时所需要的参数
	 * InvocationHandler 接口的子类可以看成代理的最终操作类
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("jdk_before");
		Object result = null;
		try{
			//利用反射动态的来反射方法，这就是动态代理和静态代理的区别
			result = method.invoke(realSubject, args);
		}catch(Exception e){
			System.out.println("ex:" + e.getMessage());
			throw e;
		}finally{
			System.out.println("jdk_after");
		}
		return result;
	}

}
