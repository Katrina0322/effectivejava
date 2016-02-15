package com.hust.flyweight;

public class Client {

	public static void main(String[] args) {
		Chess chess1 = ChessFactory.getChess("black");
		Chess chess2 = ChessFactory.getChess("black");
		chess1.display(new Coordinate(10, 1));
		chess2.display(new Coordinate(20, 10));
	}

}
