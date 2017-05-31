package net.estebanrodriguez.libs.entity_system.engines.combat;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 5/30/17.
 */

public final class Combatant {

    private final String mId;
    private final String mName;
    private final CombatEngine.CombatRole mCombatRole;
    private final GameEntity mGameEntity;
    private final StatsComponent mStatsComponent;
    private final GearComponent mGearComponent;

    public Combatant(GameEntity gameEntity, CombatEngine.CombatRole combatRole) {

        mGameEntity = gameEntity;
        mId = gameEntity.getId();
        mName = gameEntity.getComponents().get(EntityComponent.CHARACTER_COMPONENT).getComponentName();
        mCombatRole = combatRole;
        mStatsComponent = (StatsComponent) gameEntity.getComponents().get(EntityComponent.STATS_COMPONENT);
        mGearComponent = (GearComponent) gameEntity.getComponents().get(GearComponent.GEAR_COMPONENT);

    }

    public String getId() {
        return mId;
    }

    public CombatEngine.CombatRole getCombatRole() {
        return mCombatRole;
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
}
