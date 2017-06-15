package net.estebanrodriguez.libs.entity_system.factories;

import net.estebanrodriguez.libs.entity_system.components.characters.BodyComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.skills.CombatComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.utilities.DiceRoller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 5/31/17.
 */

public class MobFactory implements EntityFactory {

    public Entity createEntity() {
        return createEntity(1);
    }

    public Entity createEntity(int level){
        return createEntity(level, "Rando");
    }

    public Entity createEntity(int level, String name){

      return GameEntity.getBuilder()
                .add(new CharacterComponent(name))
                .add(new CombatComponent())
                .add(new StatsComponent(level))
                .add(new BodyComponent())
                .build();
    }


    public Mob createMob(String name, int mobSize, int maxLevel, LevelPreference levelPreference){
        List<Entity> mobList = new ArrayList<>();

        switch (levelPreference){
            case RANDOM_LEVEL:{
                for(int i = 0; i < mobSize; i++){
                    mobList.add(createEntity(DiceRoller.rollRandomInt(1, maxLevel), name));
                }
                break;
            }
            default:{
                for(int i = 0; i < mobSize; i++){
                    mobList.add(createEntity(maxLevel));
                }
                break;
            }
        }
        return new Mob(mobList);
    }

    public Mob createLevelTargetedMob(String name, int targetLevel, int maxMobSize, LevelPreference levelPreference){

        int totalLevels = 0;
        List<Integer> mobLevels = new ArrayList<>();

        switch (levelPreference){

            case RANDOM_LEVEL:{
                int minLevel = 1;
                int maxLevel = targetLevel;
                while (totalLevels < targetLevel && mobLevels.size() < maxMobSize){
                    int roll = DiceRoller.rollRandomInt(minLevel, maxLevel);
                    if(targetLevel >= totalLevels + roll){
                        mobLevels.add(roll);
                        totalLevels = totalLevels + roll;
                    }
                }
                break;
            }


            case SAME_LEVEL:{
                int mobLevel = (int) targetLevel/maxMobSize;
                while(totalLevels < targetLevel && mobLevels.size() < maxMobSize){
                    mobLevels.add(mobLevel);
                    totalLevels = totalLevels + mobLevel;
                }
                break;
            }

        }

        List<Entity> mobList = new ArrayList<>();
        int count = 0;
        for(Integer level: mobLevels){
            count++;
            mobList.add(createEntity(level, name + count));
        }

        return new Mob(mobList);
    }


    public enum LevelPreference{
        RANDOM_LEVEL, SAME_LEVEL
    }
}
