package com.rxn1d.courses;
import java.util.Random;

public class BetType {
	
	private String betType;
	private static final String[] betTypes = {"RED","BLACK","ODD","EVEN","SMALL","BIG","STRAIGHT_UP"};
	
	
	public BetType(){
		betType = betTypes[new Random().nextInt(betTypes.length)];
		if (betType.equals("STRAIGHT_UP")){
			betType += " " + new Random().nextInt(38);
		}
	}

	public static boolean wonColor(String winningBetType, String color) {
		GameTableStats.updateBetsFrequency(winningBetType);
		if (color.equals(winningBetType)) {
			return true;
		}
		return false;
	}

	public static boolean wonInterval(String interval, int number){
		GameTableStats.updateBetsFrequency(interval);
		if (interval.equals("SMALL")){
			if (number >= 1 && number <= 18) return true;
			return false;
		}
		else{
			if (number >= 19) return true;
			return false;
		}
	}

	public static boolean wonParity(String parity, int number){
		GameTableStats.updateBetsFrequency(parity);
		if (parity.equals("ODD")){
			if (number % 2 == 0) return true;
			return false;
		}
		else{
			if (number % 2 == 1) return true;
			return false;
		}
	}

	public static boolean wonNumber(int ballNumber, String playerBetType){
		GameTableStats.updateBetsFrequency("STRAIGHT_UP");
		int number = Integer.parseInt(playerBetType.substring(playerBetType.indexOf("P") + 1));
		if (ballNumber == number) return true;
		return false;
	}

	public BetType(String betType){
		this.betType = betType;
	}
	
	public String getBetType() {
		return betType;
	}
	
	public String toString(){
		return betType;
	}
	
	
	public static void main(String[] args){
		for (int i = 0; i < 99;i++)
			System.out.println(new BetType());
	}

}
