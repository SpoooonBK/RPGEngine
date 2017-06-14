package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.components.characters.BodyComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.skills.CombatComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.equipment.Equipment;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 5/30/17.
 */

public class Combatant {

    private String mId;
    private String mName;
    private GameEntity mGameEntity;
    private StatsComponent mStatsComponent;
    private BodyComponent mBodyComponent;
    private Combatant mTarget;


    public Combatant(GameEntity gameEntity) {

            if(canFight(gameEntity)){
                mGameEntity = gameEntity;
                mId = gameEntity.getId();
                mName = ((CharacterComponent) gameEntity.get(CharacterComponent.COMPONENT_NAME)).getName();
                mStatsComponent = (StatsComponent) gameEntity.get(StatsComponent.COMPONENT_NAME);
                mBodyComponent = (BodyComponent) gameEntity.get(BodyComponent.COMPONENT_NAME);
            } else throw new IllegalArgumentException("GameEntity cannot fight");
    }


    public String getId() {
        return mId;
    }

    public Combatant getTarget() {
        return mTarget;
    }

    public void setTarget(Combatant target) {
        mTarget = target;
    }

    public GameEntity getGameEntity() {
        return mGameEntity;
    }

    public StatsComponent getStatsComponent() {
        return mStatsComponent;
    }

    public BodyComponent getBodyComponent() {
        return mBodyComponent;
    }

    public Equipment getWeapons() {
        return getBodyComponent().getAllEquipment();
    }


    public String getName() {
        return mName;
    }


    public static boolean canFight(GameEntity gameEntity) {
        return gameEntity.has(CombatComponent.COMPONENT_NAME)
                && gameEntity.has(BodyComponent.COMPONENT_NAME)
                && gameEntity.has(StatsComponent.COMPONENT_NAME)
                && gameEntity.has(CharacterComponent.COMPONENT_NAME);
    }

    public boolean isAlive(){
        return (mStatsComponent.getCurrentHealth() > 0);
    }

}
