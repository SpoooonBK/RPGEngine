package net.estebanrodriguez.libs.entity_system.systems.inventory;

import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 6/2/17.
 */

public interface OnEquipChangeListener {

    void onEquipChange(GameEntity character, GameEntity gear);
}
