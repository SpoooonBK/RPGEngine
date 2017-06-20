package net.estebanrodriguez.libs.entity_system.systems.inventory;

import net.estebanrodriguez.libs.entity_system.components.characters.BodyComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.equipment.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.equipment.OwnerComponent;
import net.estebanrodriguez.libs.entity_system.components.equipment.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.components.equipment.WeaponSlotComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.utilities.EntityContainer;

import java.util.List;

/**
 * Created by spoooon on 6/2/17.
 */

public class EquipSystem {


    private Entity mCharacter;
    private BodyComponent mBodyComponent;

    public EquipSystem() {
    }


    public void equip(Entity character, Entity gear){
        if(character.has(BodyComponent.COMPONENT_NAME)){
            mCharacter = character;
            setBodyComponent();

            if(gear.has(WeaponComponent.COMPONENT_NAME)){
                equipWeapon(gear);
            }else if(gear.has(ArmorComponent.COMPONENT_NAME)){
                equipArmor(gear);
            }
        }
    }

    public void equipMob(EntityContainer mob, Entity gear){
        List<Entity> entities = mob.getEntities();
        for(Entity entity: entities){
            equip(entity, gear);
        }
    }


    private void setBodyComponent(){
        mBodyComponent = (BodyComponent) mCharacter.get(BodyComponent.COMPONENT_NAME);
    }

    public void unequip(Entity character, Entity gear){
        if(character.has(BodyComponent.COMPONENT_NAME)) {
            setBodyComponent();
            mBodyComponent.unequip(gear);
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
            setOwnership(weapon);
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
        setOwnership(armor);
    }

    private void setOwnership(Entity gear){
        if(gear.has(OwnerComponent.COMPONENT_NAME)){
            OwnerComponent ownerComponent = (OwnerComponent) gear.get(OwnerComponent.COMPONENT_NAME);
            ownerComponent.setOwnerID(mCharacter.getId());
        }
    }



}
