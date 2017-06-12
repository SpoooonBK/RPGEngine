package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 6/12/17.
 */

public class Weapons {

    List<WeaponComponent> mWeapons;

    public Weapons(List<WeaponComponent> weapons) {
        mWeapons = new ArrayList<>(weapons);
    }



}
