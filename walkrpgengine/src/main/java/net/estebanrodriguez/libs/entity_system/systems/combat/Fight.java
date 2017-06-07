package net.estebanrodriguez.libs.entity_system.systems.combat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 6/6/17.
 */

public class Fight {

    private List<Team> mTeams;
    private List<CombatRound> mCombatRounds = new ArrayList<>();
    private Team mWinner;

    public Fight(List<Team> teams) {
        mTeams = teams;
    }

    public void addCombatRound(CombatRound combatRound){
        combatRound.setRound(getTotalRounds());
        mCombatRounds.add(combatRound);
    }

    public Team getWinner() {
        return mWinner;
    }

    public int getTotalRounds(){
        return (mCombatRounds.size() + 1);
    }

    public void setWinner(Team winner) {
        this.mWinner = winner;
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
