package com.hust.mediator;

public class Development implements Department {
	private Mediator m;
	
	public Development(Mediator m) {
		super();
		this.m = m;
		m.register("development", this);
	}

	@Override
	public void selfAction() {
		System.out.println("开发项目");
	}

	@Override
	public void outAction() {
		System.out.println("需要资金支持");
		
	}

}
