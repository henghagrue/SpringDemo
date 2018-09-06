package com.example.proxy.jdk;

import java.lang.reflect.Proxy;

import com.example.proxy.basic.RealSubject;
import com.example.proxy.basic.Subject;

//客户端Client
public class Client {
	public static void main(String[] args){
		Subject subject = (Subject)Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{Subject.class},new JdkProxySubject(new RealSubject()));
		subject.hello();
		subject.request();
	}
}
