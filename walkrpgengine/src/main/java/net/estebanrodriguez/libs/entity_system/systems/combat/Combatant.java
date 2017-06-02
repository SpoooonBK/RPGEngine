package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.entities.EntityManager;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.utilities.Dice;
import net.estebanrodriguez.libs.utilities.DiceRoller;

/**
 * Created by spoooon on 5/30/17.
 */

public final class Combatant {

    private String mId;
    private String mName;
    private CombatGroup mCombatGroup;
    private GameEntity mGameEntity;
    private StatsComponent mStatsComponent;
    private GearComponent mGearComponent;


    public Combatant(GameEntity gameEntity, CombatGroup combatGroup) {

            if(CombatEngine.canFight(gameEntity)){
                if(combatGroup == CombatGroup.GROUP_RANDOM){
                    randomlyAssignToGroup();
                } else{
                    mCombatGroup = combatGroup;
                }
                mGameEntity = gameEntity;
                mId = gameEntity.getId();
                mName = ((CharacterComponent) gameEntity.get(CharacterComponent.COMPONENT_NAME)).getName();
                mStatsComponent = (StatsComponent) gameEntity.get(StatsComponent.COMPONENT_NAME);
                mGearComponent = (GearComponent) gameEntity.get(GearComponent.COMPONENT_NAME);
            } else throw new IllegalArgumentException("GameEntity cannot fight");
    }


    public String getId() {
        return mId;
    }

    public CombatGroup getCombatGroup() {
        return mCombatGroup;
    }

    public GameEntity getGameEntity() {
        return mGameEntity;
    }

    public StatsComponent getStatsComponent() {
        return mStatsComponent;
    }

    public GearComponent getGearComponent() {
        return mGearComponent;
    }


    public String getName() {
        return mName;
    }

    private void randomlyAssignToGroup(){
        int roll = DiceRoller.rollDie(Dice.D2);
        if(roll == 1){
            mCombatGroup = CombatGroup.GROUP_A;
        }else mCombatGroup = CombatGroup.GROUP_B;
    }
}
