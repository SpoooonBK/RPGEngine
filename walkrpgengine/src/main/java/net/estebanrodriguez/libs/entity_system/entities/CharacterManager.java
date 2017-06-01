package net.estebanrodriguez.libs.entity_system.entities;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CombatComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.GearComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 6/1/17.
 * Helper class to perform checks and operations on Character GameEntities without having to
 * wite messy code.
 *
 */

public class CharacterManager {



    public static boolean canFight(GameEntity gameEntity) {
        return (hasCombat(gameEntity) && hasStats(gameEntity) && hasGear(gameEntity) && isCharacter(gameEntity));
    }

    public static int getLevel(GameEntity gameEntity) {
        if(hasStats(gameEntity)){
            return ((StatsComponent) gameEntity.getComponents().get(StatsComponent.STATS_COMPONENT)).getLevel();
        }
        return 0;
    }

    public static boolean hasStats(GameEntity gameEntity) {
        return gameEntity.getComponents().containsKey(StatsComponent.STATS_COMPONENT);
    }

    public static boolean isCharacter(GameEntity gameEntity){
        return gameEntity.getComponents().containsKey(CharacterComponent.CHARACTER_COMPONENT);
    }

    public static boolean hasCombat(GameEntity gameEntity){
        return gameEntity.getComponents().containsKey(CombatComponent.COMBAT_COMPONENT);
    }

    public static boolean hasGear(GameEntity gameEntity){
        return gameEntity.getComponents().containsKey(GearComponent.GEAR_COMPONENT);
    }

    public static GearComponent getGearComponent(GameEntity gameEntity){
        if(hasGear(gameEntity)){
            return (GearComponent) gameEntity.getComponents().get(GearComponent.GEAR_COMPONENT);
        } else {
            throw new IllegalArgumentException("GameEntity does not have GearComponent");
        }
    }

    public static CharacterComponent getCharacterComponent(GameEntity gameEntity){
        if(isCharacter(gameEntity)){
            return (CharacterComponent) gameEntity.getComponents().get(CharacterComponent.CHARACTER_COMPONENT);
        } else {
            throw new IllegalArgumentException("GameEntity does not have CharacterComponent");
        }
    }

    public static StatsComponent getStatsComponent(GameEntity gameEntity){
        if(hasStats(gameEntity)){
            return (StatsComponent) gameEntity.getComponents().get(StatsComponent.STATS_COMPONENT);
        }else {
            throw new IllegalArgumentException("GameEntity does not have StatsComponent");
        }
    }

}
