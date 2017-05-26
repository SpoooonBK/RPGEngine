package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.utilities.DiceRoller;


/**
 * Created by spoooon on 4/30/17.
 */

public class StatsComponent extends EntityComponent {

    private final int TOTAL_NUMBER_OF_STATS = 4;
    public static final String STATS_COMPONENT = "stats_component";


    private LevelComponent mLevel;

    private int mCurrentHealth;
    private int mCurrentPower;

    private int mMaxHealth;
    private int mMaxPower;

    private int mMuscles;
    private int mBrains;
    private int mSpeed;
    private int mToughness;


    private int mTotalStatPoints;

    public StatsComponent() {
        this(new LevelComponent(1), 20);
    }


    public StatsComponent(LevelComponent level, int initialStatPoints){
        super(STATS_COMPONENT);
        distributeStatPoints(initialStatPoints);
        mLevel = level;
        setMaxHealth();
        setMaxPower();
        mCurrentHealth = mMaxHealth;
        mCurrentPower = mMaxPower;
    }

    public void setTotalStatPoints(){
        mTotalStatPoints = mMuscles + mBrains + mSpeed + mToughness;
    }



    public int getCurrentHealth() {
        return mCurrentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        mCurrentHealth = currentHealth;
    }

    public int getCurrentPower() {
        return mCurrentPower;
    }

    public void setCurrentPower(int currentPower) {
        mCurrentPower = currentPower;
    }

    public int getMaxHealth() {
        return mMaxHealth;
    }

    public void setMaxHealth() {
        if(mToughness > 0 && mLevel.getLevel() > 0){
         mMaxHealth = (int)(mToughness * 5.0) * mLevel.getLevel();
        }
    }

    public int getMaxPower() {
        return mMaxPower;
    }

    public void setMaxPower() {
        mMaxPower = (int)((mMuscles * 2.5)+(mBrains * 2.5)) * mLevel.getLevel();
    }

    public int getMuscles() {
        return mMuscles;
    }

    public void setMuscles(int muscles) {
        mMuscles = muscles;
    }

    public int getBrains() {
        return mBrains;
    }

    public void setBrains(int brains) {
        mBrains = brains;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    public int getToughness() {
        return mToughness;
    }

    public void setToughness(int toughness) {
        mToughness = toughness;
    }

    private void distributeStatPoints(int initialStatPoints){

        //Check to see if initialStatPoints are greater than the number of stats
        if(initialStatPoints < TOTAL_NUMBER_OF_STATS){
            initialStatPoints = TOTAL_NUMBER_OF_STATS * 2;
        }

        int base = (int)(initialStatPoints/TOTAL_NUMBER_OF_STATS);
        //vaiance formula can be tweaked
        int variance = (int)(base*3/4);

        int maxPerStat = base + variance;
        int minPerStat = base - variance;
        if(!(minPerStat > 0)){
            minPerStat = 1;
        }


        mMuscles = DiceRoller.rollRandomBetween(minPerStat, maxPerStat);
        mBrains = DiceRoller.rollRandomBetween(minPerStat, maxPerStat);
        mSpeed = DiceRoller.rollRandomBetween(minPerStat, maxPerStat);
        mToughness = DiceRoller.rollRandomBetween(minPerStat, maxPerStat);

        setTotalStatPoints();


        //sets totalStatPoints equal to Initial Stat Points
        while(mTotalStatPoints > initialStatPoints || mTotalStatPoints < initialStatPoints){
            if(mTotalStatPoints > initialStatPoints){
                int difference = mTotalStatPoints - initialStatPoints;
                int count = 0;
                while(count < difference){
                    decrementRandomStat();
                    count ++;
                    setTotalStatPoints();
                }
            }else {
                int difference = initialStatPoints - mTotalStatPoints;
                int count = 0;
                while (count < difference){
                    incrementRandomStat();
                    count ++;
                    setTotalStatPoints();
                }
            }

        }


    }

    public void decrementRandomStat(){

        switch (getRandomStat()){
            case MUSCLES:
                if(mMuscles > 1){
                    mMuscles--;
                }else {
                    decrementRandomStat();
                }
                break;
            case BRAINS:
                if(mBrains > 1){
                    mBrains--;
                }else {
                    decrementRandomStat();
                }
                break;
            case SPEED:
                if(mSpeed > 1){
                    mSpeed--;
                }else {
                    decrementRandomStat();
                }
                break;
            case TOUGHNESS:
                if(mToughness > 1){
                    mToughness--;
                }else {
                    decrementRandomStat();
                }
                break;
        }


    }

    public void incrementRandomStat(){

        switch (getRandomStat()){

            case MUSCLES: mMuscles++;
                break;
            case BRAINS: mBrains++;
                break;
            case SPEED: mSpeed++;
                break;
            case TOUGHNESS: mToughness ++;
                break;
        }

    }

    public StatTypes getRandomStat(){

        int roll = DiceRoller.rollRandomBetween(0, TOTAL_NUMBER_OF_STATS -1);
        return StatTypes.values()[roll];
    }





    public enum StatTypes{
       MUSCLES, BRAINS, SPEED, TOUGHNESS
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LevelComponent: " + mLevel.getLevel() + "\n");
        stringBuilder.append("StatsComponent: " + "\n");
        stringBuilder.append("Muscles: " + mMuscles + "\n");
        stringBuilder.append("Brains: " + mBrains + "\n");
        stringBuilder.append("Speed: " + mSpeed + "\n");
        stringBuilder.append("Toughness: " + mToughness + "\n");
        stringBuilder.append("MaxH: " + mMaxHealth + " MaxP: " + mMaxPower + "\n");
        stringBuilder.append("Health: " + mCurrentHealth + " Power: " + mCurrentPower + "\n");
        return stringBuilder.toString();
    }



}
