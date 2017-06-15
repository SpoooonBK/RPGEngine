package net.estebanrodriguez.libs.entity_system.entities;

import net.estebanrodriguez.libs.entity_system.components.Component;

/**
 * Created by spoooon on 6/15/17.
 */

public interface Entity {

    boolean has(String componentName);
    Component get(String componentName);
    String getId();
}
