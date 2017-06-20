package net.estebanrodriguez.libs.entity_system.components.characters.stats.expendable;

import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.Stat;

/**
 * Created by spoooon on 6/19/17.
 */

public class GenericStat implements Stat {

    private int mValue = 0;
    private int mMinimumValue = 0;
    private int mMaximumValue = 100;
    private StatName mStatName;

    public GenericStat(StatName statName) {
        mStatName = statName;
    }
    public GenericStat(StatName statName, int value){
        mStatName= statName;
        setValue(value);
    }

    public void setMaximumValue(int maxValue) {
        if(maxValue > mMinimumValue){
            mMaximumValue = maxValue;
        }
    }

    @Override
    public void setMinimumValue(int value) {
        if(value >= 0 && value < mMaximumValue){
            mMinimumValue = value;
        }
    }


    @Override
    public int value() {
        return mValue;
    }

    @Override
    public void setValue(int value) {
        mValue = value;
        checkValue();

    }


    @Override
    public void incrementValue() {
        mValue++;
        checkValue();
    }

    @Override
    public void decrementValue() {
        mValue--;
        checkValue();
    }

    @Override
    public void increaseValueBy(int value) {
        if(value > 0){
            mValue = mValue + value;
            checkValue();
        }
    }

    @Override
    public void decreaseValueBy(int value) {
        if(value < 0){
            mValue = mValue - value;
            checkValue();
        }
    }

    @Override
    public StatName getStatName() {
        return mStatName;
    }

    @Override
    public int getMaximum() {
        return mMaximumValue;
    }

    @Override
    public int getMinimum() {
        return mMinimumValue;
    }


    private boolean isBelowMinimum(){
        return mValue < mMinimumValue;
    }

    private boolean isAboveMaximum(){
        return mValue > mMaximumValue;
    }

    private void setToMaximum() {
            mValue = mMaximumValue;
    }

    private void setToMinimum(){
        mValue = mMinimumValue;
    }

    private void checkValue() {
        if(isBelowMinimum()){
            setToMinimum();
        }
        if(isAboveMaximum()){
            setToMaximum();
        }
    }

}
