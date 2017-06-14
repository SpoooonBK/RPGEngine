package net.estebanrodriguez.libs.entity_system.components.equipment;

import net.estebanrodriguez.libs.entity_system.components.Component;

/**
 * Created by spoooon on 6/13/17.
 */

public class OwnerComponent extends Component {

    public static final String COMPONENT_NAME = OwnerComponent.class.getSimpleName();

    private int mOwnerID;

    public OwnerComponent() {
        super(COMPONENT_NAME);
    }

    public int getOwnerID() {
        return mOwnerID;
    }

    public void setOwnerID(int ownerID) {
        mOwnerID = ownerID;
    }
}
