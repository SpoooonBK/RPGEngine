package net.estebanrodriguez.libs.entity_system.factories;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CombatComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.utilities.DiceRoller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 5/31/17.
 */

public class MobFactory implements GameEntityFactory {

    public static int MOB_MAX = 4;
    public static int MOB_LEVEL_MIN_DIVISOR = 3;


    public GameEntity createGameEntity() {
        return createGameEntity(1);
    }

    public GameEntity createGameEntity(int level){
        return createGameEntity(level, "Rando");
    }

    public GameEntity createGameEntity(int level, String name){

      return GameEntity.getBuilder()
                .add(new CharacterComponent(name))
                .add(new CombatComponent())
                .add(new StatsComponent(level))
                .add(new GearComponent())
                .build();
    }


    public Mob createMob(int mobSize, int maxLevel, LevelPreference levelPreference){
        List<GameEntity> mobList = new ArrayList<>();

        switch (levelPreference){
            case RANDOM_LEVEL:{
                for(int i = 0; i < mobSize; i++){
                    mobList.add(createGameEntity(DiceRoller.rollRandomInt(1, maxLevel)));
                }
                break;
            }
            default:{
                for(int i = 0; i < mobSize; i++){
                    mobList.add(createGameEntity(maxLevel));
                }
                break;
            }
        }
        return new Mob(mobList);
    }

    public Mob createLevelTargetedMob(int targetLevel, int maxMobSize, LevelPreference levelPreference){

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

        List<GameEntity> mobList = new ArrayList<>();
        int count = 0;
        for(Integer level: mobLevels){
            count++;
            mobList.add(createGameEntity(level, "Rando" + count));
        }

        return new Mob(mobList);
    }


    public enum LevelPreference{
        RANDOM_LEVEL, SAME_LEVEL
    }
}
