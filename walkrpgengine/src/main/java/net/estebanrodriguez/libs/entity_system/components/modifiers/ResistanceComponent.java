package net.estebanrodriguez.libs.entity_system.components.modifiers;

import net.estebanrodriguez.libs.entity_system.components.Component;
import net.estebanrodriguez.libs.entity_system.systems.combat.DamageType;

/**
 * Created by spoooon on 6/12/17.
 */

public final class ResistanceComponent extends Component {

    private final static String COMPONENT_NAME = ResistanceComponent.class.getSimpleName();
    private final DamageType mDamageType;
    private final int mValue;

    public ResistanceComponent(DamageType damageType, int resistance) {
        super(COMPONENT_NAME);
        mDamageType = damageType;
        mValue = resistance;
    }

    public DamageType getDamageType() {
        return mDamageType;
    }

    public int getValue() {
        return mValue;
    }
}
