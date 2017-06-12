package net.estebanrodriguez.libs.entity_system.systems.inventory;

import net.estebanrodriguez.libs.entity_system.components.gear.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by spoooon on 6/2/17.
 */

public class Equipper {


    private GameEntity mCharacter;
    private GameEntity mGear;
    private GearComponent mGearComponent;

    public Equipper() {
    }


    public void equip(GameEntity character, GameEntity gear){
        if(canEquip(character, gear)){
            mCharacter = character;
            mGear = gear;
            mGearComponent = (GearComponent) character.get(GearComponent.COMPONENT_NAME);

            if(isWeapon()){
                equipWeapon();
            }else {
                equipArmor();
            }
        }
    }

    public void unequip(GameEntity character, GameEntity gear){
        if(character.has(GearComponent.COMPONENT_NAME)){

            if(isWeapon()){
                mGearComponent.getEquippedWeapons().remove(gear);

            }else if(gear.has(ArmorComponent.COMPONENT_NAME)){
                mGearComponent.getEquippedArmor().remove(gear);
            }
        }
    }

    public void unequipRandomWeapon(){
        Map<BodyPart, GameEntity> weapons = mGearComponent.getEquippedWeapons();
        Set<BodyPart> bodyParts = weapons.keySet();
        Iterator<BodyPart> iterator = bodyParts.iterator();
        weapons.remove(iterator.next());
    }


    private void equipWeapon(){

        int availableWeaponSlots = mGearComponent.getAvailableWeaponSlots();

        WeaponComponent weaponComponent = (WeaponComponent) mGear.get(WeaponComponent.COMPONENT_NAME);
        BodyPart bodyPart = weaponComponent.getBodyPart();

        if(availableWeaponSlots > 0){
            mGearComponent.getEquippedWeapons().put(bodyPart, mGear);
        } else {
            swapWeapon(bodyPart);
        }
    }


    private void swapWeapon(BodyPart bodyPart){

        if(mGearComponent.isBodyPartOccupiedByWeapon(bodyPart)){
            mGearComponent.getEquippedWeapons().put(bodyPart, mGear);
        } else{
            unequipRandomWeapon();
            mGearComponent.getEquippedWeapons().put(bodyPart, mGear);
        }
    }


    private void equipArmor(){
        ArmorComponent armorComponent = (ArmorComponent) mGear.get(ArmorComponent.COMPONENT_NAME);
        BodyPart bodyPart = armorComponent.getBodyPart();
        mGearComponent.getEquippedArmor().put(bodyPart, mGear);
    }



    public boolean isEquippable(GameEntity gear){
        return gear.has(WeaponComponent.COMPONENT_NAME)
                || gear.has(ArmorComponent.COMPONENT_NAME);
    }


    private boolean isWeapon(){
        return mGear.has(WeaponComponent.COMPONENT_NAME);
    }

    private boolean isArmor(){
        return mGear.has(ArmorComponent.COMPONENT_NAME);
    }

    private boolean canEquip(GameEntity character, GameEntity gear){
        return (character.has(GearComponent.COMPONENT_NAME) && isEquippable(gear));
    }








}
