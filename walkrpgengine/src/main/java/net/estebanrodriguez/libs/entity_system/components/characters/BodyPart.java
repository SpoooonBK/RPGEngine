package net.estebanrodriguez.libs.entity_system.components.characters;

import net.estebanrodriguez.libs.entity_system.entities.Entity;

/**
 * Created by spoooon on 6/13/17.
 */

public class BodyPart {

    private final Part mPart;
    private String mWeaponId;
    private String mArmorId;

    public BodyPart(Part part) {
        mPart = part;
    }

    public boolean hasWeapon(){
        return mWeaponId != null;
    }

    public boolean hasArmor(){
        return mArmorId != null;
    }

    public void unequip(String id){
        if(id.equals(mArmorId)){
            unequipArmor();
        }
        if(id.equals(mWeaponId)){
            unequipWeapon();
        }
    }

    public void unequipArmor(){
        mArmorId = null;
    }

    public void unequipWeapon(){
        mWeaponId = null;
    }

    public void equipWeapon(Entity weapon){
        mWeaponId = weapon.getId();
    }

    public void equipArmor(Entity armor){
        mArmorId = armor.getId();
    }

    public Part getPart() {
        return mPart;
    }

    public String getWeaponId() {
        return mWeaponId;
    }

    public String getArmorId() {
        return mArmorId;
    }

    /**
     * Created by spoooon on 5/30/17.
     */

    public static enum Part {

        HEAD, NECK, WRISTS, LEGS, ARMS, TORSO, EARRINGS, BACK, FEET, LEFT_FINGERS, RIGHT_FINGERS, HANDS, LEFT_HAND, RIGHT_HAND, ARM_PIT, GROIN, TAIL, MOUTH
    }
}
