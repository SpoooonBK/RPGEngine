package net.estebanrodriguez.libs.entity_system.components.equipment;

import net.estebanrodriguez.libs.entity_system.components.Component;

/**
 * Created by spoooon on 6/13/17.
 */

public class WeaponSlotComponent extends Component {


    public static final String COMPONENT_NAME = WeaponSlotComponent.class.getSimpleName();
    public static final int STANDARD_WEAPON_SLOTS = 2;
    private final int mTotalWeaponSlots;


    public WeaponSlotComponent() {
        super(COMPONENT_NAME);
        mTotalWeaponSlots = STANDARD_WEAPON_SLOTS;
    }

    public WeaponSlotComponent(int totalWeaponSlots){
        super(COMPONENT_NAME);
        mTotalWeaponSlots = totalWeaponSlots;
    }

    public int getTotalWeaponSlots() {
        return mTotalWeaponSlots;
    }
}
