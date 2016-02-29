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
