package com.rxn1d.courses;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public abstract class AbstractGameTable {
    private static RouletteField ball;
	private static RouletteField[] rouletteFields;
	HashSet<AbstractPlayer> players;
	private List<Boolean> colors = Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
              true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
	private List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36);
	
	
	public AbstractGameTable(){
		rouletteFields = new RouletteField[37]; 
		players = new HashSet<AbstractPlayer>();
        createTable();
	}
	
	private void createTable(){
        rouletteFields[0] = new RouletteField(0,"green");
        Collections.shuffle(numbers);
        Collections.shuffle(colors);
        for (int i = 0; i < rouletteFields.length-1; i++){
            rouletteFields[i+1] = new RouletteField(numbers.get(i), colors.get(i)?"RED":"BLACK" );
        }
        GameTableStats.initStats(rouletteFields);
    }
	
    public void spin(){
    	int randomN = new Random().nextInt(rouletteFields.length);
        ball = new RouletteField(rouletteFields[randomN].getNumber(),rouletteFields[randomN].getColor());
		System.out.println("Winning number = "+ball);
        GameTableStats.updateFieldsFrequency(ball);
    }
    
    public void checkPlayers(){
    	for (AbstractPlayer p : players)
    		if (p.isInGame()){
    			p.getResult(getBall());
    		}
    	
    }
    
	public boolean isPlayersHaveEnoughBalance(){
		for (AbstractPlayer p : players){
			if (p.getBalance() > 0) return true;
		}
		return false;
	}
    
    public RouletteField getBall() {
		return ball;
	}

	public static RouletteField[] getRouletteFields() {
		return rouletteFields;
	}
    
    

}
