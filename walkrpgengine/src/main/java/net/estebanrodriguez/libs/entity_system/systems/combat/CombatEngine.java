package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CombatComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.factories.Mob;
import net.estebanrodriguez.libs.utilities.Dice;
import net.estebanrodriguez.libs.utilities.Roll;
import net.estebanrodriguez.libs.utilities.RollTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by spoooon on 5/26/17.
 */

public class CombatEngine {

    private CombatEngine(){}



    private static class CombatEngineHelper{
        private  static final CombatEngine INSTANCE = new CombatEngine();
    }

    public static CombatEngine getInstance(){
        return CombatEngineHelper.INSTANCE;
    }

    private List<Combatant> mCombatants = new ArrayList<>();



    public void addCombatant(GameEntity gameEntity, CombatGroup combatGroup){
        if(canFight(gameEntity)){
            mCombatants.add(new Combatant(gameEntity, combatGroup));
        }
    }

    public void addMob(Mob mob, CombatGroup combatGroup){
        for(GameEntity gameEntity: mob.getGameEntities()){
            addCombatant(gameEntity, combatGroup);
        }
    }

    public String fight(){

        StringBuilder stringBuilder = new StringBuilder();

        if(mCombatants.size() >= 2){
            rollForInititiative();
        }

        while(groupCheckIfAlive(CombatGroup.GROUP_A) && groupCheckIfAlive(CombatGroup.GROUP_B)){
            String result = executeAttackRound();
            stringBuilder.append(result);
        }

        if(groupCheckIfAlive(CombatGroup.GROUP_A)){
            stringBuilder.append("Group A wins!");
        }else stringBuilder.append("Group B wins!");

        return stringBuilder.toString();
    }


    //For Testing purposes
    public String executeAttackRound(){

        StringBuilder stringBuilder = new StringBuilder();

        for(Combatant attacker: mCombatants) {
            if(checkIfAlive(attacker)) {
                String result = attack(attacker, chooseDefender(attacker.getCombatGroup()));
                stringBuilder.append(result + "\n");
            }
        }
        return stringBuilder.toString();
    }


    public String attack(Combatant attacker, Combatant defender){

        StringBuilder stringBuilder = new StringBuilder();

        if(rollForHit(attacker, defender)){
            int damage = rollForDamage(attacker, defender);
            defender.getStatsComponent().decrementHealth(damage);
            stringBuilder.append(attacker.getName()
                    + " hits " + defender.getName()
                    + "for " + damage + " damage!\n" );

            if(!checkIfAlive(defender)){
                stringBuilder.append(attacker.getName() + " defeats " + defender.getName() + "!\n");
            }
        }else stringBuilder.append(attacker.getName() + " misses " + defender.getName() + ".\n");

        return stringBuilder.toString();
    }



    //For Testing purposes
    private Combatant chooseDefender(CombatGroup combatGroup) {
        for(Combatant combatant: mCombatants)
            if(combatant.getCombatGroup() != combatGroup && checkIfAlive(combatant)){
                return combatant;
            }
        return null;
    }




    private boolean rollForHit(Combatant attacker, Combatant defender){

        StatsComponent attackerStats = attacker.getStatsComponent();
        StatsComponent defenderStats = defender.getStatsComponent();

        Roll attackRoll = new Roll();
        attackRoll.addModifier(attackerStats.getMusclesModifier());
        return (attackRoll.getRoll() > defenderStats.getDefense());
    }

    private int rollForDamage(Combatant attacker, Combatant defender){
        StatsComponent attackerStats = attacker.getStatsComponent();
        StatsComponent defenderStats = defender.getStatsComponent();

        GearComponent attackerGear = attacker.getGearComponent();
        GearComponent defenderGear = defender.getGearComponent();

        Map<BodyPart, GameEntity> weapons = attackerGear.getEquippedWeapons();
        Map<BodyPart, GameEntity> armor = defenderGear.getEquippedArmor();

        WeaponComponent weaponComponent =
                (WeaponComponent) attackerGear.getMainWeapon().getComponents().get(WeaponComponent.COMPONENT_NAME);


        Roll roll = new Roll(weaponComponent.getEntityID(), weaponComponent.getBaseDie(), weaponComponent.getDieMultiplier());
        roll.addModifier(attackerStats.getMusclesModifier());

        int damage = roll.getRoll() - defenderStats.getDamageReduction();

        defenderStats.decrementHealth(damage);

        return damage;
    }



    private void rollForInititiative(){

        RollTracker rollTracker = new RollTracker();

        for(Combatant combatant: mCombatants){
            Roll roll = new Roll(combatant.getId(), Dice.D20);
            roll.addModifier(combatant.getStatsComponent().getSpeedModifier());
            rollTracker.addRoll(roll);
        }

        rollTracker.sortByHighest();

        List<Combatant> initiativeOrder = new ArrayList<>();
            for(Roll roll: rollTracker.getRolls()){
                for(Combatant combatant: mCombatants){
                    if(combatant.getId().equals(roll.getID())){
                        initiativeOrder.add(combatant);
                        break;
                    }
                }
            }
        mCombatants = initiativeOrder;
    }

    private boolean  groupCheckIfAlive(CombatGroup combatGroup){

      int alive = 0;

        for(Combatant combatant: mCombatants) {

            if (combatant.getCombatGroup() == combatGroup) {
                if (checkIfAlive(combatant)) {
                        alive++;
                }
            }
        }
        return (alive > 0);
    }


    private boolean checkIfAlive(Combatant combatant){
        int health = combatant.getStatsComponent().getCurrentHealth();
        return (health > 0);
    }

    public static boolean canFight(GameEntity gameEntity) {
        return gameEntity.has(CombatComponent.COMPONENT_NAME)
                && gameEntity.has(GearComponent.COMPONENT_NAME)
                && gameEntity.has(StatsComponent.COMPONENT_NAME)
                && gameEntity.has(CharacterComponent.COMPONENT_NAME);
    }


}
