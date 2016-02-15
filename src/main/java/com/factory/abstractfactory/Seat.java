package com.factory.abstractfactory;

public interface Seat {
	void message();
}

class LuxurySeat implements Seat{

	@Override
	public void message() {
		System.out.println("按摩椅");
	}
	
}

class LowSeat implements Seat{

	@Override
	public void message() {
		System.out.println("不能摩椅");
	}
	
}
