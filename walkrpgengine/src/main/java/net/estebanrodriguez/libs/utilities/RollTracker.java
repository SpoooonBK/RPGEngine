package net.estebanrodriguez.libs.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by spoooon on 5/30/17.
 */

public class RollTracker {

    private List<Roll> mRolls = new ArrayList<>();

    public RollTracker() {
    }

    public List<Roll> getRolls() {
        return mRolls;
    }

    public List<Integer> getValues() {

        List<Integer> values = new ArrayList<>();
        for (Roll roll : mRolls) {
            values.add(roll.getValue());
        }
        return values;
    }

    public void addRoll(Roll roll) {
        mRolls.add(roll);
    }

    public void sortByHighestToLowest() {

        Collections.sort(mRolls, new Comparator<Roll>() {
            @Override
            public int compare(Roll roll, Roll t1) {
                int leftRoll = roll.getValue();
                int rightRoll = t1.getValue();

                if (leftRoll == rightRoll) {
                    return 0;
                } else if (leftRoll > rightRoll) {
                    return 1;
                } else return -1;
            }
        });
    }

    public void sortByLowest() {

        Collections.sort(mRolls, new Comparator<Roll>() {
            @Override
            public int compare(Roll roll, Roll t1) {
                int leftRoll = roll.getValue();
                int rightRoll = t1.getValue();

                if (leftRoll == rightRoll) {
                    return 0;
                } else if (leftRoll < rightRoll) {
                    return 1;
                } else return -1;
            }
        });

    }

    public int getSumOfRolls() {
        int sumOfRolls = 0;
        for (Roll roll : mRolls) {
            sumOfRolls = sumOfRolls + roll.getValue();
        }
        return sumOfRolls;
    }


}
