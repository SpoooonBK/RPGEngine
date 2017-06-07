package net.estebanrodriguez.libs.entity_system.systems.combat;

/**
 * Created by spoooon on 6/6/17.
 */

public class CombatRound {

    private String mCombatLog;
    private int mRound;


    public CombatRound(String combatLog) {
        this.mCombatLog = combatLog;
    }

    public String getCombatLog() {
        return mCombatLog;
    }

    public int getRound() {
        return mRound;
    }

    public void setRound(int round) {
        mRound = round;
    }

    @Override
    public String toString() {
        return mCombatLog;
    }
}
