package com.rxn1d.courses;

/**
 * Created by Dima on 02.03.2016.
 */
public class Casino {
    private static CasinoCash casinoCash = new CasinoCash();
    private static GameTableRealPlayers gameTable = new GameTableRealPlayers();

    public CasinoCash getCasinoCash(){
        return casinoCash;
    }

    public GameTableRealPlayers getGameTable(){
        return gameTable;
    }
}
