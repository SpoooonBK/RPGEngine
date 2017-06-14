package net.estebanrodriguez.libs.entity_system.components.modifiers;

import net.estebanrodriguez.libs.entity_system.components.Component;

/**
 * Created by spoooon on 6/12/17.
 */

public class ModifierComponent extends Component {

    public final static String COMPONENT_NAME = ModifierComponent.class.getSimpleName();
    private final int mValue;
    private final ModifierType mModifierType;


    public ModifierComponent(ModifierType modifierType, int modifierValue) {
        super(COMPONENT_NAME);
        mModifierType = modifierType;
        mValue = modifierValue;
    }

    public int getValue() {
        return mValue;
    }

    public ModifierType getModifierType() {
        return mModifierType;
    }
}
