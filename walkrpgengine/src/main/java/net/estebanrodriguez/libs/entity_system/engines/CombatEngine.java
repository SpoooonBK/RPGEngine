package net.estebanrodriguez.libs.entity_system.engines;

import net.estebanrodriguez.libs.entity_system.components.characters.common.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CombatComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.utilities.Dice;
import net.estebanrodriguez.libs.utilities.DiceRoller;
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

    private List<GameEntity> mGroupA = new ArrayList<>();
    private List<GameEntity> mGroupB = new ArrayList<>();


    public void addCombatant(GameEntity gameEntity, CombatRole combatRole){

        if(canFight(gameEntity)){
            switch (combatRole){

                case GROUP_A: mGroupA.add(gameEntity);
                    break;
                case GROUP_B: mGroupB.add(gameEntity);
                    break;
                case GROUP_RANDOM:
                    break;
            }
        }
    }


    public void fight(){
        if(mGroupA.size() > 0 && mGroupB.size() > 0){

        }
    }


    //Checks to see if GameEntities has character and stat components.  Only GameEntities with each
    //may fight
    private boolean canFight(GameEntity gameEntity) {

                boolean hasCombat
                        = gameEntity.getComponents().containsKey(CombatComponent.COMBAT_COMPONENT);
                boolean hasStats
                        = gameEntity.getComponents().containsKey(StatsComponent.STATS_COMPONENT);
                if(!hasCombat || !hasStats){
                    return false;
                }
        return true;
    }

    private boolean rollForHit(GameEntity attacker, GameEntity defender){

        StatsComponent attackerStats = (StatsComponent) attacker.getComponents().get(StatsComponent.STATS_COMPONENT);
        StatsComponent defenderStats = (StatsComponent) defender.getComponents().get(StatsComponent.STATS_COMPONENT);

        Roll attackRoll = new Roll();
        attackRoll.addModifier(attackerStats.getMusclesModifier());
        if(attackRoll.getRoll() > defenderStats.getDefense()){
            return true;
        }
        return false;
    }

    private int rollForDamage(GameEntity attacker, GameEntity defender){
        StatsComponent attackerStats = (StatsComponent) attacker.getComponents().get(StatsComponent.STATS_COMPONENT);
        StatsComponent defenderStats = (StatsComponent) defender.getComponents().get(StatsComponent.STATS_COMPONENT);

        GearComponent attackerGear = (GearComponent) attacker.getComponents().get(GearComponent.GEAR_COMPONENT);
        GearComponent defenderGear = (GearComponent) defender.getComponents().get(GearComponent.GEAR_COMPONENT);

        Map<BodyPart, GameEntity> weapons = attackerGear.getEquippedWeapons();
        Map<BodyPart, GameEntity> armor = defenderGear.getEquippedArmor();

        GameEntity mainWeapon = attackerGear.getMainWeapon();
        WeaponComponent weaponComponent = (WeaponComponent) mainWeapon.getComponents().get(WeaponComponent.WEAPON_COMPONENT);

        Roll roll = new Roll(weaponComponent.getEntityID(), weaponComponent.getBaseDie(), weaponComponent.getDieMultiplier());
        roll.addModifier(attackerStats.getMusclesModifier());

        int damage = roll.getRoll() - defenderStats.getDamageReduction();

        defenderStats.decrementHealth(damage);

        return damage;
    }



    private List<String> rollForInititiative(){

        RollTracker rollTracker = new RollTracker();

        for(GameEntity entity: mGroupA){
            Roll roll = new Roll(entity.getId(), Dice.D20);
            int modifier =
                    ((StatsComponent)entity.getComponents().get(StatsComponent.STATS_COMPONENT)).getSpeed();
            roll.addModifier(modifier);
            rollTracker.addRoll(roll);
        }
        for(GameEntity entity: mGroupB){
            Roll roll = new Roll(entity.getId(), Dice.D20);
            int modifier =
                    ((StatsComponent)entity.getComponents().get(StatsComponent.STATS_COMPONENT)).getSpeed();
            roll.addModifier(modifier);
            rollTracker.addRoll(roll);
        }
        List<String> initiativeOrder = new ArrayList<>();
            for(Roll roll: rollTracker.getRolls()){
                initiativeOrder.add(roll.getID());
            }
        return initiativeOrder;
    }

    public enum CombatRole {
        GROUP_A, GROUP_B, GROUP_RANDOM
    }
}
