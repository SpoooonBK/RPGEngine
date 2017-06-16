package net.estebanrodriguez.libs.entity_system.factories;

import net.estebanrodriguez.libs.entity_system.components.characters.BodyComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.skills.CombatComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 6/15/17.
 */

public class CharacterFactory implements EntityFactory {

    @Override
    public Entity createEntity() {
        return createEntity(1);
    }

    public Entity createEntity(int level){
        return createEntity("Rando", level);
    }

    public Entity createEntity(String name, int level){

        return GameEntity.getBuilder()
                .add(new CharacterComponent(name))
                .add(new CombatComponent())
                .add(new StatsComponent(level))
                .add(new BodyComponent())
                .build();
    }
}
