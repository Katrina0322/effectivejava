package com.hust.decorator;

public interface ICar {
	void move();

}

class Car implements ICar{

	@Override
	public void move() {
		System.out.println("陆地上跑");
	}
	
}

class SuperCar implements ICar{
	protected Car car;

	public SuperCar(Car car) {
		super();
		this.car = car;
	}
	
	

	@Override
	public void move() {
		car.move();
	}
	
}

class FlyCar extends SuperCar{

	public FlyCar(Car car) {
		super(car);
	}
	public void fly(){
		System.out.println("可以飞");
	}
	
	@Override
	public void move() {
		super.move();
		fly();
	}
	
}
