package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.systems.combat.interfaces.CombatTurnListener;

/**
 * Created by spoooon on 6/6/17.
 */

public class CombatRound implements CombatTurnListener{

    private String mCombatLog;
    private int mRound;
    private int mTotalTurns;
    private int mCurrentTurn;


    public CombatRound(int round, int totalTurns){
        mRound = round;
        mTotalTurns = totalTurns;
        mCurrentTurn = 0;
    }
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

    public int getTotalTurns() {
        return mTotalTurns;
    }

    public int getCurrentTurn() {
        return mCurrentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        mCurrentTurn = currentTurn;
    }

    public void incrementCurrentTurn(){
        mCurrentTurn++;
    }

    @Override
    public String toString() {
        return mCombatLog;
    }

    @Override
    public void onCombatTurnIncrement(int turnIndex) {

    }
}
