package net.estebanrodriguez.libs.entity_system.components.characters.stats;

import net.estebanrodriguez.libs.entity_system.components.Component;

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

    private BaseStats mBaseStats;


    private int mDefense;
    private int mDamageReduction;

    public StatsComponent() {
        this(1);
    }

    public StatsComponent(int level, BaseStats baseStats){
        super(COMPONENT_NAME);
        mLevel = level;
        mBaseStats = baseStats;
        setSecondaryStats();
    }


    public StatsComponent(int level){
        super(COMPONENT_NAME);
        mLevel = level;
        setBaseStats();
        setSecondaryStats();
    }

    private void setSecondaryStats(){
        setMaxHealth();
        setMaxPower();
        mCurrentHealth = mMaxHealth;
        mCurrentPower = mMaxPower;
        calculateDefense();
        calculateDamageReduction();
    }

    private void setBaseStats() {

        mBaseStats = StandardBaseStats.getBuilder()
                .build();

        StatPointDistributor distributor = new StatPointDistributor(this);
        distributor.distributeStatPoints();
    }

    public int getTotalNumberOfStatTypes(){
        return mBaseStats.size();
    }

    public Set<StatType> getStatTypes(){
        return mBaseStats.getStatTypesSet();
    }



    private int getStatValue(StatType statType) {
        Stat stat = getStat(statType);
        return stat.getValue();
    }

    private int getStatModifier(StatType statType){
        Stat stat = getStat(statType);
        int statModifier = (int)((stat.getValue() - 5)*.5);
        return statModifier;
    }

    private Stat getStat(StatType statType) {
        if(hasStat(statType)){
            return mBaseStats.get(statType);
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
        stat.increaseValueBy(increase);
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
        return mBaseStats.has(statType);
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