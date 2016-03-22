package com.rxn1d.courses;

public class RandomTablePlayer extends AbstractPlayer {
	
	public RandomTablePlayer(double balance, String name) {
		super(balance, name);
	}

	
	public void makeBet(String bet){
		if (Double.parseDouble(bet) > balance) {
			inGame = false;
			return;
		}
		this.bet = Double.parseDouble(bet);
		this.betType = new BetType();
	}

}
