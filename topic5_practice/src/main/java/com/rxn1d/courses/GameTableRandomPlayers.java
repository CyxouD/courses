package com.rxn1d.courses;

public class GameTableRandomPlayers extends AbstractGameTable {
	private double bet;
	
	
    public GameTableRandomPlayers(int nPlayers, double balance, double bet){
        this.bet = bet;
        createPlayers(nPlayers,balance);
    }
    
    public void startGame(){
    	while(isPlayersHaveEnoughBalance()){
    		makePlayersBet();
    		spin();
    		checkPlayers();
    	}
    }

    private void createPlayers(int nPlayers, double balance){
    	for (int i = 0; i < nPlayers; i++){
    		players.add(new RandomTablePlayer(balance, "vasya"+i));
    	}
    	GameTableStats.registerHashPlayersForDatabase(players);
    }
    
    private void makePlayersBet(){
    	for (AbstractPlayer p : players){
    		if (p.isInGame()){
    			p.makeBet(bet+"");
    			System.out.println("Player "+ p.getName() + " ������ " + p.getBet()+ " �� " + p.getBetType());
    		}
    	}
    	
    }
    
    

    
	public static void main(String[] args){
    
    	}
}
