package com.rxn1d.courses;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Dima on 01.03.2016.
 */
public class CommandProcessor {
    private static Casino casino = new Casino();
    private static GameTableRealPlayers gameTable = casino.getGameTable();
    private static CasinoCash casinoCash = casino.getCasinoCash();



    private  CommandProcessor(){
    }

    public static void start(){
        System.out.println("Game Started");
        System.out.println("Generated Roulette: " +  Arrays.asList(gameTable.getEuropeanRoulette().getRouletteFields()));
    }

    public static boolean parseCommand(String[] command){
        switch (command[0]){
            case(""):
                break;
            case("LOAD"):
                CommandsFromFileLoader.create(command[1]);
                try {
                    String[] line = null;
                    while ((line = CommandsFromFileLoader.getCommand()) != null){
                        parseCommand(line);
                    }

                }catch (CommandsFromFileLoader.LoaderNotReadyException e) {
                    e.printStackTrace();
                }
                break;
            case("NEW_USER"):
                if(gameTable.isGameStarted()){
                    System.out.println("SORRY,"+command[1] +", THE GAME HAS ALREADY STARTED.");
                    break;
                }
                boolean isAdded = gameTable.addNewPlayer(command[1]+ " " +command[2]);
                if (!isAdded){
                    if(gameTable.isGameStarted()){
                        System.out.println("The gameTable is full.Let's start!");
                        break;
                    }
                    else{
                        System.out.println("Current player is already registered");
                    }
                    return true;
                }
                break;

            case("BET"):
                RealTablePlayer player = gameTable.findPlayerWithName(command[1]);
                if (player != null) {
                    String bet;
                    if (command.length == 4)
                        bet = command[2] + " " + command[3];
                    else
                        bet = command[2] + " " + command[3] + " " + command[4];
                    player.makeBet(bet);
                    return true;
                }
                break;

            case("PLAY_GAME"):
                if (gameTable.isAllPlayersMadeBet()){
                    gameTable.spin();
                    gameTable.checkPlayers();
                }
                break;

            case("EXIT"):
                System.out.println("OOPS! GameTable was closed");
                return false;

            case("TEST_START"):
                int nPlayers;
                double balancePlayers;
                double betPlayers;
                nPlayers = Integer.parseInt(command[1]);
                if (nPlayers < 0 || nPlayers > 5) {
                    System.out.println("Number of players must be [0;5]");
                    break;
                }
                balancePlayers = Double.parseDouble(command[2]);
                if (balancePlayers < 1) {
                    System.out.println("Balance of players should be at least more than 1 to make one bet");
                    break;
                }
                betPlayers = Double.parseDouble(command[3]);
                if (betPlayers < 1 || betPlayers > 500) {
                    System.out.println("Bets must be between [1;500]");
                    break;
                }
                GameTableRandomPlayers gameTable = new GameTableRandomPlayers(nPlayers, balancePlayers, betPlayers);
                gameTable.startGame();
                GameTableStats.printStats();
                break;

            case("STATS"):
                GameTableStats.printStats();
                break;

            default:
                System.out.println("THERE IS NO COMMAND: "+command[0]);
                break;

            }
        if (!gameTable.isPlayersHaveEnoughBalance()) return false;
        return true;
        }

    public static void main(String[] args){
    }
}
