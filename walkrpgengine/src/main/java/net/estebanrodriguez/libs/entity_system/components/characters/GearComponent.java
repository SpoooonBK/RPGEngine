package net.estebanrodriguez.libs.entity_system.components.characters;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.gear.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by spoooon on 5/30/17.
 */

public class GearComponent extends EntityComponent {

    public static final int STANDARD_WEAPON_SLOTS = 3;
    public static final String COMPONENT_NAME = GearComponent.class.getSimpleName();
    private Map<BodyPart, GameEntity> mEquippedWeapons = new HashMap<>();
    private Map<BodyPart, GameEntity> mEquippedArmor = new HashMap<>();
    private int mTotalWeaponSlots;


    public GearComponent(){
        super(COMPONENT_NAME);
        mTotalWeaponSlots = STANDARD_WEAPON_SLOTS;
    }
    public GearComponent(int weaponSlots) {
        super(COMPONENT_NAME);
        mTotalWeaponSlots = weaponSlots;
    }


    public Map<BodyPart, GameEntity> getEquippedWeapons() {
        return mEquippedWeapons;
    }

    public Map<BodyPart, GameEntity> getEquippedArmor() {
        return mEquippedArmor;
    }

    public int getTotalWeaponSlots() {
        return mTotalWeaponSlots;
    }

    public void setTotalWeaponSlots(int totalWeaponSlots) {
        this.mTotalWeaponSlots = totalWeaponSlots;
    }

    public List<WeaponComponent> getWeaponComponentList(){

        List<WeaponComponent> weaponList = new ArrayList<>();
            Set<BodyPart> keySet = mEquippedWeapons.keySet();
            for(BodyPart bodyPart: keySet){
                WeaponComponent weaponComponent = (WeaponComponent) mEquippedWeapons.get(bodyPart).get(WeaponComponent.COMPONENT_NAME);
                weaponList.add(weaponComponent);
            }

        return weaponList;
    }

    public List<ArmorComponent> getArmorComponentList(){

        List<ArmorComponent> armorList = new ArrayList<>();
        Set<BodyPart> keySet = mEquippedArmor.keySet();
        for(BodyPart bodyPart: keySet){
            ArmorComponent armorComponent= (ArmorComponent) mEquippedArmor.get(bodyPart).get(ArmorComponent.COMPONENT_NAME);
            armorList.add(armorComponent);
        }

        return armorList;
    }

    @Override
    public String toString() {


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Weapons:\n");
        for (BodyPart bodyPart: BodyPart.values()){
            if(!mEquippedWeapons.containsKey(bodyPart)){
                stringBuilder.append(bodyPart.toString() + ": none\n" );
            }else {
                GameEntity weapon = mEquippedArmor.get(bodyPart);
                WeaponComponent weaponComponent = (WeaponComponent) weapon.get(WeaponComponent.COMPONENT_NAME);
                stringBuilder.append(bodyPart.toString() + ": " + weaponComponent.getName() + "\n");
            }
        }

        stringBuilder.append("Armor:\n");

        for(BodyPart bodyPart: BodyPart.values()){
            if(!mEquippedArmor.containsKey(bodyPart)){
                stringBuilder.append(bodyPart.toString() + ": none\n" );
            } else{
                GameEntity armor = mEquippedArmor.get(bodyPart);
                ArmorComponent armorComponent = (ArmorComponent) armor.get(ArmorComponent.COMPONENT_NAME);
                stringBuilder.append(bodyPart.toString() + ": " + armorComponent.getName() + "\n");
            }
        }

        return stringBuilder.toString();
    }
}
