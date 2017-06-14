package net.estebanrodriguez.libs.utilities;

import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 6/13/17.
 */

public interface EntityContainer {

    void add(GameEntity gameEntity);
    GameEntity getById(String id);

}
