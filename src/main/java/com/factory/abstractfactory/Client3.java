package com.factory.abstractfactory;

public class Client3 {

	public static void main(String[] args) {
		CarFactory factory = new LuxuryCarFactory();
		Engine e = factory.createEngine();
		e.run();
		e.start();

	}

}
