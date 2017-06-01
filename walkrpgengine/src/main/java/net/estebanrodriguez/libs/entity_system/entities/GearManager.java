package net.estebanrodriguez.libs.entity_system.entities;

import net.estebanrodriguez.libs.entity_system.components.characters.common.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;

/**
 * Created by spoooon on 6/1/17.
 */

public class GearManager {

    public static boolean isWeapon(GameEntity gameEntity){
        return gameEntity.getComponents().containsKey(WeaponComponent.WEAPON_COMPONENT);
    }

    public static boolean isArmor(GameEntity gameEntity){
        return gameEntity.getComponents().containsKey(ArmorComponent.ARMOR_COMPONENT);
    }

    public static WeaponComponent getWeaponComponent(GameEntity gameEntity){
        if(isWeapon(gameEntity)){
            return (WeaponComponent) gameEntity.getComponents().get(WeaponComponent.WEAPON_COMPONENT);
        }else throw new IllegalArgumentException("GameEntity does not have a Weapon Component. "
                + "GameEntityID: " + gameEntity.getId());
    }

    public static ArmorComponent getArmorComponent(GameEntity gameEntity){
        if(isArmor(gameEntity)){
            return (ArmorComponent) gameEntity.getComponents().get(ArmorComponent.ARMOR_COMPONENT);
        }else throw new IllegalArgumentException("GameEntity does not have an Armor Component. "
                + "GameEntityID: " + gameEntity.getId());
    }
}
