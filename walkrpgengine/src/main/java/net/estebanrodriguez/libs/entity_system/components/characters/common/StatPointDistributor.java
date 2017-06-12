package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.utilities.DiceRoller;
import net.estebanrodriguez.libs.utilities.Die;
import net.estebanrodriguez.libs.utilities.Roll;
import net.estebanrodriguez.libs.utilities.RollTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by spoooon on 6/10/17.
 */

public final class StatPointDistributor {

    private final double VARIANCE_MULTIPLIER = .75;

    private int sBaseStatPoints;
    private StatsComponent mStatsComponent;
    private int mInitialStatPoints;
    private int mMaximumInitialStatPointsPerStat;
    private int mMinimumInitialStatPointPerStat;
    private int mTotalNumberOfStatTypes;
    private int mStatPointVariance;


    public StatPointDistributor(StatsComponent statsComponent) {
        mStatsComponent = statsComponent;
        mTotalNumberOfStatTypes = statsComponent.getTotalNumberOfStatTypes();
        setBaseStatPoints();
        setInitialStatPoints();
        setStatPointVariance();
        setMaximumInitialStatPointsPerStat();
        setMaximumInitialStatPointsPerStat();
    }


    public void distributeStatPoints(){
        RollTracker rollTracker = rollUntilTotalStatPointsSpent();
        List<Integer> rolls = rollTracker.getValues();
        int count = 0;
        Set<StatType> statTypes = mStatsComponent.getStatTypes();
        for(StatType statType: statTypes){
            mStatsComponent.setStatValue(statType, rolls.get(count));
            count ++;
        }
    }

    private RollTracker rollUntilTotalStatPointsSpent(){
        int sumOfRolls = 0;
        RollTracker rollTracker = null;
        while(sumOfRolls != mInitialStatPoints){
            rollTracker = rollForEachStatType();
            sumOfRolls = rollTracker.getSumOfRolls();
        }
        return rollTracker;
    }

    private RollTracker rollForEachStatType(){
        RollTracker rollTracker = new RollTracker();
        Die die = buildDie();
        for(int i = 0; i < mTotalNumberOfStatTypes; i++){
            rollTracker.addRoll(new Roll(die));
        }
        return rollTracker;
    }


    private Die buildDie(){
        return new Die(mMinimumInitialStatPointPerStat, mMaximumInitialStatPointsPerStat);
    }

    private void setInitialStatPoints() {
        int level = mStatsComponent.getLevel();
      mInitialStatPoints = sBaseStatPoints + (level - 1);
    }

    private void setBaseStatPoints(){
        int minimumStatPoints = mStatsComponent.getTotalNumberOfStatTypes() * 2;
            if(minimumStatPoints > 20){
                sBaseStatPoints = minimumStatPoints;
            }else sBaseStatPoints = 20;
    }


    private int getAverageStatPointsPerStat(){
        return (mInitialStatPoints / mTotalNumberOfStatTypes);
    }

    private void setStatPointVariance(){
        mStatPointVariance = (int)(getAverageStatPointsPerStat() * VARIANCE_MULTIPLIER);
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
}
