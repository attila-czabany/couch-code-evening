package com.scytl.couchdb.model;

public class Ball extends BasicDocument{

	private static final long serialVersionUID = 1L;
	
	private int number;
	
	private String game;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

}
