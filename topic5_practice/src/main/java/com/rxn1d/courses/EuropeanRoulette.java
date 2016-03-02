package com.rxn1d.courses;

/**
 * Created by Dima on 02.03.2016.
 */
public class EuropeanRoulette {
    private static RouletteField ball;
    private static RouletteField[] rouletteFields;

    public RouletteField getBall() {
        return ball;
    }

    public static void setBall(RouletteField ball) {
        EuropeanRoulette.ball = ball;
    }

    public static void setRouletteFields(RouletteField[] rouletteFields) {
        EuropeanRoulette.rouletteFields = rouletteFields;
    }

    public RouletteField[] getRouletteFields() {
        return rouletteFields;
    }
}
