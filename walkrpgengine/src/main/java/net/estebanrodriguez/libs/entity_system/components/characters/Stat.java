package net.estebanrodriguez.libs.entity_system.components.characters;

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

    public int getModifier() {
        return (int)((mValue - 5)*.5);
    }


    public void increaseValueby(int increase){
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

    /**
     * Created by spoooon on 6/9/17.
     */

    public static enum StatType {
        MUSCLES, BRAINS, SPEED, TOUGHNESS
    }
}
