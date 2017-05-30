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

    public void addRoll(Roll roll) {
        mRolls.add(roll);
    }

    public void sortByHighest(){

        Collections.sort(mRolls, new Comparator<Roll>() {
            @Override
            public int compare(Roll roll, Roll t1) {
                int leftRoll = roll.getRoll();
                int rightRoll = t1.getRoll();

                if(leftRoll == rightRoll){
                    return 0;
                } else if(leftRoll > rightRoll){
                    return 1;
                } else return -1;
            }
        });
    }

    public void sortByLowest(){

        Collections.sort(mRolls, new Comparator<Roll>() {
            @Override
            public int compare(Roll roll, Roll t1) {
                int leftRoll = roll.getRoll();
                int rightRoll = t1.getRoll();

                if(leftRoll == rightRoll){
                    return 0;
                } else if(leftRoll < rightRoll){
                    return 1;
                } else return -1;
            }
        });

    }



}
