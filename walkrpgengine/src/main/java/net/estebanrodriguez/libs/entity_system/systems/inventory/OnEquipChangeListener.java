package net.estebanrodriguez.libs.entity_system.systems.inventory;

import net.estebanrodriguez.libs.entity_system.entities.Entity;

/**
 * Created by spoooon on 6/2/17.
 */

public interface OnEquipChangeListener {

    void onEquipChange(Entity character, Entity gear);
}
