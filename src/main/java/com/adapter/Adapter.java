package com.adapter;
/**
 * 类适配器
 * @author spark
 *
 */
public class Adapter extends Adaptee implements Target{

	public void handleRequest() {
		super.request();
	}

}
