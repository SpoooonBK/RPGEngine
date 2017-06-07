package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CombatComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.factories.Mob;
import net.estebanrodriguez.libs.utilities.Dice;
import net.estebanrodriguez.libs.utilities.DiceRoller;
import net.estebanrodriguez.libs.utilities.Roll;
import net.estebanrodriguez.libs.utilities.RollTracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 5/26/17.
 */

public class CombatEngine {

    private CombatEngine() {
    }


    private static class CombatEngineHelper {
        private static final CombatEngine INSTANCE = new CombatEngine();
    }

    public static CombatEngine getInstance() {
        return CombatEngineHelper.INSTANCE;
    }

    private List<Combatant> mCombatants = new ArrayList<>();
    private List<Team> mTeams = new ArrayList<>();
    private int turnIndex;


    public void addTeam(Team team){
        if(team.size() > 0){
            mTeams.add(team);
            for(Combatant combatant: team.getCombatants()){
                mCombatants.add(combatant);
            }
        }
    }


    public void addCombatant(GameEntity gameEntity, Team team) {
        if (canFight(gameEntity)) {
            mCombatants.add(new Combatant(gameEntity));
        }
    }

    public void addMob(Mob mob, Team team) {
        for (GameEntity gameEntity : mob.getGameEntities()) {
            addCombatant(gameEntity, team);
        }
    }


    public Fight  executeCombat(){
        Fight fight = new Fight(mTeams);
        if(mTeams.size() >=2){
            rollForInitiative();
        }
        fight.addCombatRound(executeCombatRound());

        return fight;
    }

    public CombatRound executeCombatRound(){
        StringBuilder stringBuilder = new StringBuilder();

    }



    public Fight executeAutoCombat() {

        Fight fight = new Fight(mTeams);


        if (mTeams.size() >= 2) {
            rollForInitiative();
        }

        while (!hasWinner()) {
            fight.addCombatRound(executeAutoCombatRound());
        }
            fight.setWinner(getWinner());

        return fight;
    }


    //For Testing purposes
    public CombatRound executeAutoCombatRound() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Combatant attacker : mCombatants) {
            if (attacker.isAlive()) {
                String result = attack(attacker, chooseDefender(attacker));
                stringBuilder.append(result + "\n");
            }
        }
        return new CombatRound(stringBuilder.toString());
    }


    public String attack(Combatant attacker, Combatant defender) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Attacker: " + attacker.getName() +"\n");
        stringBuilder.append("Health: " + attacker.getStatsComponent().getCurrentHealth() + "\n");
        stringBuilder.append("Defender: " + defender.getName() +"\n");
        stringBuilder.append("Health: " + defender.getStatsComponent().getCurrentHealth() + "\n" );


        if (rollForHit(attacker, defender)) {
            int damage = rollForDamage(attacker, defender);
            defender.getStatsComponent().decrementHealth(damage);
            stringBuilder.append(attacker.getName()
                    + " hits " + defender.getName()
                    + " for " + damage + " damage!\n");

            if (!defender.isAlive()) {
                stringBuilder.append(attacker.getName() + " defeats " + defender.getName() + "!\n");
            }
        } else stringBuilder.append(attacker.getName() + " misses " + defender.getName() + ".\n");

        return stringBuilder.toString();
    }


    private Team chooseRandomDefendingTeam(Combatant attacker){
        int index = DiceRoller.rollRandomInt(0, (mTeams.size() - 1));
        Team randomTeam = mTeams.get(index);
        if(!randomTeam.contains(attacker) && randomTeam.isAlive()){
            return randomTeam;
        }else chooseRandomDefendingTeam(attacker);
        return null;
    }


    //For Testing purposes
    private Combatant chooseDefender(Combatant attacker) {

        Team attackingTeam = null;

        for(Team team: mTeams){
            if(team.contains(attacker)){
                attackingTeam = team;
            }
        }

        for(Combatant combatant: mCombatants){
            if(combatant.isAlive() && !attackingTeam.contains(combatant)){
                return combatant;
            }
        }

        return null;
    }


    private boolean rollForHit(Combatant attacker, Combatant defender) {

        StatsComponent attackerStats = attacker.getStatsComponent();
        StatsComponent defenderStats = defender.getStatsComponent();

        Roll attackRoll = new Roll();
        attackRoll.addModifier(attackerStats.getMusclesModifier());
        return (attackRoll.getRoll() > defenderStats.getDefense());
    }

    private int rollForDamage(Combatant attacker, Combatant defender) {
        StatsComponent attackerStats = attacker.getStatsComponent();
        StatsComponent defenderStats = defender.getStatsComponent();

        GearComponent attackerGear = attacker.getGearComponent();
        GearComponent defenderGear = defender.getGearComponent();

        List<WeaponComponent> weaponComponentList = attackerGear.getWeaponComponentList();

        WeaponComponent weaponComponent =
                (WeaponComponent) weaponComponentList.get(0);


        Roll roll = new Roll(weaponComponent.getEntityID(), weaponComponent.getBaseDie(), weaponComponent.getDieMultiplier());
        roll.addModifier(attackerStats.getMusclesModifier());

        int damage = roll.getRoll() - defenderStats.getDamageReduction();

        defenderStats.decrementHealth(damage);

        return damage;
    }


    public void rollForInitiative() {

        RollTracker rollTracker = new RollTracker();

        for (Combatant combatant : mCombatants) {
            Roll roll = new Roll(combatant.getId(), Dice.D20);
            roll.addModifier(combatant.getStatsComponent().getSpeedModifier());
            rollTracker.addRoll(roll);
        }

        rollTracker.sortByHighest();

        List<Combatant> initiativeOrder = new ArrayList<>();
        for (Roll roll : rollTracker.getRolls()) {
            for (Combatant combatant : mCombatants) {
                if (combatant.getId().equals(roll.getID())) {
                    initiativeOrder.add(combatant);
                    break;
                }
            }
        }
        mCombatants = initiativeOrder;
    }


    public static boolean canFight(GameEntity gameEntity) {
        return gameEntity.has(CombatComponent.COMPONENT_NAME)
                && gameEntity.has(GearComponent.COMPONENT_NAME)
                && gameEntity.has(StatsComponent.COMPONENT_NAME)
                && gameEntity.has(CharacterComponent.COMPONENT_NAME);
    }


    private boolean hasWinner(){

        int alive = 0;
        for(Team team: mTeams){
            if(team.isAlive()){
                alive ++;
            }
        }
        if(alive == 1){
            return true;
        }else return false;
    }

    private Team getWinner(){
        if(hasWinner()){
            for(Team team : mTeams){
                if(team.isAlive()){
                    return team;
                }
            }
        }
        return null;
    }

    public Combatant getCombatantByID(String id){
        for(Combatant combatant: mCombatants){
            if (id.equals(combatant.getId())) {
                return combatant;
            }
        }
        return null;
    }

    public Combatant getCurrentCombatant(){
        return mCombatants.get(turnIndex);
    }

    private void incrementTurn(){
        turnIndex++;
    }


}
