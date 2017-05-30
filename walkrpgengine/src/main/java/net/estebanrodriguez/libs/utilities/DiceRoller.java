package net.estebanrodriguez.libs.utilities;

import java.util.Random;

/**
 * Created by spoooon on 5/24/17.
 */

public class DiceRoller {

    private static Random sRandom = new Random();


    public static int rollDie(Dice die){
        return rollRandomBetween(die.getMin(), die.getMax());
    }


    public static int rollRandomBetween(int min, int max){

        return sRandom.nextInt((max - min) + 1) + min;
    }




}
