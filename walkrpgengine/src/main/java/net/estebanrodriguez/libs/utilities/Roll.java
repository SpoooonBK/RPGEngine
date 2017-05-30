package net.estebanrodriguez.libs.utilities;

/**
 * Created by spoooon on 5/30/17.
 */

public class Roll {

    private String mID;
    private int mRoll;

    //Default rolls D20 with no id
    public Roll(){
        mRoll = DiceRoller.rollDie(Dice.D20);
    }

    //Rolls one die
    public Roll(String id, Dice die){
        this(id, die, 1);
    }

    //Rolls multiple of one die
    public Roll(String id, Dice die, int numberOfRolls){
        mID = id;
        mRoll = 0;
        for(int i = 0; i < numberOfRolls; i++){
            mRoll = mRoll + DiceRoller.rollDie(die);
        }
    }



    public void addModifier(int modifer){
        mRoll = mRoll + modifer;
    }

    public String getID() {
        if(mID != null){
            return mID;
        }
        else return "0";
    }

    public int getRoll() {
        return mRoll;
    }
}
