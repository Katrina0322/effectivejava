package com.adapter;
/**
 * 对象适配器，使用组合的方式
 * @author spark
 *
 */
public class Adapter2 implements Target{
	private Adaptee adaptee;

	public Adapter2(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	public void handleRequest() {
		adaptee.request();
	}

}
