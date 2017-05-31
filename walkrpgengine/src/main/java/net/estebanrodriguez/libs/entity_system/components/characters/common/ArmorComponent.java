package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.engines.combat.DamageType;

/**
 * Created by spoooon on 5/30/17.
 */

public class ArmorComponent extends EntityComponent {

    String mName;
    BodyPart mBodyPart;
    int mArmorClass;
    DamageType mDamageTypeResistance;
    int mDamageResistance;


    ArmorComponent(){
        this("Sweaty T", BodyPart.TORSO, 1);
    }

    public ArmorComponent(String name, BodyPart bodyPart, int armorClass) {
        super(ARMOR_COMPONENT);
        mName = name;
        mBodyPart = bodyPart;
        mArmorClass = armorClass;
    }

    public String getName() {
        return mName;
    }

    public BodyPart getBodyPart() {
        return mBodyPart;
    }

    public int getArmorClass() {
        return mArmorClass;
    }

    public DamageType getDamageTypeResistance() {
        return mDamageTypeResistance;
    }

    public int getDamageResistance() {
        return mDamageResistance;
    }
}
