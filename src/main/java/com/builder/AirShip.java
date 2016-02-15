package com.builder;
/**
 * 创建飞船
 * @author spark
 *
 */
public class AirShip {
	private OrbitalModule orbitalModule;
	private Engine engine;
	private Escape escapeTower;
	public OrbitalModule getOrbitalModule() {
		return orbitalModule;
	}
	public void setOrbitalModule(OrbitalModule orbitalMOdule) {
		this.orbitalModule = orbitalMOdule;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public Escape getEscapeTower() {
		return escapeTower;
	}
	public void setEscapeTower(Escape escapeTower) {
		this.escapeTower = escapeTower;
	}
	public AirShip() {}
	
	
	
}


class OrbitalModule {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrbitalModule() {
		super();
	}

	public OrbitalModule(String name) {
		super();
		this.name = name;
	}
}

class Engine{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Engine() {
		super();
	}

	public Engine(String name) {
		super();
		this.name = name;
	}
	
}

class Escape{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Escape() {
		super();
	}

	public Escape(String name) {
		super();
		this.name = name;
	}
	
}
