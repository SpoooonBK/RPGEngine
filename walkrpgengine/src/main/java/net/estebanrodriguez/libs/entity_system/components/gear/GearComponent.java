package net.estebanrodriguez.libs.entity_system.components.gear;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.BodyPart;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.entities.GearManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by spoooon on 5/30/17.
 */

public class GearComponent extends EntityComponent {


    private Map<BodyPart, GameEntity> mEquippedWeapons = new HashMap<>();
    private Map<BodyPart, GameEntity> mEquippedArmor = new HashMap<>();

    private GameEntity mMainWeapon;
    private GameEntity mSecondaryWeapon;
    private GameEntity mTertiaryWeapon;




    public GearComponent(){
        super(GEAR_COMPONENT);
    }

    public GearComponent(GameEntity mainWeapon) {
        super(GEAR_COMPONENT);
        if(isEquippable(mainWeapon)){
            equip(mainWeapon);
        }

    }

    public GameEntity getMainWeapon() {
        return mMainWeapon;
    }

    public GameEntity getSecondaryWeapon() {
        return mSecondaryWeapon;
    }

    public GameEntity getTertiaryWeapon() {
        return mTertiaryWeapon;
    }

    public Map<BodyPart, GameEntity> getEquippedWeapons() {
        return mEquippedWeapons;
    }

    public Map<BodyPart, GameEntity> getEquippedArmor() {
        return mEquippedArmor;
    }

//    TODO Refactor equip method
    public void equip(GameEntity gear){
        if(GearManager.isWeapon(gear)){
            mMainWeapon = gear;
        }
        if (GearManager.isArmor(gear)) {
            ArmorComponent armorComponent = GearManager.getArmorComponent(gear);
            mEquippedWeapons.put(armorComponent.getBodyPart(), gear);
        }

    }

    public void unequip(GameEntity gear){

    }

    public boolean isEquippable(GameEntity gear){
        return (GearManager.isWeapon(gear) || GearManager.isArmor(gear));
    }


    @Override
    public String toString() {


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Armor:\n");

        for(BodyPart bodyPart: BodyPart.values()){
            if(!mEquippedArmor.containsKey(bodyPart)){
                stringBuilder.append(bodyPart.toString() + ": none\n" );
            } else{
                GameEntity armor = mEquippedArmor.get(bodyPart);
                ArmorComponent armorComponent = GearManager.getArmorComponent(armor);
                stringBuilder.append(bodyPart.toString() + ": " + armorComponent.getName() + "\n");
            }
        }

        if(mMainWeapon != null){
            WeaponComponent weaponComponent = GearManager.getWeaponComponent(mMainWeapon);
            stringBuilder.append(weaponComponent.getBodyPart().toString() + ": " + weaponComponent.getName() + "/n");
        }

        return super.toString();
    }
}
