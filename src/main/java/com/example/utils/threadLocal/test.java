package com.example.utils.threadLocal;
/**
 * 本地ThreadLocal的使用：以空间换时间，创建以Thread为key的一个map变量，存储该线程的副本，每个副本的参数都不一致，可模拟多实例；防止单实例的资源占用锁问题；
 * 
 * 
 * ThreadLocal翻译过来就是线程本地变量，初学者可能以为ThreadLocal是指一个Thread，
 * 其实说白了，ThreadLocal就是一个成员变量，
 * 只不过这是一个特殊的变量——变量值总是与当前线程（调用Thread.currentThread()得到）相关联。
 * 既然ThreadLocal是一个变量，那么其作用是是什么呢？
 * 说得抽象点就是提供了线程封闭性，说得具体点就是为每个使用该变量的线程提供一个变量的副本，
 * 这样每个使用该变量的线程都有一个副本，从而将线程之间对变量的访问隔离开来了，
 * 对变量的操作互不影响。
 * 
 * 
 * 当访问共享的可变数据时（因为还有final类型的不可变数据），
 * 通常会使用同步机制，因为同步需要加锁，所以在效率上可能会收到影响。
 * 一种避免使用同步的方式就是不共享数据。因为在单线程内访问数据就不需要考虑同步。
 * 这就是对线程封闭的解释，同时也是ThreadLocal设计的核心思想。
 * 当某个对象被线程封闭在一个线程内部时，该对象就自动实现了线程安全性。
 * @author upbchain12
 *
 */
public class test {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();
 
//	ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
//        protected Long initialValue() {
//            return Thread.currentThread().getId();
//        };
//    };
//    ThreadLocal<String> stringLocal = new ThreadLocal<String>(){;
//        protected String initialValue() {
//            return Thread.currentThread().getName();
//        };
//    };
 
     
    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }
     
    public long getLong() {
        return longLocal.get();
    }
     
    public String getString() {
        return stringLocal.get();
    }
    static  int i;
    public static void main(String[] args) throws InterruptedException {
        test test = new test();
         
        test.set();
        System.out.println("mainLong=" + test.getLong());
        System.out.println("mainString=" + test.getString());
        
//         for( i=0 ; i < 3 ; i ++){
	        Thread thread1 = new Thread(){
	            public void run() {
	                test.set();
	                System.out.println("thread_" + i + "_Long=" + test.getLong());
	                System.out.println("thread_" + i + "_String=" + test.getString());
	            };
	        };
	        thread1.start();
	        thread1.join();
//         }
//         
////        System.out.println(test.getLong());
////        System.out.println(test.getString());
    }
}