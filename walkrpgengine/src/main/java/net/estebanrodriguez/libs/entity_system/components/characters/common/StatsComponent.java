package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.utilities.DiceRoller;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by spoooon on 4/30/17.
 */

public class StatsComponent extends EntityComponent {

    public static final String COMPONENT_NAME = StatsComponent.class.getSimpleName();
    private final int BASE_STAT_POINTS = 20;
    private final double VARIANCE_MULTIPLIER = 3/4;

    private final int TOTAL_NUMBER_OF_STAT_TYPES = StatTypes.values().length;




    private int mLevel;

    private int mCurrentHealth;
    private int mCurrentPower;

    private int mMaxHealth;
    private int mMaxPower;

    private Map<StatType, Stat> mStats = new HashMap<>();

    private Stat mMuscles;
    private Stat mBrains;
    private Stat mSpeed;
    private Stat mToughness;

    private int mMusclesModifier;
    private int mBrainsModifier;
    private int mSpeedModifier;
    private int mToughnessModifier;

    private int mDefense;
    private int mDamageReduction;
    private int mInitialStatPoints;
    private int mMinimumInitialStatPoints;

    private int mMaximumInitialStatPointsPerStat;
    private int mMinimumInitialStatPointPerStat;
    private int mStatPointVariance;



    private int mTotalStatPoints;

    public StatsComponent() {
        this(1);
    }


    public StatsComponent(int level){
        super(COMPONENT_NAME);
        int initialStatPoints = 20 + level - 1;
        distributeStatPoints(initialStatPoints);
        mLevel = level;
        setMaxHealth();
        setMaxPower();
        mCurrentHealth = mMaxHealth;
        mCurrentPower = mMaxPower;
        calculateModifiers();
        calculateDefense();
        calculateDamageReduction();
    }



    private int getStatValue(StatType statType) {
        Stat stat = getStat(statType);
        return stat.getValue();
    }

    private Stat getStat(StatType statType) {
        if(hasStat(statType)){
            return mStats.get(statType);
        }else throw new IllegalArgumentException();

    }

    private void incrementStat(StatType statType){
        Stat stat = getStat(statType);
        stat.incrementValue();
    }

    private void decrementStat(StatType statType){
        Stat stat = getStat(statType);
        stat.decrementValue();
    }

    private void increaseStatBy(StatType statType, int increase){
        Stat stat = getStat(statType);
        stat.increaseValueby(increase);
    }

    private void decreaseStatBy(StatType statType, int decrease){
        Stat stat = getStat(statType);
        stat.decreaseValueBy(decrease);
    }

    private void setStatValue(StatType statType, int value){
        Stat stat = getStat(statType);
        stat.setValue(value);
    }

    public int getMusclesValue(){
        return getStatValue(StatType.MUSCLES);
    }

    public int getBrainsValue(){
        return getStatValue(StatType.BRAINS);
    }

    public int getSpeedValue(){
        return getStatValue(StatType.SPEED);
    }

    public int getToughnessValue(){
        return getStatValue(StatType.TOUGHNESS);
    }

    public boolean hasStat(StatType statType){
        return mStats.containsKey(statType);
    }

    public void incrementMuscles(){
        incrementStat(StatType.MUSCLES);
    }

    public void incrementBrains(){
        incrementStat(StatType.BRAINS);
    }

    public void incrementSpeed(){
        incrementStat(StatType.SPEED);
    }

    public void incrementToughness(){
        incrementStat(StatType.TOUGHNESS);
    }

    public void decrementMuscles(){
        decrementStat(StatType.MUSCLES);
    }

    public void decrementBrains(){
        decrementStat(StatType.BRAINS);
    }

    public void decrementSpeed(){
        decrementStat(StatType.SPEED);
    }

    public void decrementToughness(){
        decrementStat(StatType.TOUGHNESS);
    }

    public void increaseMuscles(int increase){
        increaseStatBy(StatType.MUSCLES, increase);
    }

    public void increaseBrains(int increase){
        increaseStatBy(StatType.BRAINS, increase);
    }

    public void increaseSpeed(int increase){
        increaseStatBy(StatType.SPEED, increase);
    }

    public void increaseToughness(int increase){
        increaseStatBy(StatType.TOUGHNESS, increase);
    }

    public void decreaseMuscles(int decrease){
        decreaseStatBy(StatType.MUSCLES, decrease);
    }

    public void decreaseBrains(int decrease){
        decreaseStatBy(StatType.BRAINS, decrease);
    }

    public void decreaseSpeed(int decrease){
        decreaseStatBy(StatType.SPEED, decrease);
    }

    public void decreaseToughness(int decrease){
        decreaseStatBy(StatType.TOUGHNESS, decrease);
    }

    public void setMusclesValue(int value){
        setStatValue(StatType.MUSCLES, value);
    }

    public void setBrainsValue(int value){
        setStatValue(StatType.BRAINS, value);
    }

    public void setSpeedValue(int value){
        setStatValue(StatType.SPEED, value);
    }

    public void setToughnessValue(int value){
        setStatValue(StatType.TOUGHNESS, value);
    }



    public void setMaxHealth(int maxHealth) {

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
        if(getToughnessValue() > 0 && mLevel > 0){
         mMaxHealth = (int)((getToughnessValue() * 5.0) +  (mLevel * 3.0));
        }
    }

    public int getMaxPower() {
        return mMaxPower;
    }

    public void setMaxPower() {
        mMaxPower = (int)((mMuscles * 2.5)+(mBrains * 2.5)) * mLevel;
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


    public int getLevel(){return mLevel;}

    public int getMusclesModifier() {
        return mMusclesModifier;
    }

    public int getBrainsModifier() {
        return mBrainsModifier;
    }

    public int getSpeedModifier() {
        return mSpeedModifier;
    }

    public int getToughnessModifier() {
        return mToughnessModifier;
    }

    public int getTotalStatPoints() {
        return mTotalStatPoints;
    }


    public int getDefense() {
        return mDefense;
    }

    public int getDamageReduction() {
        return mDamageReduction;
    }

    private void setMinimunInitialStatPoints(){
        mMinimumInitialStatPoints = TOTAL_NUMBER_OF_STAT_TYPES * 2;
    }


    private void setInitialStatPoints(){
        int statPoints = BASE_STAT_POINTS + (mLevel -1);
        if(statPoints >= mMinimumInitialStatPoints){
            mInitialStatPoints = statPoints;
        }else mInitialStatPoints = mMinimumInitialStatPoints;
    }

    private int getAverageStatPointsPerStat(){
        return (mInitialStatPoints/TOTAL_NUMBER_OF_STAT_TYPES);
    }

    private void setStatPointVariance(){
        mStatPointVariance = (int)(getAverageStatPointsPerStat() * VARIANCE_MULTIPLIER);
    }

    private void setStatPointDistributionRange(){
        setMaximumInitialStatPointsPerStat();
        setMinimumInitialStatPointPerStat();
    }

    private void setMaximumInitialStatPointsPerStat(){
        int average = getAverageStatPointsPerStat();
        mMaximumInitialStatPointsPerStat = average + mStatPointVariance;
    }

    private void setMinimumInitialStatPointPerStat(){
        int average = getAverageStatPointsPerStat();
        mMinimumInitialStatPointPerStat = average - mStatPointVariance;
        if(mMinimumInitialStatPointPerStat < 1){
            mMinimumInitialStatPointPerStat = 1;
        }
    }


    private void distributeStatPoints(){
        mTotalStatPoints = 0;
        while(mTotalStatPoints != mInitialStatPoints){
            rollInitialStats();
            calculateTotalStatPoints();
        }
    }

    private void rollInitialStats(){
        for(Stat stat: mStats.values()){
            int value = DiceRoller.rollRandomInt(mMinimumInitialStatPointPerStat, mMaximumInitialStatPointsPerStat);
            setStatValue(stat.getStatType(), value);
        }
    }

    public void calculateTotalStatPoints(){
        int total = 0;
        for(Stat stat: mStats.values()){
            total = total + stat.getValue();
        }
        mTotalStatPoints = total;
    }


    private void distributeStatPoints(int initialStatPoints){


        mMuscles = DiceRoller.rollRandomInt(minPerStat, maxPerStat);
        mBrains = DiceRoller.rollRandomInt(minPerStat, maxPerStat);
        mSpeed = DiceRoller.rollRandomInt(minPerStat, maxPerStat);
        mToughness = DiceRoller.rollRandomInt(minPerStat, maxPerStat);

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



    public StatTypes getRandomStat(){

        int roll = DiceRoller.rollRandomInt(0, TOTAL_NUMBER_OF_STATS -1);
        return StatTypes.values()[roll];
    }

    public void calculateModifiers(){
        mMusclesModifier = (int)((mMuscles - 5)*.5);
        mBrainsModifier = (int)((mBrains - 5)*.5);
        mSpeedModifier = (int)((mSpeed - 5)*.5);
        mToughnessModifier = (int)((mToughness - 5)*.5);
    }

    public void calculateDefense(){
        mDefense = (10 + mSpeedModifier);
    }

    public void calculateDamageReduction(){
        mDamageReduction = (int) (mToughnessModifier * .5);
    }

    public void decrementHealth(int damage){
        mCurrentHealth = mCurrentHealth - damage;
    }

    public void addHealth(int healing){
        int potentialHealth = mCurrentHealth + healing;
        if(potentialHealth >= mMaxHealth){
            mCurrentHealth = mMaxHealth;
        }else {
            mCurrentHealth = potentialHealth;
        }
    }


    public enum StatTypes{
       MUSCLES, BRAINS, SPEED, TOUGHNESS
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Level: " + mLevel + "\n");
        stringBuilder.append("Muscles: " + mMuscles + "\n");
        stringBuilder.append("Brains: " + mBrains + "\n");
        stringBuilder.append("Speed: " + mSpeed + "\n");
        stringBuilder.append("Toughness: " + mToughness + "\n");
        stringBuilder.append("MaxH: " + mMaxHealth + " MaxP: " + mMaxPower + "\n");
        stringBuilder.append("Health: " + mCurrentHealth + " Power: " + mCurrentPower + "\n");
        return stringBuilder.toString();
    }



}
