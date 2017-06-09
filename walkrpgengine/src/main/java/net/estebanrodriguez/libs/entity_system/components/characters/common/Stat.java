package net.estebanrodriguez.libs.entity_system.components.characters.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 6/9/17.
 */

public class Stat {

    private final StatType mStatType;
    private final int MINIMUM_VALUE = 1;
    private int mValue;
    private List<OnStatChangeListener> mStatChangeListeners = new ArrayList<>();

    public Stat(StatType statType) {
        mStatType = statType;
    }

    public StatType getStatType() {
        return mStatType;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        if(mValue > 0){
            mValue = value;
        } else mValue = MINIMUM_VALUE;
    }

    public void incrementValue(){
        mValue++;
    }

    public void decrementValue(){
        if(mValue - 1 >  0){
            mValue--;
        }
    }

    public void increaseValueby(int increase){
        mValue = mValue + increase;
    }

    public void decreaseValueBy(int decrease){
        mValue = mValue - decrease;
        if(mValue < MINIMUM_VALUE){
            mValue = MINIMUM_VALUE;
        }
    }
}
