package net.estebanrodriguez.libs.entity_system.engines.combat;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 5/30/17.
 */

public final class Combatant {

    private final String mId;
    private final String mName;
    private final CombatGroup mCombatGroup;
    private final GameEntity mGameEntity;
    private final StatsComponent mStatsComponent;
    private final GearComponent mGearComponent;

    public Combatant(GameEntity gameEntity, CombatGroup combatGroup) {

        mGameEntity = gameEntity;
        mId = gameEntity.getId();
        CharacterComponent characterComponent = (CharacterComponent) gameEntity.getComponents().get(EntityComponent.CHARACTER_COMPONENT);
        mName = characterComponent.getName();
        mCombatGroup = combatGroup;
        mStatsComponent = (StatsComponent) gameEntity.getComponents().get(EntityComponent.STATS_COMPONENT);
        mGearComponent = (GearComponent) gameEntity.getComponents().get(GearComponent.GEAR_COMPONENT);

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
}
