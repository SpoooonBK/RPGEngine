package net.estebanrodriguez.libs.entity_system.factories;

import net.estebanrodriguez.libs.utilities.EntityContainer;

/**
 * Created by spoooon on 6/15/17.
 */

public interface EntityContainerFactory {

    EntityContainer createEntityContainer(int size);

}
