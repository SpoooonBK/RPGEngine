package net.estebanrodriguez.libs.utilities;

/**
 * Created by spoooon on 5/30/17.
 */

public class Roll {

    private String mID;
    private int mValue;
    private Die mDie;


    public Roll(Die die){
        mDie = die;
        mID = "untracked";
        mValue = DiceRoller.rollDie(die);
    }


    public Roll(String id, Die die){
        mID = id;
        mDie = die;
        mValue = DiceRoller.rollDie(die);
    }

    //Rolls multiple of one die
    public Roll(String id, int numberOfRolls, Die die){
        mID = id;
        mValue = 0;
        mDie = die;
        for(int i = 0; i < numberOfRolls; i++){
            mValue = mValue + DiceRoller.rollDie(die);
        }
    }



    public void addModifier(int modifer){
        mValue = mValue + modifer;
    }

    public String getID() {
        if(mID != null){
            return mID;
        }
        else return "0";
    }

    public int getValue() {
        return mValue;
    }
}
