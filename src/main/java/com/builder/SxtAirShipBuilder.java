package com.builder;

public class SxtAirShipBuilder implements AirShipBuilder {

	public OrbitalModule buildOrbitalModule() {
		return new OrbitalModule("牛逼的发动机");
	}

	public Engine buildEngine() {
		return new Engine("牛逼的引擎");
	}

	public Escape buildEscape() {
		return new Escape("逃生利器");
	}

}
