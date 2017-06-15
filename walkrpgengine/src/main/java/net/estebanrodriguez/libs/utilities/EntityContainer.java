package net.estebanrodriguez.libs.utilities;

import net.estebanrodriguez.libs.entity_system.entities.Entity;

import java.util.List;

/**
 * Created by spoooon on 6/13/17.
 */

public interface EntityContainer {

    void add(Entity gameEntity);
    Entity getById(String id);
    List<Entity> getEntities();
    int size();

}
