package com.factory.abstractfactory;

public interface Engine {
	void run();

	void start();

}

class LuxuryEngine implements Engine {

	@Override
	public void run() {
		System.out.println("转得快");
	}

	@Override
	public void start() {
		System.out.println("自启动");
	}

}


class LowEngine implements Engine {

	@Override
	public void run() {
		System.out.println("转得慢");
	}

	@Override
	public void start() {
		System.out.println("无法自启动");
	}

}