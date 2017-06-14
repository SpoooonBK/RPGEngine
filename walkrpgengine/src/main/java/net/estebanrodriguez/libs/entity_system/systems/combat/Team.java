package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.factories.Mob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 6/6/17.
 */

public class Team {

    private Team() {
    }

    private String teamName = "team_weak";
    private List<Combatant> mCombatants = new ArrayList<>();

    public static Builder getBuilder(){
        return new Builder();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    private void addCombant(Combatant combatant){
        mCombatants.add(combatant);
    }

    public List<Combatant> getCombatants() {
        return mCombatants;
    }

    public boolean isAlive(){

        int alive = 0;

        for (Combatant combatant : mCombatants) {
            if (combatant.isAlive()) {
                alive++;
            }
        }
        return (alive > 0);
    }

    public boolean contains(Combatant combatant){
        return mCombatants.contains(combatant);
    }

    public int size(){
        return mCombatants.size();
    }


    public static class Builder {

        private Builder(){}

        private Team instance = new Team();

        public Builder add(Combatant combatant){
            instance.addCombant(combatant);
            return this;
        }

        public Builder setName(String name){
            instance.teamName = name;
            return this;
        }

        public Builder add(Mob mob){
            for(GameEntity gameEntity: mob.getGameEntities()){
                    instance.addCombant(new Combatant(gameEntity));
            }
            return this;
        }
        public Team build() {
            return instance;
        }
    }

}
