package com.hust.bridge;

public  class Computer {
	protected Brand brand;

	public Computer(Brand brand) {
		super();
		this.brand = brand;
	}
	
	public void sale() {
		brand.sale();
	}
}


class Desktop extends Computer{

	public Desktop(Brand brand) {
		super(brand);
	}
	
}
