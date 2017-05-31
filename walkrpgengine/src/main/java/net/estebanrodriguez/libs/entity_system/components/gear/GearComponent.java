package net.estebanrodriguez.libs.entity_system.components.gear;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.BodyPart;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spoooon on 5/30/17.
 */

public class GearComponent extends EntityComponent {

    public static final String GEAR_COMPONENT = "gear_component";

    private Map<BodyPart, GameEntity> mEquippedWeapons = new HashMap<>();
    private Map<BodyPart, GameEntity> mEquippedArmor = new HashMap<>();

    private GameEntity mMainWeapon;
    private GameEntity mSecondaryWeapon;
    private GameEntity mTertiaryWeapon;




    public GearComponent(){
        super(GEAR_COMPONENT);
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

    public void equip(GameEntity gear){

    }

    public void unequip(GameEntity gear){

    }

    public boolean isEquippable(GameEntity gear){
        if(!gear.getComponents().containsKey(EntityComponent.WEAPON_COMPONENT)
                || !gear.getComponents().containsKey(EntityComponent.ARMOR_COMPONENT)){
            return false;
        }
        return true;
    }




}
