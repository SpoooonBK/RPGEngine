package net.estebanrodriguez.libs.entity_system.systems.inventory;

import net.estebanrodriguez.libs.entity_system.components.characters.BodyComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.equipment.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.equipment.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.components.equipment.WeaponSlotComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;

/**
 * Created by spoooon on 6/2/17.
 */

public class EquipSystem {


    private Entity mCharacter;
    private BodyComponent mBodyComponent;
    private int mTotalWeaponSlots;

    public EquipSystem() {
    }


    public void equip(Entity character, Entity equipment){
        if(character.has(BodyComponent.COMPONENT_NAME)){
            mCharacter = character;
            setBodyComponent();

            if(equipment.has(WeaponComponent.COMPONENT_NAME)){
                equipWeapon(equipment);
            }else if(equipment.has(ArmorComponent.COMPONENT_NAME)){
                equipArmor(equipment);
            }
        }
    }


    private void setBodyComponent(){
        mBodyComponent = (BodyComponent) mCharacter.get(BodyComponent.COMPONENT_NAME);
    }

    public void unequip(Entity character, Entity equipment){
        if(character.has(BodyComponent.COMPONENT_NAME)) {
            setBodyComponent();
            mBodyComponent.unequip(equipment);
        }
    }


    private void equipWeapon(Entity weapon){

        int totalWeaponSlots = getTotalWeaponSlots();
        int totalWeaponsEquipped = mBodyComponent.getTotalWeaponsEquipped();
        WeaponComponent weaponComponent = (WeaponComponent) weapon.get(WeaponComponent.COMPONENT_NAME);
        BodyPart.Part part = weaponComponent.getPart();

        if(totalWeaponSlots > totalWeaponsEquipped){
            BodyPart bodyPart = mBodyComponent.getBodyPart(part);
            bodyPart.equipWeapon(weapon);
        } else {
            swapWeapon(weapon);
        }
    }


    private int getTotalWeaponSlots(){
        if(mCharacter.has(WeaponSlotComponent.COMPONENT_NAME)){
            WeaponSlotComponent weaponSlotComponent =
                    (WeaponSlotComponent) mCharacter.get(WeaponSlotComponent.COMPONENT_NAME);
            return weaponSlotComponent.getTotalWeaponSlots();
        }else return 0;
    }


    private void swapWeapon(Entity weapon){
        unequipRandomWeapon();
        equipWeapon(weapon);
    }


    private void unequipRandomWeapon(){
        BodyPart bodyPart = mBodyComponent.getRandomBodyPart();
        bodyPart.unequipWeapon();
    }


    private void equipArmor(Entity armor){
        ArmorComponent armorComponent = (ArmorComponent) armor.get(ArmorComponent.COMPONENT_NAME);
        BodyPart.Part part = armorComponent.getPart();
        BodyPart bodyPart = mBodyComponent.getBodyPart(part);
        bodyPart.equipArmor(armor);
    }

}
