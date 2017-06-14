package net.estebanrodriguez.libs.entity_system.components.equipment;

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


    private Map<String, GameEntity> mWeapons = new HashMap<>();
    private Map<String, GameEntity> mArmor = new HashMap<>();

    public Equipment() {

    }

    public Equipment(Map<String, GameEntity> weapons, Map<String, GameEntity> armor) {
        mWeapons = weapons;
        mArmor = armor;
    }

    public void add(GameEntity equipment){
        if(equipment.has(WeaponComponent.COMPONENT_NAME)){
            addWeapon(equipment);
        } else if (equipment.has(ArmorComponent.COMPONENT_NAME)){
            addArmor(equipment);
        }
    }

    public void add(Equipment equipment){
        List<GameEntity> weapons = equipment.getWeaponsList();
        add(weapons);
        List<GameEntity> armor = equipment.getArmorList();
        add(armor);
    }

    private void add(List<GameEntity> equipment){
        for(GameEntity gameEntity: equipment){
            add(gameEntity);
        }
    }

    private void addArmor(GameEntity armor){
        String id = armor.getId();
        mArmor.put(id, armor);
    }

    private void addWeapon(GameEntity weapon){
            String id = weapon.getId();
            mWeapons.put(id, weapon);

    }

    public GameEntity getById(String id){
        GameEntity gameEntity;
        if(mWeapons.containsKey(id)){
            return mWeapons.get(id);
        }else if(mArmor.containsKey(id)){
            return mArmor.get(id);
        }else return GameEntity.getEmptyGameEntity();
    }

    public List<GameEntity> getWeaponsList(){
        return new ArrayList<>(mWeapons.values());
    }

    public List<GameEntity> getArmorList(){
        return new ArrayList<>(mArmor.values());
    }

}
