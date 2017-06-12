package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.utilities.DiceRoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by spoooon on 4/30/17.
 */

public class StatsComponent extends EntityComponent {

    public static final String COMPONENT_NAME = StatsComponent.class.getSimpleName();

    private int mLevel;

    private int mCurrentHealth;
    private int mCurrentPower;

    private int mMaxHealth;
    private int mMaxPower;

    private Map<StatType, Stat> mStats = new HashMap<>();


    private int mDefense;
    private int mDamageReduction;

    public StatsComponent() {
        this(1);
    }


    public StatsComponent(int level){
        super(COMPONENT_NAME);
        mLevel = level;
        setStats();
        StatPointDistributor distributor = new StatPointDistributor(this);
        distributor.distributeStatPoints();
        setMaxHealth();
        setMaxPower();
        mCurrentHealth = mMaxHealth;
        mCurrentPower = mMaxPower;
        calculateDefense();
        calculateDamageReduction();
    }

    private void setStats() {

        mStats.put(StatType.MUSCLES, new Stat(StatType.MUSCLES));
        mStats.put(StatType.BRAINS, new Stat(StatType.BRAINS));
        mStats.put(StatType.SPEED, new Stat(StatType.SPEED));
        mStats.put(StatType.TOUGHNESS, new Stat(StatType.TOUGHNESS));
    }

    public int getTotalNumberOfStatTypes(){
        return mStats.size();
    }

    public Set<StatType> getStatTypes(){
        return mStats.keySet();
    }



    private int getStatValue(StatType statType) {
        Stat stat = getStat(statType);
        return stat.getValue();
    }

    private int getStatModifier(StatType statType){
        Stat stat = getStat(statType);
        return stat.getModifier();
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

    public void setStatValue(StatType statType, int value){
        Stat stat = getStat(statType);
        stat.setValue(value);
    }

    public int getMuscles(){
        return getStatValue(StatType.MUSCLES);
    }

    public int getBrains(){
        return getStatValue(StatType.BRAINS);
    }

    public int getSpeed(){
        return getStatValue(StatType.SPEED);
    }

    public int getToughness(){
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

    public void setMuscles(int value){
        setStatValue(StatType.MUSCLES, value);
    }

    public void setBrains(int value){
        setStatValue(StatType.BRAINS, value);
    }

    public void setSpeed(int value){
        setStatValue(StatType.SPEED, value);
    }

    public void setToughness(int value){
        setStatValue(StatType.TOUGHNESS, value);
    }

    public void setMaxHealth(int maxHealth) {
        mMaxHealth = maxHealth;
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
        if(getToughness() > 0 && mLevel > 0){
         mMaxHealth = (int)((getToughness() * 5.0) +  (mLevel * 3.0));
        }
    }

    public int getMaxPower() {
        return mMaxPower;
    }

    public void setMaxPower() {
        mMaxPower = (int)((getMuscles() * 2.5)+(getBrains() * 2.5)) * mLevel;
    }




    public int getLevel(){return mLevel;}

    public int getMusclesModifier() {

        return getStatModifier(StatType.MUSCLES);
    }

    public int getBrainsModifier() {
        return getStatModifier(StatType.BRAINS);
    }

    public int getSpeedModifier() {
        return getStatModifier(StatType.SPEED);
    }

    public int getToughnessModifier() {
        return getStatModifier(StatType.TOUGHNESS);
    }


    public int getDefense() {
        return mDefense;
    }

    public int getDamageReduction() {
        return mDamageReduction;
    }


    public void calculateDefense(){
        mDefense = (10 + getSpeedModifier());
    }

    public void calculateDamageReduction(){
        mDamageReduction = (int) (getToughnessModifier() * .5);
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
        stringBuilder.append("Muscles: " + getMuscles() + "\n");
        stringBuilder.append("Brains: " + getBrains() + "\n");
        stringBuilder.append("Speed: " + getSpeed() + "\n");
        stringBuilder.append("Toughness: " + getToughness() + "\n");
        stringBuilder.append("MaxH: " + mMaxHealth + " MaxP: " + mMaxPower + "\n");
        stringBuilder.append("Health: " + mCurrentHealth + " Power: " + mCurrentPower + "\n");
        return stringBuilder.toString();
    }



}
