package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.utilities.Die;
import net.estebanrodriguez.libs.utilities.Roll;
import net.estebanrodriguez.libs.utilities.RollTracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 6/6/17.
 */

public class Fight {

    private List<Team> mTeams;
    private List<Combatant> mCombatantAttackQueue;
    private List<CombatRound> mCombatRounds = new ArrayList<>();
    private Team mWinner;

    public Fight(List<Team> teams) {
        mTeams = teams;
        setCombatantAttackQueue();
        sortCombatantsByInitiative();
    }

    public void addCombatRound(CombatRound combatRound){
        combatRound.setRound(mCombatRounds.size() + 1);
        mCombatRounds.add(combatRound);
    }


    public List<Team> getTeams() {
        return mTeams;
    }



    private void setCombatantAttackQueue(){
        for(Team team: mTeams){
                for(Combatant combatant: team.getCombatants()){
                    mCombatantAttackQueue.add(combatant);
            }
        }
    }

    public Combatant getCombatantByAttackQueueIndex(int index){
        if(mCombatantAttackQueue.size()<index){
            return mCombatantAttackQueue.get(index);
        } throw new IndexOutOfBoundsException("Requested index [" + index
                + "] when attackQueue size is " + mCombatantAttackQueue.size());
    }

    public List<Combatant> getCombatantAttackQueue() {
        return mCombatantAttackQueue;
    }


    public Team getWinner() {
        return mWinner;
    }



    public void setWinner(Team winner) {
        this.mWinner = winner;
    }

    public boolean hasWinner(){
       return (countTeamsAlive() == 1);
    }

    public int countTeamsAlive(){
        int alive = 0;
        for(Team team: mTeams){
            if(team.isAlive()){
                alive ++;
            }
        }
        return alive;
    }

//    public Fight execute(){
//
//        int round = 1;
//        while(!hasWinner()){
//            CombatRound combatRound = CombatEngine.getInstance().executeCombatRound(this, round);
//            mCombatRounds.add(combatRound);
//            round++;
//        }
//            return this;
//    }


    private void sortCombatantsByInitiative() {
        List<Combatant> combatantsSortedByInitiativeOrder = new ArrayList<>();
        RollTracker initiativeRolls = trackInitiativeRolls();
        for (Roll roll : initiativeRolls.getRolls()) {
                    Combatant combatant = getCombatantByRoll(roll);
                    combatantsSortedByInitiativeOrder.add(combatant);
        }
        mCombatantAttackQueue = combatantsSortedByInitiativeOrder;
    }

    private Combatant getCombatantByRoll(Roll roll){
        Combatant resultantCombatant = null;
        for(Combatant combatant: mCombatantAttackQueue){
            if(combatant.getId().equals(roll.getID())){
                resultantCombatant = combatant;
            }
        }
        return resultantCombatant;
    }

    private RollTracker trackInitiativeRolls(){
        RollTracker initiativeRolls = new RollTracker();

        for (Combatant combatant : mCombatantAttackQueue) {
            initiativeRolls.addRoll(rollInitiativeForCombatant(combatant));
        }
        initiativeRolls.sortByHighestToLowest();
        return initiativeRolls;
    }

    private Roll rollInitiativeForCombatant(Combatant combatant){
        Roll roll = new Roll(combatant.getId(), new Die(Die.StandardDie.D20));
//        roll.addModifier(combatant.getStatComponent().getSpeedModifier());
        return roll;
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 1;
        for(Team team : mTeams){
            stringBuilder.append("Team " + count + ": " + team.getTeamName() + "\n");
            count++;
        }

        for(CombatRound combatRound : mCombatRounds){
            stringBuilder.append(combatRound);
        }
        stringBuilder.append(mWinner.getTeamName() + " wins!");

        return stringBuilder.toString();
    }
}
