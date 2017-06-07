package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CombatComponent;
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
    private GameEntity mGameEntity;
    private StatsComponent mStatsComponent;
    private GearComponent mGearComponent;


    public Combatant(GameEntity gameEntity) {

            if(canFight(gameEntity)){
                mGameEntity = gameEntity;
                mId = gameEntity.getId();
                mName = ((CharacterComponent) gameEntity.get(CharacterComponent.COMPONENT_NAME)).getName();
                mStatsComponent = (StatsComponent) gameEntity.get(StatsComponent.COMPONENT_NAME);
                mGearComponent = (GearComponent) gameEntity.get(GearComponent.COMPONENT_NAME);
            } else throw new IllegalArgumentException("GameEntity cannot executeCombat");
    }


    public String getId() {
        return mId;
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


    public static boolean canFight(GameEntity gameEntity) {
        return gameEntity.has(CombatComponent.COMPONENT_NAME)
                && gameEntity.has(GearComponent.COMPONENT_NAME)
                && gameEntity.has(StatsComponent.COMPONENT_NAME)
                && gameEntity.has(CharacterComponent.COMPONENT_NAME);
    }

    public boolean isAlive(){
        return (mStatsComponent.getCurrentHealth() > 0);
    }

}
