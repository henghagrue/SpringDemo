package com.example.proxy.CGLibProxy;

import org.springframework.cglib.proxy.Enhancer;

import com.example.proxy.basic.RealSubject;

public class Client {
	public static void main(String[] args){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(RealSubject.class);
		enhancer.setCallback(new DemoMethodInterceptor());
		//刺客，realSubject不是单纯的目标类，而是增强过的目标类
		RealSubject realSubject = (RealSubject) enhancer.create();
		realSubject.hello();
		realSubject.request();
	}
}
