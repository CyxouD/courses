package com.rxn1d.courses;
public abstract class AbstractPlayer {
	static final int MIN_PLAYER_BET = 1;
    static final int MAX_PLAYER_BET = 500;


	double balance;
	String name;
	BetType betType;
	double bet;
	boolean inGame;
	
	public AbstractPlayer(double balance, String name) {
		this.balance = balance;
		this.name = name;
		inGame = true;
	}
	
	public abstract void makeBet(String bet);

	
	public String toString(){
		return name + " = " + balance;
	}
	
	public boolean isInGame() {
		return inGame;
	}
	

	public double getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}

	public BetType getBetType() {
		return betType;
	}
	
	public double getBet() {
		return bet;
	}

    public void setBalance(double newBalance){
        balance = newBalance;
    }

}
