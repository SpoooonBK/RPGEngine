package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.components.characters.BodyComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.skills.CombatComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.utilities.DiceRoller;
import net.estebanrodriguez.libs.utilities.Die;
import net.estebanrodriguez.libs.utilities.Roll;

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



//    public void addCombatant(GameEntity gameEntity, Team team) {
//        if (canFight(gameEntity)) {
//            mCombatants.add(new Combatant(gameEntity));
//        }
//    }
//
//    public void addMob(Mob mob, Team team) {
//        for (GameEntity gameEntity : mob.getEntities()) {
//            addCombatant(gameEntity, team);
//        }
//    }


    public void initiateCombat(Fight fight){

    }


//
//    public CombatRound executeCombatRound(Fight fight, int round){
//        StringBuilder stringBuilder = new StringBuilder();
//        List<Combatant> combatants = fight.getCombatantAttackQueue();
//        int totalTurns = combatants.size();
//        CombatRound combatRound = new CombatRound(round, totalTurns);
//        for(int i = 0; i < totalTurns; i++){
//            executeCombatTurn(fight.getCombatantByAttackQueueIndex(i));
//        }
//
//    }
//
//    private void executeCombatTurn(Combatant attacker) {
//        attacker.getTarget();
//
//    }

//
//    public Fight executeAutoCombat(Fight fight) {
//
//
//
//        if (fight.getTeams().size() >= 2) {
//            rollForInitiative(fight);
//        }
//
//        while (!hasWinner(fight)) {
//            fight.addCombatRound(executeAutoCombatRound());
//        }
//            fight.setWinner(getWinner());
//
//        return fight;
//    }
//

    //For Testing purposes
//    public CombatRound executeAutoCombatRound() {
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (Combatant attacker : mCombatants) {
//            if (attacker.isAlive()) {
//                String result = attack(attacker, chooseDefender(attacker));
//                stringBuilder.append(result + "\n");
//            }
//        }
//        return new CombatRound(stringBuilder.toString());
//    }


//    public String attack(Combatant attacker, Combatant defender) {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("Attacker: " + attacker.getName() +"\n");
//        stringBuilder.append("Health: " + attacker.getStatsComponent().getCurrentHealth() + "\n");
//        stringBuilder.append("Defender: " + defender.getName() +"\n");
//        stringBuilder.append("Health: " + defender.getStatsComponent().getCurrentHealth() + "\n" );
//
//
//        if (rollForHit(attacker, defender)) {
//            int damage = rollForDamage(attacker, defender);
//            defender.getStatsComponent().decrementHealth(damage);
//            stringBuilder.append(attacker.getName()
//                    + " hits " + defender.getName()
//                    + " for " + damage + " damage!\n");
//
//            if (!defender.isAlive()) {
//                stringBuilder.append(attacker.getName() + " defeats " + defender.getName() + "!\n");
//            }
//        } else stringBuilder.append(attacker.getName() + " misses " + defender.getName() + ".\n");
//
//        return stringBuilder.toString();
//    }


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

        Roll attackRoll = new Roll(new Die(Die.StandardDie.D20));
        attackRoll.addModifier(attackerStats.getMusclesModifier());
        return (attackRoll.getValue() > defenderStats.getDefense());
    }

//    private int rollForDamage(Combatant attacker, Combatant defender, GameEntity weapon) {
//        StatsComponent attackerStats = attacker.getStatsComponent();
//        StatsComponent defenderStats = defender.getStatsComponent();
//
//        BodyComponent attackerBody = attacker.getBodyComponent();
//        BodyComponent defenderBody = defender.getBodyComponent();
//
//        List<GameEntity> weaponsList = attackerBody.getAllEquipment().getWeaponsList();
//        for(GameEntity weapon: weaponsList){
//
//        }
//
//
//
//        Roll roll = new Roll(weaponComponent.getEntityID(), weaponComponent.getDieMultiplier(), weaponComponent.getBaseDie());
//        roll.addModifier(attackerStats.getMusclesModifier());
//
//        int damage = roll.getValue() - defenderStats.getDamageReduction();
//
//        defenderStats.decrementHealth(damage);
//
//        return damage;
//    }
//




    public static boolean canFight(Entity gameEntity) {
        return gameEntity.has(CombatComponent.COMPONENT_NAME)
                && gameEntity.has(BodyComponent.COMPONENT_NAME)
                && gameEntity.has(StatsComponent.COMPONENT_NAME)
                && gameEntity.has(CharacterComponent.COMPONENT_NAME);
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
