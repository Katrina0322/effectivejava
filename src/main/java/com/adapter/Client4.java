package com.adapter;

/**
 * 客户端类
 * 
 * @author spark
 *
 */
public class Client4 {
	
	
	public void test1(Target t) {
		t.handleRequest();
	}

	public static void main(String[] args) {
		Client4 c = new Client4();
		Adaptee adaptee = new Adaptee();
//		Target t = new Adapter();
		Target t = new Adapter2(adaptee);
		c.test1(t);
	}

}
