package net.estebanrodriguez.libs.entity_system.components.gear;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.systems.combat.DamageType;
import net.estebanrodriguez.libs.utilities.Die;

/**
 * Created by spoooon on 5/30/17.
 */

public class WeaponComponent extends EntityComponent {

    public static final String COMPONENT_NAME = WeaponComponent.class.getSimpleName();
    private final String mName;
    private final BodyPart mBodyPart;
    private final Die.StandardDie mBaseDie;
    private final int mDieMultiplier;
    private int mAttackModifier; //added to any Attack Roll
    private int mDamageModifier; //added to any Damage Roll
    private int mDefenseModifier; //added to any Defense Rolls
    private final DamageType mDamageType;
    private final WeaponSize mWeaponSize;


    public WeaponComponent(String name, BodyPart bodyPart, Die.StandardDie baseDie, int dieMultiplier, WeaponSize weaponSize, DamageType damageType) {
        super(COMPONENT_NAME);
        mName = name;
        mBodyPart = bodyPart;
        mBaseDie = baseDie;
        mDieMultiplier = dieMultiplier;
        mWeaponSize = weaponSize;
        mDamageType = damageType;
    }

    public String getName() {
        return mName;
    }

    public BodyPart getBodyPart() {
        return mBodyPart;
    }

    public Die.StandardDie getBaseDie() {
        return mBaseDie;
    }

    public int getDieMultiplier() {
        return mDieMultiplier;
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

    public int getWeaponSize() {
        return mWeaponSize.getSize();
    }




    /**
     * Created by spoooon on 6/12/17.
     */

    public static enum WeaponSize {

        NORMAL(1),
        LARGE(2),
        EXTRA_LARGE(3),
        MASSIVE(4);

        private final int mSize;

        WeaponSize(int size) {
            mSize = size;
        }

        public int getSize() {
            return mSize;
        }
    }
}
