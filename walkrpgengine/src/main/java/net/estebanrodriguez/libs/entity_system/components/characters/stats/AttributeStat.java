package net.estebanrodriguez.libs.entity_system.components.characters.stats;

import net.estebanrodriguez.libs.entity_system.components.characters.OnStatChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 6/9/17.
 */

public class AttributeStat implements Stat {

    private final StatType mStatType;
    private final int MINIMUM_VALUE = 1;
    private int mValue;
    private List<OnStatChangeListener> mStatChangeListeners = new ArrayList<>();

    public AttributeStat(StatType statType) {
        mStatType = statType;
    }

    public AttributeStat(StatType statType, int value){
        mStatType = statType;
        setValue(value);
    }

    public StatType getStatType() {
        return mStatType;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        if(value > 0){
            mValue = value;
        } else mValue = MINIMUM_VALUE;
        notifyListeners();
    }

    public void incrementValue(){
        mValue++;
        notifyListeners();
    }

    public void decrementValue(){
        if(mValue - 1 >  0){
            mValue--;
        }
        notifyListeners();
    }



    public void increaseValueBy(int increase){
        mValue = mValue + increase;
        notifyListeners();
    }

    public void decreaseValueBy(int decrease){
        mValue = mValue - decrease;
        if(mValue < MINIMUM_VALUE){
            mValue = MINIMUM_VALUE;
        }
        notifyListeners();
    }

    private void notifyListeners(){
        for(OnStatChangeListener listener: mStatChangeListeners){
            listener.onStatChange();
        }

    }

}
