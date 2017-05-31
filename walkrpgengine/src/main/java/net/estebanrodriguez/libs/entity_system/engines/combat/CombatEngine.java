package net.estebanrodriguez.libs.entity_system.engines.combat;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.gear.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
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



    public void addCombatant(GameEntity gameEntity, CombatRole combatRole){
        if(canFight(gameEntity)){
            mCombatants.add(new Combatant(gameEntity, combatRole));
        }
    }


    //For Testing purposes
    public void fight(){
        if(mCombatants.size() >= 2){
            rollForInititiative();
            for(Combatant attacker: mCombatants){
                Combatant defender = chooseDefender(attacker.getCombatRole());
                if(rollForHit(attacker, defender)){
                    rollForDamage(attacker, defender);
                }
            }
        }
    }

    //For Testing purposes
    private Combatant chooseDefender(CombatRole combatRole) {
        for(Combatant combatant: mCombatants)
            if(combatant.getCombatRole() != combatRole){
                return combatant;
            }
        return null;
    }


    //Checks to see if GameEntities has character and stat components.  Only GameEntities with each
    //may fight
    private boolean canFight(GameEntity gameEntity) {

                boolean hasCombat
                        = gameEntity.getComponents().containsKey(EntityComponent.COMBAT_COMPONENT);
                boolean hasStats
                        = gameEntity.getComponents().containsKey(EntityComponent.STATS_COMPONENT);
            return (hasCombat && hasStats);
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
                (WeaponComponent) attackerGear.getMainWeapon().getComponents().get(EntityComponent.WEAPON_COMPONENT);


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

    public enum CombatRole {
        GROUP_A, GROUP_B, GROUP_RANDOM
    }
}
