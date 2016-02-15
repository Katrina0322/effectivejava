package com.builder;

public class Client3 {

	public static void main(String[] args) {
		
		AirShipDirector director = new SxtAirShipDirector(new SxtAirShipBuilder());
		AirShip airShip = director.directAirShip();
		System.out.println(airShip.getEngine());

	}

}
