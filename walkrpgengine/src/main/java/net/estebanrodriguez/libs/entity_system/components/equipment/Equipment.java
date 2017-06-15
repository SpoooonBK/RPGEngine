package net.estebanrodriguez.libs.entity_system.components.equipment;

import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.utilities.EntityContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spoooon on 6/12/17.
 */

public class Equipment implements EntityContainer {


    private Map<String, Entity> mWeapons = new HashMap<>();
    private Map<String, Entity> mArmor = new HashMap<>();

    public Equipment() {}

    public void add(Entity equipment){
        if(equipment.has(WeaponComponent.COMPONENT_NAME)){
            addWeapon(equipment);
        } else if (equipment.has(ArmorComponent.COMPONENT_NAME)){
            addArmor(equipment);
        }
    }

    public void add(Equipment equipment){
        List<Entity> weapons = equipment.getWeaponsList();
        add(weapons);
        List<Entity> armor = equipment.getArmorList();
        add(armor);
    }

    private void add(List<Entity> equipment){
        for(Entity gameEntity: equipment){
            add(gameEntity);
        }
    }

    private void addArmor(Entity armor){
        String id = armor.getId();
        mArmor.put(id, armor);
    }

    private void addWeapon(Entity weapon){
            String id = weapon.getId();
            mWeapons.put(id, weapon);

    }

    public Entity getById(String id){
        Entity gameEntity;
        if(mWeapons.containsKey(id)){
            return mWeapons.get(id);
        }else if(mArmor.containsKey(id)){
            return mArmor.get(id);
        }else return GameEntity.getEmptyEntity();
    }

    @Override
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();
        entities.addAll(mWeapons.values());
        entities.addAll(mArmor.values());
        return entities;
    }

    @Override
    public int size() {
        return mArmor.size() + mWeapons.size();
    }

    public List<Entity> getWeaponsList(){
        return new ArrayList<>(mWeapons.values());
    }

    public List<Entity> getArmorList(){
        return new ArrayList<>(mArmor.values());
    }



}
