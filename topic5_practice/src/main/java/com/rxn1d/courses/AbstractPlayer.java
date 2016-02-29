package com.rxn1d.courses;
public abstract class AbstractPlayer {
	
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
	
	
	public void getResult(RouletteField ball){
		String playerBetType = betType.getBetType();
		switch(playerBetType){
		case "RED":
			if ("RED".equals(ball.getColor())) won1by1();
			else lost();
			GameTableStats.updateBetsFrequency("RED");
			break;
			
		case "BLACK":
			if ("BLACK".equals(ball.getColor())) won1by1();
			else lost();
			GameTableStats.updateBetsFrequency("BLACK");
			break;
			
		case "ODD":
			if (ball.getNumber() % 2 == 1) won1by1();
			else lost();
			GameTableStats.updateBetsFrequency("ODD");
			break;
			
		case "EVEN":
			if (ball.getNumber() % 2 == 0) won1by1();
			else lost();
			GameTableStats.updateBetsFrequency("EVEN");
			break;
		case "SMALL":
			if (ball.getNumber() >= 1 && ball.getNumber() <= 18) won1by1();
			else lost();
			GameTableStats.updateBetsFrequency("SMALL");
			break;
		
		case "BIG":
			if (ball.getNumber() >= 19) won1by1();
			else lost();
			GameTableStats.updateBetsFrequency("BIG");
			break;
			
		default:
			int number = Integer.parseInt(playerBetType.substring(playerBetType.indexOf("P")+2));
			if (ball.getNumber() == number) won1by35();
			else lost();
			GameTableStats.updateBetsFrequency("STRAIGHT_UP");
			break;	
		}
	}
	
	
	private void won1by1(){
		balance = balance + bet;
		GameTableStats.updateCasinoBalance(-bet);
		printPlayerWon();
	}
	
	private void won1by35(){
		balance = balance + 35 * bet;
		GameTableStats.updateCasinoBalance(- 35 * bet);
		printPlayerWon();
	}
	
	private void lost(){
		balance = balance - bet;
		GameTableStats.updateCasinoBalance(bet);
		printPlayerLost();
	}
	
	private void printPlayerWon(){
		System.out.println("PLAYER: " + name +" +"+ bet );
	}
	
	private void printPlayerLost(){
		System.out.println("PLAYER: " + name +" -"+ bet );
	}
	
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

}
