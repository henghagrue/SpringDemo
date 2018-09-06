package com.example.proxy.basic;
//目标对象RealSubject
public class RealSubject implements Subject {
	@Override
	public void request() {
		System.out.println("real subject execute request!");		
	}

	@Override
	public void hello() {
		System.out.println("hello!");
	}
	
	
}
