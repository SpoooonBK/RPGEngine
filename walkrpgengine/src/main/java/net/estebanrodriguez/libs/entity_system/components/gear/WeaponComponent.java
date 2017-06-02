package net.estebanrodriguez.libs.entity_system.components.gear;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.systems.combat.DamageType;
import net.estebanrodriguez.libs.utilities.Dice;

/**
 * Created by spoooon on 5/30/17.
 */

public class WeaponComponent extends EntityComponent {

    public static final String COMPONENT_NAME = WeaponComponent.class.getSimpleName();
    private String mName;
    private BodyPart mBodyPart;
    private Dice mBaseDie;
    private int mDieMultiplier;
    private int mAttackModifier;
    private int mDamageModifier;
    private int mDefenseModifier;
    private DamageType mDamageType;
    private int mWeaponSize;


    public WeaponComponent(String name, BodyPart bodyPart, Dice baseDie, int dieMultiplier, int weaponSize) {
        super(COMPONENT_NAME);
        mName = name;
        mBodyPart = bodyPart;
        mBaseDie = baseDie;
        mDieMultiplier = dieMultiplier;
        mWeaponSize = weaponSize;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public BodyPart getBodyPart() {
        return mBodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        mBodyPart = bodyPart;
    }

    public Dice getBaseDie() {
        return mBaseDie;
    }

    public void setBaseDie(Dice baseDie) {
        mBaseDie = baseDie;
    }

    public int getDieMultiplier() {
        return mDieMultiplier;
    }

    public void setDieMultiplier(int dieMultiplier) {
        mDieMultiplier = dieMultiplier;
    }

    public int getAttackModifier() {
        return mAttackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        mAttackModifier = attackModifier;
    }

    public int getDamageModifier() {
        return mDamageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        mDamageModifier = damageModifier;
    }

    public int getDefenseModifier() {
        return mDefenseModifier;
    }

    public void setDefenseModifier(int defenseModifier) {
        mDefenseModifier = defenseModifier;
    }

    public DamageType getDamageType() {
        return mDamageType;
    }

    public void setDamageType(DamageType damageType) {
        mDamageType = damageType;
    }

    public int getWeaponSize() {
        return mWeaponSize;
    }

    public void setWeaponSize(int weaponSize) {
        mWeaponSize = weaponSize;
    }
}
