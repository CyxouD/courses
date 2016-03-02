package com.rxn1d.courses;

/**
 * Created by Dima on 02.03.2016.
 */
public class CasinoCash {
    public static void getResult(RouletteField ball, AbstractPlayer p){
            String playerBetType = p.getBetType().getBetType();
            switch (playerBetType) {
                case "RED":
                    if (BetType.wonColor("RED",playerBetType)) won1by1(p);
                    else lost(p);
                    break;
                case "BLACK":
                    if (BetType.wonColor("BLACK",playerBetType)) won1by1(p);
                    else lost(p);
                    break;
                case "ODD":
                    if (BetType.wonParity("ODD",ball.getNumber())) won1by1(p);
                    else lost(p);
                    break;
                case "EVEN":
                    if (BetType.wonParity("EVEN",ball.getNumber())) won1by1(p);
                    else lost(p);
                    break;
                case "SMALL":
                    if (BetType.wonInterval("SMALL",ball.getNumber())) won1by1(p);
                    else lost(p);
                    break;
                case "BIG":
                    if (BetType.wonInterval("BIG",ball.getNumber())) won1by1(p);
                    else lost(p);
                    break;

                default:
                    if (BetType.wonNumber(ball.getNumber(), playerBetType)) won1by35(p);
                    else lost(p);
                    break;
            }
    }


    private static void won1by1(AbstractPlayer p){
        p.setBalance(p.getBalance() + p.getBet());
        GameTableStats.updateCasinoBalance(-p.getBet());
        printPlayerWon(p);
    }

    private static void won1by35(AbstractPlayer p){
        p.setBalance(p.getBalance() + 35 * p.getBet());
        GameTableStats.updateCasinoBalance(- 35 * p.getBet());
        printPlayerWon(p);
    }

    private static void lost(AbstractPlayer p){
        p.setBalance(p.getBalance() - p.getBet());
        GameTableStats.updateCasinoBalance(p.getBet());
        printPlayerLost(p);
    }

    private static void printPlayerWon(AbstractPlayer p){
        System.out.println("PLAYER: " + p.getName() +" +"+ p.getBet() );
    }

    private static void printPlayerLost(AbstractPlayer p){
        System.out.println("PLAYER: " + p.getName() +" -"+ p.getBet() );
    }
}
