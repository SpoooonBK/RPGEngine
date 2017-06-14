package net.estebanrodriguez.libs.entity_system.components.equipment;

import net.estebanrodriguez.libs.entity_system.components.Component;
import net.estebanrodriguez.libs.entity_system.components.characters.BodyPart;
import net.estebanrodriguez.libs.entity_system.systems.combat.DamageType;
import net.estebanrodriguez.libs.utilities.Die;

/**
 * Created by spoooon on 5/30/17.
 */

public class WeaponComponent extends Component {

    public static final String COMPONENT_NAME = WeaponComponent.class.getSimpleName();
    private final String mName;
    private final BodyPart.Part mPart;
    private final Die.StandardDie mBaseDie;
    private final int mDieMultiplier;

    private final DamageType mDamageType;



    public WeaponComponent(String name, BodyPart.Part part, Die.StandardDie baseDie, int dieMultiplier, DamageType damageType) {
        super(COMPONENT_NAME);
        mName = name;
        mPart = part;
        mBaseDie = baseDie;
        mDieMultiplier = dieMultiplier;
        mDamageType = damageType;
    }

    public String getName() {
        return mName;
    }

    public BodyPart.Part getPart() {
        return mPart;
    }


    public Die.StandardDie getBaseDie() {
        return mBaseDie;
    }

    public int getDieMultiplier() {
        return mDieMultiplier;
    }
    public DamageType getDamageType() {
        return mDamageType;
    }






}
