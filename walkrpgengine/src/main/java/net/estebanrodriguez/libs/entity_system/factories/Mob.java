package net.estebanrodriguez.libs.entity_system.factories;

import net.estebanrodriguez.libs.entity_system.components.characters.StatsComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.entity_system.systems.inventory.EquipSystem;
import net.estebanrodriguez.libs.utilities.EntityContainer;

import java.util.List;

/**
 * Created by spoooon on 6/1/17.
 * A Mob is a collection of GameEntities that can participate in combat.  They are particularly
 * used as combatants against the player character
 */

public class Mob implements EntityContainer{

    private List<Entity> mEntities;
    private int mTotalLevels;


    public Mob(List<Entity> entities) {
        mEntities = entities;
    }

    public List<Entity> getEntities() {
        return mEntities;
    }


    public int getTotalLevels() {
        return mTotalLevels;
    }

    private int size(){
        return mEntities.size();
    }

    private void calcuateTotalLevels(){
        mTotalLevels = 0;
        for(Entity gameEntity: mEntities){
            mTotalLevels = mTotalLevels
                    + ((StatsComponent)gameEntity.get(StatsComponent.COMPONENT_NAME)).getLevel();
        }
    }

    public void equipMob(Entity gear){
        for(Entity character: mEntities){
            EquipSystem equipper = new EquipSystem();
            equipper.equip(character, gear);
        }
    }

    public void equipMob(List<Entity> gearList){
        for(Entity gear: gearList){
            equipMob(gear);
        }
    }

    //TODO implement

    @Override
    public void add(Entity gameEntity) {

    }

    @Override
    public Entity getById(String id) {
        return null;
    }
}
