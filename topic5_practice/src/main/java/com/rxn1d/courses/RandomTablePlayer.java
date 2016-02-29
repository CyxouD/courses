package com.rxn1d.courses;

public class RandomTablePlayer extends AbstractPlayer {
	
	public RandomTablePlayer(double balance, String name) {
		super(balance, name);
		// TODO Auto-generated constructor stub
	}

	
	public void makeBet(String bet){
		if (Double.parseDouble(bet) > balance) {
			inGame = false;
			return;
		}
		this.bet = Double.parseDouble(bet);
		this.betType = new BetType();
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
