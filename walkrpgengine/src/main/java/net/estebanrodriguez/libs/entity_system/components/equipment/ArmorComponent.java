package net.estebanrodriguez.libs.entity_system.components.equipment;

import net.estebanrodriguez.libs.entity_system.components.Component;
import net.estebanrodriguez.libs.entity_system.components.characters.BodyPart;

/**
 * Created by spoooon on 5/30/17.
 */

public final class ArmorComponent extends Component {

    public final static String COMPONENT_NAME = ArmorComponent.class.getSimpleName();
    private String mName;
    private BodyPart.Part mPart;
    private int mArmorClass;


    public ArmorComponent(String name, BodyPart.Part part, int armorClass) {
        super(COMPONENT_NAME);
        mName = name;
        mPart = part;
        mArmorClass = armorClass;
    }

    public String getName() {
        return mName;
    }

    public BodyPart.Part getPart() {
        return mPart;
    }

    public int getArmorClass() {
        return mArmorClass;
    }

}
