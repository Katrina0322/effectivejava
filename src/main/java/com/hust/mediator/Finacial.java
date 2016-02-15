package com.hust.mediator;

public class Finacial implements Department {
	private Mediator m;
	
	public Finacial(Mediator m) {
		super();
		this.m = m;
		m.register("finacial", this);
	}

	@Override
	public void selfAction() {
		System.out.println("数钱");
	}

	@Override
	public void outAction() {
		System.out.println("有资金了");
	}

}
