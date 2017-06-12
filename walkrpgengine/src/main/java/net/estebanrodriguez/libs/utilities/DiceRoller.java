package net.estebanrodriguez.libs.utilities;

import java.util.Random;

/**
 * Created by spoooon on 5/24/17.
 */

public class DiceRoller {

    private static Random sRandom = new Random();


    public static int rollDie(Die die){
        return rollRandomInt(die.getMinimum(), die.getMaximum());
    }


    public static int rollRandomInt(int min, int max){

        return sRandom.nextInt((max - min)) + min;
    }




}
