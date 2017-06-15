package net.estebanrodriguez.libs.utilities;

import net.estebanrodriguez.libs.entity_system.entities.Entity;

/**
 * Created by spoooon on 6/13/17.
 */

public interface EntityContainer {

    void add(Entity gameEntity);
    Entity getById(String id);

}
