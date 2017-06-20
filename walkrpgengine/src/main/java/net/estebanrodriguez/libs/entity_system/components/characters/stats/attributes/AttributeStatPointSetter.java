package net.estebanrodriguez.libs.entity_system.components.characters.stats.attributes;

import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.Stat;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.StatPointSetter;
import net.estebanrodriguez.libs.utilities.DiceRoller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 6/10/17.
 */

public final class AttributeStatPointSetter implements StatPointSetter {



    private StatComponent mStatComponent;
    private List<Stat> mAttributes;
    private int mTotalStatPoints;

    public AttributeStatPointSetter(StatComponent statComponent) {

        mStatComponent = statComponent;
        setAttributeList();
        setTotalStatPoints();
    }

    private void setAttributeList(){
        mAttributes = new ArrayList<>();
        List<Stat> statList = mStatComponent.getBaseStats().getStatList();
        for(Stat stat: statList){
            if(isAttribute(stat)){
                mAttributes.add(stat);
            }
        }
    }

    @Override
    public void setPoints(){
            while(getTotalPointsSpent() != mTotalStatPoints ){
                incrementRandomStat();
            }
    }

    private void incrementRandomStat() {
        Stat stat = getRandomStat();
        stat.incrementValue();
    }

    public Stat getRandomStat() {
        int roll = DiceRoller.rollRandomInt(0, (mAttributes.size()-1));
        return mAttributes.get(roll);
    }

    private boolean isAttribute(Stat stat) {
        StatName.StatType statType = stat.getStatName().getStatType();
        return statType == StatName.StatType.ATTRIBUTE;
    }

    private void setTotalStatPoints() {
        int level = mStatComponent.getLevel();
        int minimumStatPoints = mAttributes.size() * 2;
        if(minimumStatPoints < 20){
            minimumStatPoints = 20;
        }
      mTotalStatPoints = minimumStatPoints + level;
    }

    private int getTotalPointsSpent(){
        int totalPoints = 0;
        for(Stat stat: mAttributes){
            totalPoints = totalPoints + stat.value();
        }
        return totalPoints;
    }


}
