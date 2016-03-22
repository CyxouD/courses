package com.rxn1d.courses;

public class GameTableRealPlayers extends AbstractGameTable {

	private boolean gameStarted = false;
	private boolean firstBetRoundFinished;
	
	
	public boolean addNewPlayer(String player){ 
		if (players.size() >= MAX_PLAYER_NUMBER){
			beginTheSession();
			return false;
		}
		
		String playerName = player.split(" ")[0];
		double playerBalance = Double.parseDouble(player.split(" ")[1]);
		if (playerBalance < 1){
			System.out.println("NEW_USER NOT ACCEPTED");
			System.out.println("Player's balance less than one");
			return false;
		}

		boolean isAdded = players.add(new TablePlayerImpl(playerBalance, playerName));
		if (isAdded){
			System.out.println("New user with name = "+playerName+" and balance = "+playerBalance+" is added to table");
			return true;
		}
		return false;
		
	}

	
	public TablePlayerImpl findPlayerWithName(String name){
		for (AbstractPlayer p : players){
			if (p.getName().equals(name)) return (TablePlayerImpl) p;
		}
		System.out.println("BET NOT ACCEPTED");
		System.out.println("Player with this name "+ name+ " is not registered");
		return null;
	}
	
	public boolean isAllPlayersMadeBet(){
		for (AbstractPlayer p : players){
			TablePlayerImpl realPlayer = (TablePlayerImpl) p;
			if (!realPlayer.isMadeBet() && realPlayer.isInGame()) return false;
		}
		if(!firstBetRoundFinished){
			firstBetRoundFinished = true;
			beginTheSession();
		}
		return true;
	}
	
    public void checkPlayers(){
    	super.checkPlayers();
    	makeAllBetsNotMade();
    	
    }


	public boolean isGameStarted() {
		return gameStarted;
	}

	public void beginTheSession() {
		this.gameStarted = true;
		GameTableStats.registerHashPlayersForDatabase(players);
		
	}
	
	private void makeAllBetsNotMade(){
		for (AbstractPlayer p : players){
			TablePlayerImpl realPlayer = (TablePlayerImpl) p;
			realPlayer.setMadeBet(false);
		}
	}
	
	
	

}
