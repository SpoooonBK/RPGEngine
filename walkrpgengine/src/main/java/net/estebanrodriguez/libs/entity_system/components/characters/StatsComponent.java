package net.estebanrodriguez.libs.entity_system.components.characters;

import net.estebanrodriguez.libs.entity_system.components.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by spoooon on 4/30/17.
 */

public class StatsComponent extends Component {

    public static final String COMPONENT_NAME = StatsComponent.class.getSimpleName();

    private int mLevel;

    private int mCurrentHealth;
    private int mCurrentPower;

    private int mMaxHealth;
    private int mMaxPower;

    private Map<Stat.StatType, Stat> mStats = new HashMap<>();


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

        mStats.put(Stat.StatType.MUSCLES, new Stat(Stat.StatType.MUSCLES));
        mStats.put(Stat.StatType.BRAINS, new Stat(Stat.StatType.BRAINS));
        mStats.put(Stat.StatType.SPEED, new Stat(Stat.StatType.SPEED));
        mStats.put(Stat.StatType.TOUGHNESS, new Stat(Stat.StatType.TOUGHNESS));
    }

    public int getTotalNumberOfStatTypes(){
        return mStats.size();
    }

    public Set<Stat.StatType> getStatTypes(){
        return mStats.keySet();
    }



    private int getStatValue(Stat.StatType statType) {
        Stat stat = getStat(statType);
        return stat.getValue();
    }

    private int getStatModifier(Stat.StatType statType){
        Stat stat = getStat(statType);
        return stat.getModifier();
    }

    private Stat getStat(Stat.StatType statType) {
        if(hasStat(statType)){
            return mStats.get(statType);
        }else throw new IllegalArgumentException();

    }

    private void incrementStat(Stat.StatType statType){
        Stat stat = getStat(statType);
        stat.incrementValue();
    }

    private void decrementStat(Stat.StatType statType){
        Stat stat = getStat(statType);
        stat.decrementValue();
    }

    private void increaseStatBy(Stat.StatType statType, int increase){
        Stat stat = getStat(statType);
        stat.increaseValueby(increase);
    }

    private void decreaseStatBy(Stat.StatType statType, int decrease){
        Stat stat = getStat(statType);
        stat.decreaseValueBy(decrease);
    }

    public void setStatValue(Stat.StatType statType, int value){
        Stat stat = getStat(statType);
        stat.setValue(value);
    }

    public int getMuscles(){
        return getStatValue(Stat.StatType.MUSCLES);
    }

    public int getBrains(){
        return getStatValue(Stat.StatType.BRAINS);
    }

    public int getSpeed(){
        return getStatValue(Stat.StatType.SPEED);
    }

    public int getToughness(){
        return getStatValue(Stat.StatType.TOUGHNESS);
    }


    public boolean hasStat(Stat.StatType statType){
        return mStats.containsKey(statType);
    }

    public void incrementMuscles(){
        incrementStat(Stat.StatType.MUSCLES);
    }

    public void incrementBrains(){
        incrementStat(Stat.StatType.BRAINS);
    }

    public void incrementSpeed(){
        incrementStat(Stat.StatType.SPEED);
    }

    public void incrementToughness(){
        incrementStat(Stat.StatType.TOUGHNESS);
    }

    public void decrementMuscles(){
        decrementStat(Stat.StatType.MUSCLES);
    }

    public void decrementBrains(){
        decrementStat(Stat.StatType.BRAINS);
    }

    public void decrementSpeed(){
        decrementStat(Stat.StatType.SPEED);
    }

    public void decrementToughness(){
        decrementStat(Stat.StatType.TOUGHNESS);
    }

    public void increaseMuscles(int increase){
        increaseStatBy(Stat.StatType.MUSCLES, increase);
    }

    public void increaseBrains(int increase){
        increaseStatBy(Stat.StatType.BRAINS, increase);
    }

    public void increaseSpeed(int increase){
        increaseStatBy(Stat.StatType.SPEED, increase);
    }

    public void increaseToughness(int increase){
        increaseStatBy(Stat.StatType.TOUGHNESS, increase);
    }

    public void decreaseMuscles(int decrease){
        decreaseStatBy(Stat.StatType.MUSCLES, decrease);
    }

    public void decreaseBrains(int decrease){
        decreaseStatBy(Stat.StatType.BRAINS, decrease);
    }

    public void decreaseSpeed(int decrease){
        decreaseStatBy(Stat.StatType.SPEED, decrease);
    }

    public void decreaseToughness(int decrease){
        decreaseStatBy(Stat.StatType.TOUGHNESS, decrease);
    }

    public void setMuscles(int value){
        setStatValue(Stat.StatType.MUSCLES, value);
    }

    public void setBrains(int value){
        setStatValue(Stat.StatType.BRAINS, value);
    }

    public void setSpeed(int value){
        setStatValue(Stat.StatType.SPEED, value);
    }

    public void setToughness(int value){
        setStatValue(Stat.StatType.TOUGHNESS, value);
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

        return getStatModifier(Stat.StatType.MUSCLES);
    }

    public int getBrainsModifier() {
        return getStatModifier(Stat.StatType.BRAINS);
    }

    public int getSpeedModifier() {
        return getStatModifier(Stat.StatType.SPEED);
    }

    public int getToughnessModifier() {
        return getStatModifier(Stat.StatType.TOUGHNESS);
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
