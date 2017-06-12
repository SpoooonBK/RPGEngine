package net.estebanrodriguez.libs.entity_system.components.gear;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.systems.combat.DamageType;

/**
 * Created by spoooon on 5/30/17.
 */

public class ArmorComponent extends EntityComponent {

    public final static String COMPONENT_NAME = ArmorComponent.class.getSimpleName();
    private String mName;
    private BodyPart mBodyPart;
    private int mArmorClass;
    private DamageType mDamageTypeResistance;
    private int mDamageResistance;


    ArmorComponent(){
        this("Sweaty T", BodyPart.TORSO, 1);
    }

    public ArmorComponent(String name, BodyPart bodyPart, int armorClass) {
        super(COMPONENT_NAME);
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
