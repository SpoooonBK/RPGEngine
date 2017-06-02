package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;

/**
 * Created by spoooon on 5/30/17.
 */

public class CombatComponent extends EntityComponent {

    public static final String COMPONENT_NAME = CombatComponent.class.getSimpleName();

    public CombatComponent() {
        super(COMPONENT_NAME);
    }
}
