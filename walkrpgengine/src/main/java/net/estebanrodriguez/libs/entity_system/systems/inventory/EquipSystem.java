package net.estebanrodriguez.libs.entity_system.systems.inventory;

import net.estebanrodriguez.libs.entity_system.components.gear.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by spoooon on 6/2/17.
 */

public class EquipSystem {

    private static List<OnEquipChangeListener> sListeners = new ArrayList<>();





    public static boolean equip(GameEntity character, GameEntity gear){

        if(character.has(GearComponent.COMPONENT_NAME)&& isEquippable(gear)){

            GearComponent gearComponent = (GearComponent) character.get(GearComponent.COMPONENT_NAME);

            if(gear.has(WeaponComponent.COMPONENT_NAME)){
               return equipWeapon(character, gearComponent, gear);


            }else if(gear.has(ArmorComponent.COMPONENT_NAME)){
                ArmorComponent armorComponent = (ArmorComponent) gear.get(ArmorComponent.COMPONENT_NAME);
                BodyPart bodyPart = armorComponent.getBodyPart();
                gearComponent.getEquippedArmor().put(bodyPart,  gear);
                notifyListeners(character, gear);
                return true;
            }
        }
        return false;
    }

    public static boolean UnEquip(GameEntity character, GameEntity gear){
        if(character.has(GearComponent.COMPONENT_NAME)){
            GearComponent gearComponent = (GearComponent)character.get(GearComponent.COMPONENT_NAME);

            if(gear.has(WeaponComponent.COMPONENT_NAME)){
                gearComponent.getEquippedWeapons().remove(gear);
                notifyListeners(character, gear);
                return true;
            }else if(gear.has(ArmorComponent.COMPONENT_NAME)){
                gearComponent.getEquippedArmor().remove(gear);
                notifyListeners(character, gear);
                return true;
            }
        }
        return false;
    }


    public static boolean equipWeapon(GameEntity character, GearComponent gearComponent, GameEntity weapon){

        int weaponMax = gearComponent.getWeaponSlots();
        int availableSlots = weaponMax - gearComponent.getEquippedWeapons().size();
        WeaponComponent weaponComponent = (WeaponComponent) weapon.get(WeaponComponent.COMPONENT_NAME);
        int weaponSize = weaponComponent.getWeaponSize();

        if(weaponMax > availableSlots  && weaponMax > weaponSize){

            if(weaponMax < weaponSize + availableSlots){
                Set<BodyPart> bodyParts = gearComponent.getEquippedWeapons().keySet();
                bodyParts.
            }

            BodyPart bodyPart = weaponComponent.getBodyPart();
            gearComponent.getEquippedWeapons().put(bodyPart, weapon);
            notifyListeners(character, weapon);
            return true;
        } else

    }

    public static boolean isEquippable(GameEntity gear){
        return gear.has(WeaponComponent.COMPONENT_NAME)
                || gear.has(ArmorComponent.COMPONENT_NAME);
    }


    private static void notifyListeners(GameEntity character, GameEntity gear){
        for(OnEquipChangeListener listener: sListeners){
            listener.onEquipChange(character,gear);
        }
    }

    private static void addOnEquipChangeListener(OnEquipChangeListener listener){
        if(!sListeners.contains(listener)){
            sListeners.add(listener);
        }
    }

    private static void removeOnEquipChangeListener(OnEquipChangeListener listener){
        if(sListeners.contains(listener)){
            sListeners.remove(listener);
        }

    }


}
