package com.rxn1d.courses;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class GameTableStats {

	private static int totalBetsCount;
	private static double casinoBalance;
	private static RouletteField mostFrequentNumber;
	private static HashSet<AbstractPlayer> players;
	private static HashMap<RouletteField,Integer> rouletteFieldsFrequency = new HashMap<RouletteField,Integer>();
	private static HashMap<String,Integer> betsByTypeFrequency = new HashMap<String,Integer>();

	
	private GameTableStats(){
		
	}
	
	public static void updateFieldsFrequency(RouletteField newBall){
		rouletteFieldsFrequency.put(newBall, rouletteFieldsFrequency.get(newBall)+1);
	}
	
	public static void updateBetsFrequency(String betType){
		betsByTypeFrequency.put(betType, betsByTypeFrequency.get(betType)+1);
		updateTotalBetsCount();
	}
	
	public static void updateCasinoBalance(double cash) {
		casinoBalance += cash;
	}
	
	
	public static int getTotalBetsCount() {
		return totalBetsCount;
	}


	public static String getBetsByTypeFrequency() {
		return betsByTypeFrequency.toString();
	}

	public	static double getCasinoBalance() {
		return casinoBalance;
	}
	
	public static String getBalanceOfTablePlayers(){
		return players.toString();
	}
	/*
	public static void registerPlayersForDatabase(Player[] tablePlayers){
		players = tablePlayers;
	}
	*/
	public static <T extends AbstractPlayer> void registerHashPlayersForDatabase(HashSet<T> tablePlayers){
		players = (HashSet<AbstractPlayer>) tablePlayers;
	}

	public static RouletteField getMostFrequentNumber() {
		int maxValue = Collections.max(rouletteFieldsFrequency.values());
		for (Entry<RouletteField, Integer> entry : rouletteFieldsFrequency.entrySet()) {  // Iterate through hashmap
	            if (entry.getValue() == maxValue) {
	            	mostFrequentNumber = entry.getKey();
	                return mostFrequentNumber;    // Print the key with max value
	            }
		}
	     return null;
	}


	public static void initStats(RouletteField[] gameFields){
		for (RouletteField rf : gameFields){
			rouletteFieldsFrequency.put(rf, 0);
		}
		betsByTypeFrequency.put("RED", 0);
		betsByTypeFrequency.put("BLACK", 0);
		betsByTypeFrequency.put("ODD", 0);
		betsByTypeFrequency.put("EVEN", 0);
		betsByTypeFrequency.put("SMALL", 0);
		betsByTypeFrequency.put("BIG", 0);
		betsByTypeFrequency.put("STRAIGHT_UP", 0);
		
		casinoBalance = 100000;
		totalBetsCount = 0;
		
	}
	
	public static void printStats(){
		System.out.println("STATS");
    	System.out.println("Total bets count = "+GameTableStats.getBetsByTypeFrequency());
    	System.out.println("Total bets by type "+GameTableStats.getTotalBetsCount());
    	System.out.println("Balance = "+GameTableStats.getCasinoBalance());
    	System.out.println("Most frequent number = "+GameTableStats.getMostFrequentNumber());
    	System.out.println("Players "+GameTableStats.getBalanceOfTablePlayers());	
	}
	
	private static void updateTotalBetsCount() {
		totalBetsCount++;
	}
	
	
	
	
}
