package com.rxn1d.courses;

import java.util.Arrays;
public class RouletteStarter {
	
	public static void main(String[] args){
		printRules();
		String[] in;
        CommandProcessor.start();
		while (true) {
			in = ConsoleReader.readFromConsole();
			if(!CommandProcessor.parseCommand(in)) break;
		}
	}

    private static void printRules(){
        System.out.println("Type \"EXIT\" to finish the application");
        System.out.println("Use \"<NEW_USER>< ><YOUR_NAME>< ><YOUR_BALANCE>\" to register yourself for the game");
        System.out.println("Use \"<LOAD>< ><PATH_TO_YOUR_FILE\" to load commands from file(\"inputOK.txt\" to check)");
        System.out.println("Use \"<BET>< ><YOUR_NAME>< ><BET>< ><BET_TYPE>\" to make a bet");
        System.out.println("Bet types: [RED,BLACK,ODD,EVEN,SMALL,BIG,STRAIGHT_UP <YOUR_NUMBER>]");
        System.out.println("Bet between [1;500]");
        System.out.println("For the organisator use \"PLAY_GAME\" to start the game\n");
    }
}
		

