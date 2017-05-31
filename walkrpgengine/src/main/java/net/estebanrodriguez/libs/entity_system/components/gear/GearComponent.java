package net.estebanrodriguez.libs.entity_system.components.gear;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.BodyPart;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

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
        if(isWeapon(gear)){
            mMainWeapon = gear;
        }
        if (isArmor(gear)) {
            ArmorComponent armorComponent = (ArmorComponent) gear.getComponents().get(ARMOR_COMPONENT);
            mEquippedWeapons.put(armorComponent.getBodyPart(), gear);
        }

    }

    public void unequip(GameEntity gear){

    }

    public boolean isEquippable(GameEntity gear){
        return (isWeapon(gear) || isArmor(gear));
    }

    public boolean isWeapon(GameEntity gear){
        return gear.getComponents().containsKey(WEAPON_COMPONENT);
    }

    public boolean isArmor(GameEntity gear){
        return gear.getComponents().containsKey(ARMOR_COMPONENT);
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
                ArmorComponent armorComponent = (ArmorComponent)armor.getComponents().get(ARMOR_COMPONENT);
                stringBuilder.append(bodyPart.toString() + ": " + armorComponent.getName() + "\n");
            }
        }

        if(mMainWeapon != null){
            WeaponComponent weaponComponent = (WeaponComponent) mMainWeapon.getComponents().get(WEAPON_COMPONENT);
            stringBuilder.append(weaponComponent.getBodyPart().toString() + ": " + weaponComponent.getName() + "/n");
        }

        return super.toString();
    }
}
