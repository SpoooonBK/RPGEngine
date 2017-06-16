package net.estebanrodriguez.libs.entity_system.factories;

import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.utilities.DiceRoller;
import net.estebanrodriguez.libs.utilities.Die;

/**
 * Created by spoooon on 5/31/17.
 */

public class MobFactory implements EntityContainerFactory {


    @Override
    public Mob createEntityContainer(int size) {
        return createRandomLevelMob("Rando", size, 1);
    }

    public Mob createRandomLevelMob(String name, int mobSize, int maxLevel){

        Mob mob = new Mob();
        CharacterFactory characterFactory = new CharacterFactory();
        for(int i = 0; i < mobSize; i++){
            int levelRoll = DiceRoller.rollDie(new Die(1, maxLevel));
            Entity entity = characterFactory.createEntity(name, levelRoll);
            mob.add(entity);
        }
        return mob;
    }

    public Mob createSameLevelMob(String name, int mobSize, int level){

        Mob mob = new Mob();
        CharacterFactory characterFactory = new CharacterFactory();
        for(int i = 0; i < mobSize; i++){
            Entity entity = characterFactory.createEntity(name, level);
            mob.add(entity);
        }
        return mob;
    }


    public Mob createMobWithMaxAmountOfTotalLevels(String name, int maxTotalLevels, int maxMobSize){

        Mob mob = new Mob();
        CharacterFactory characterFactory = new CharacterFactory();

        int totalLevels = 0;
        int minLevel = 1;
        int maxLevel = totalLevels;
        Die die = new Die(minLevel, maxLevel);

        while (totalLevels < totalLevels && mob.size() < maxMobSize){
            int level = DiceRoller.rollDie(die);
            if(totalLevels >= totalLevels + level){
                Entity entity = characterFactory.createEntity(name, level);
                mob.add(entity);
                totalLevels = totalLevels + level;
            }
        }
        return  mob;
    }

}
