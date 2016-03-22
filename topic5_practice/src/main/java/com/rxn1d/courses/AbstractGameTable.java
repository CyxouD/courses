package com.rxn1d.courses;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public abstract class AbstractGameTable {
    static final int MAX_PLAYER_NUMBER = 5;
    static final int MIN_PLAYER_NUMBER = 0;


    private static EuropeanRoulette europeanRoulette;
	HashSet<AbstractPlayer> players;
	
	
	public AbstractGameTable(){
        europeanRoulette = new EuropeanRoulette();
		europeanRoulette.setRouletteFields(new RouletteField[37]);
		players = new HashSet<AbstractPlayer>();
        createTable();
	}
	
	private void createTable(){
        List<Boolean> colors = Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
                true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36);

        europeanRoulette.getRouletteFields()[0] = new RouletteField(0,"green");
        Collections.shuffle(numbers);
        Collections.shuffle(colors);
        for (int i = 0; i < europeanRoulette.getRouletteFields().length-1; i++){
            europeanRoulette.getRouletteFields()[i+1] = new RouletteField(numbers.get(i), colors.get(i)?"RED":"BLACK" );
        }
        GameTableStats.initStats(europeanRoulette.getRouletteFields());
    }
	
    public void spin(){
    	int randomN = new Random().nextInt(europeanRoulette.getRouletteFields().length);
        europeanRoulette.setBall(new RouletteField(europeanRoulette.getRouletteFields()[randomN].getNumber(),europeanRoulette.getRouletteFields()[randomN].getColor()));
		System.out.println("Winning number = "+europeanRoulette.getBall());
        GameTableStats.updateFieldsFrequency(europeanRoulette.getBall());
    }

    public EuropeanRoulette getEuropeanRoulette() {
        return europeanRoulette;
    }

    public void checkPlayers(){
    	for (AbstractPlayer p : players)

    		if (p.isInGame()){
				CasinoCash.getResult(europeanRoulette.getBall(),p);
    		}
    	
    }
    
	public boolean isPlayersHaveEnoughBalance(){
		for (AbstractPlayer p : players){
			if (p.getBalance() > 0) return true;
		}
		System.out.println("SORRY ALL PLAYERS ARE BANCROUPTS");
		return false;
	}


    

}
