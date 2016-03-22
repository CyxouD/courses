package com.rxn1d.courses;

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
                processLoadCommand(command[1]);
                break;
            case("NEW_USER"):
                if (processNewUserCommand(command[1], command[2])) return true;
                break;
            case("BET"):
                if (processNewBetCommand(command)) return true;
                break;
            case("PLAY_GAME"):
                processPlayGameCommand();
                break;
            case("EXIT"):
                processExitCommand();
                return false;
            case("TEST_START"):
                processTestCommand(command[1], command[2], command[3]);
                break;
            case("STATS"):
                processStatsCommand();
                break;
            default:
                processIllegalCommand(command[0]);
                break;
            }
        if (!gameTable.isPlayersHaveEnoughBalance()) return false;
        return true;
        }

    private static void processLoadCommand(String path){
        CommandsFromFileLoader.create(path);
        try {
            String[] line = null;
            while ((line = CommandsFromFileLoader.getCommand()) != null){
                parseCommand(line);
            }

        }catch (CommandsFromFileLoader.LoaderNotReadyException e) {
            e.printStackTrace();
        }
    }

    private static boolean processNewUserCommand(String playerName, String playerBalance){
        if(gameTable.isGameStarted()){
            System.out.println("SORRY,"+playerName +", THE GAME HAS ALREADY STARTED.");
            return false;
        }
        boolean isAdded = gameTable.addNewPlayer(playerName+ " " +playerBalance);
        if (!isAdded){
            if(gameTable.isGameStarted()){
                System.out.println("The gameTable is full.Let's start!");
                return false;
            }
            else{
                System.out.println("Current player is already registered");
            }
        }
        return true;
    }

    private static boolean processNewBetCommand(String[] betData){
        TablePlayerImpl player = gameTable.findPlayerWithName(betData[1]);
        if (player != null) {
            String bet;
            if (betData.length == 4)
                bet = betData[2] + " " + betData[3];
            else
                bet = betData[2] + " " + betData[3] + " " + betData[4];
            player.makeBet(bet);
            return true;
        }
        return false;
    }

    private static void processPlayGameCommand(){
        if (gameTable.isAllPlayersMadeBet()){
            gameTable.spin();
            gameTable.checkPlayers();
        }
    }

    private static void processExitCommand(){
        System.out.println("OOPS! GameTable was closed");
    }

    private static void processTestCommand(String playersNumber, String playersBalance, String playersBet){
        int nPlayers;
        double balancePlayers;
        double betPlayers;
        nPlayers = Integer.parseInt(playersNumber);
        balancePlayers = Double.parseDouble(playersBalance);
        betPlayers = Double.parseDouble(playersBet);
        if (!processOnTestErrors(nPlayers, balancePlayers, betPlayers)) return; // incorrect info
        GameTableRandomPlayers gameTable = new GameTableRandomPlayers(nPlayers, balancePlayers, betPlayers);
        gameTable.startGame();
        GameTableStats.printStats();
    }

    private static boolean processOnTestErrors(int playersNumber, double playersBalance, double playersBet){
        if (playersNumber < AbstractGameTable.MIN_PLAYER_NUMBER || playersNumber > AbstractGameTable.MAX_PLAYER_NUMBER) {
            System.out.println("Number of players must be [0;5]");
            return false;
        }
        if (playersBalance < AbstractPlayer.MIN_PLAYER_BET) {
            System.out.println("Balance of players should be at least more than 1 to make one bet");
            return false;
        }
        if (playersBet < AbstractPlayer.MIN_PLAYER_BET || playersBet > AbstractPlayer.MAX_PLAYER_BET) {
            System.out.println("Bets must be between [1;500]");
            return false;
        }
        return true;
    }

    private static void processStatsCommand(){
        GameTableStats.printStats();
    }

    private static void processIllegalCommand(String command){
        System.out.println("THERE IS NO COMMAND: "+command);
    }

    public static void main(String[] args){
    }
}
