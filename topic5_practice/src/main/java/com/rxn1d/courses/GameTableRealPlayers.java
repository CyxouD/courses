package com.rxn1d.courses;

public class GameTableRealPlayers extends AbstractGameTable {
	private boolean isGameStarted = false;
	
	
	public boolean addNewPlayer(String player){ 
		if (players.size() >= 5){
			setGameStarted(true);
			return false;
		}
		
		String playerName = player.split(" ")[0];
		String playerBalance = player.split(" ")[1];
		boolean isAdded = players.add(new RealTablePlayer(Double.parseDouble(playerBalance), playerName));
		if (isAdded){
			System.out.println("New user with name = "+playerName+" and balance = "+playerBalance+" is added to table");
			return true;
		}
		return false;
		
	}
	
	public RealTablePlayer findPlayerWithName(String name){
		for (AbstractPlayer p : players){
			if (p.getName().equals(name)) return (RealTablePlayer) p;
		}
		System.out.println("BET NOT ACCEPTED");
		System.out.println("Player with this name is not registered");
		return null;
	}
	
	public boolean isAllPlayersMadeBet(){
		for (AbstractPlayer p : players){
			RealTablePlayer realPlayer = (RealTablePlayer) p;
			if (!realPlayer.isMadeBet() && realPlayer.isInGame()) return false;
		}
		return true;
	}
	
    public void checkPlayers(){
    	super.checkPlayers();
    	makeAllBetsNotMade();
    	
    }

	public boolean isGameStarted() {
		return isGameStarted;
	}

	public void setGameStarted(boolean isGameStarted) {
		this.isGameStarted = isGameStarted;
		GameTableStats.registerHashPlayersForDatabase(players);
		
	}
	
	private void makeAllBetsNotMade(){
		for (AbstractPlayer p : players){
			RealTablePlayer realPlayer = (RealTablePlayer) p;
			realPlayer.setMadeBet(false);
		}
	}
	
	
	

}
