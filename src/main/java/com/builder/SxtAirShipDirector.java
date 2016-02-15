package com.builder;

public class SxtAirShipDirector implements AirShipDirector{
	
	private AirShipBuilder builder;
	
	

	public SxtAirShipDirector(AirShipBuilder builder) {
		
		this.builder = builder;
	}



	public AirShip directAirShip() {
		Engine a = builder.buildEngine();
		OrbitalModule b = builder.buildOrbitalModule();
		Escape c = builder.buildEscape();
		
		AirShip airShip = new AirShip();
		airShip.setEngine(a);
		airShip.setOrbitalModule(b);
		airShip.setEscapeTower(c);
 		return airShip;
	}


}
