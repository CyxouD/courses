package com.rxn1d.courses;

import java.util.Arrays;
public class RouletteStarter {
	
	public static void main(String[] args){
		
		String[] in;
		while (true){
		System.out.println("Enter \"TEST\" if you want to run a test version with random players/\n"
				+ "Else enter \"REAL\" if you want it to be it realistic ");

		in = ConsoleReader.readFromConsole();
		if (in.length == 0) continue;
		else if (in[0].equals("REAL")){
            System.out.println("Type \"EXIT\" to finish the application");
            System.out.println("Use \"<NEW_USER>< ><YOUR_NAME>< ><YOUR_BALANCE>\" to register yourself for the game");
            System.out.println("Use \"<BET>< ><YOUR_NAME>< ><BET>< ><BET_TYPE>\" to make a bet");
            System.out.println("Bet types: [RED,BLACK,ODD,EVEN,SMALL,BIG,STRAIGHT_UP <YOUR_NUMBER>]");
            System.out.println("Bet between [1;500]");
            System.out.println("For the organisator use \"PLAY_GAME\" to start the game");
			GameTableRealPlayers gt = new GameTableRealPlayers();
			
			while (true){
				 System.out.println("Game Started");
				 System.out.println(Arrays.asList(gt.getRouletteFields()));
				 while(true){
				 in = ConsoleReader.readFromConsole();
				 if(in.length == 0) {
					 System.out.println("Incorrect input");
					 continue;
				 }
				 else if (in[0].equals("EXIT")){
				 	System.out.println("GameTable was closed");
				 	return;
				 }
				 else if (in[0].equals("NEW_USER")){
					 boolean isAdded = gt.addNewPlayer(in[1]+ " " +in[2]);
					 if (!isAdded){
						 if(gt.isGameStarted()){
							 System.out.println("The gameTable is full.Let's start!");
						 	break;
						 }
						 else{
							 System.out.println("Current player is already registered");
						 }
					 }
				 }
				 else if (in[0].equals("BET")){
					 System.out.println("The game not started yet");
				 }
				 else if (in[0].equals("PLAY_GAME")){
					 System.out.println("The game has started");
					 gt.setGameStarted(true);
					 break;
				 }
				 else{
					 System.out.println("Incorrect input");
					 continue;
				 }
				 }
				 
				 while (gt.isPlayersHaveEnoughBalance()){
					 System.out.println("Ladies and gentelmen, make your bets");
					 while (!gt.isAllPlayersMadeBet()){
						in = ConsoleReader.readFromConsole();
					 	if (in[0].equals("EXIT")){
					 		System.out.println("GameTable was closed");
					 		return;
					 	}
					 	else if(in[0].equals("STATS")){
					 		GameTableStats.printStats();
					 		continue;
					 	}
					 	else if(in[0].equals("BET")){
					 		RealTablePlayer player = gt.findPlayerWithName(in[1]);
					 		if (player != null){
					 				String bet;
					 				if (in.length == 4)
					 					bet = in[2] +" "+ in[3];
					 				else
					 					bet = in[2] +" "+ in[3] +" "+in[4];
					 				player.makeBet(bet);
					 			
					 		}
					 	}
					 	else{
					 		System.out.println("");
							continue;
					 	}
					 }
				 	 gt.spin();
				 	 gt.checkPlayers();
					 }
				 System.out.println("Sorry, all players are bankrupts");
				 GameTableStats.printStats();
				 break;
			 }
		}
		else if (in[0].equals("TEST")){
			System.out.println("Enter \"TEST_START <n_Players>< ><players_Balance>< ><Players_bet>\"");
			in = ConsoleReader.readFromConsole();
			if (in[0].equals("TEST_START")){
				GameTableRandomPlayers gameTable = new GameTableRandomPlayers(Integer.parseInt(in[1]),Double.parseDouble(in[2]), Double.parseDouble(in[3]));
				gameTable.startGame();
				GameTableStats.printStats();
			}
			else{
				System.out.println("Incorrect input.Try again");
			}
		}
		else{
			System.out.println("Incorrect input.Try again");
		}
    	
			 
		}
	}
}
		

