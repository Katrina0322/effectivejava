package com.factory.simplefactory;

public class Client1 {

	public static void main(String[] args) {

		Car c1 = CarFactory.createCar("奥迪");

		c1.run();
	}

}
