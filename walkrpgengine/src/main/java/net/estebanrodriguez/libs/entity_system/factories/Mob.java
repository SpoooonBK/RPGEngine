package net.estebanrodriguez.libs.entity_system.factories;

import net.estebanrodriguez.libs.entity_system.components.characters.BodyComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.attributes.StatComponent;
import net.estebanrodriguez.libs.entity_system.components.skills.CombatComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.utilities.EntityContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spoooon on 6/1/17.
 * A Mob is a collection of GameEntities that can participate in combat.  They are particularly
 * used as combatants against the player character
 */

public class Mob implements EntityContainer{

    private Map<String, Entity> mEntities = new HashMap<>();


    @Override
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>(mEntities.values());
        return entities;
    }

    @Override
    public int size() {
        return mEntities.size();
    }

    public void add(List<Entity> entities){
        for(Entity entity: entities){
            add(entity);
        }
    }

    @Override
    public void add(Entity entity) {
        if(canAttack(entity)){
            String id = entity.getId();
            mEntities.put(id, entity);
        }
    }

    @Override
    public Entity getById(String id) {
        if(mEntities.containsKey(id)){
            return mEntities.get(id);
        }else throw new IllegalArgumentException(id + " not found.");
    }

    private boolean canAttack(Entity entity){
        return (entity.has(CombatComponent.COMPONENT_NAME)
                && entity.has(CharacterComponent.COMPONENT_NAME)
                && entity.has(BodyComponent.COMPONENT_NAME)
                && entity.has(StatComponent.COMPONENT_NAME)
        );
    }
}
