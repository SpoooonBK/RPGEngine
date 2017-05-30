package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 5/30/17.
 */

public class CombatComponent extends EntityComponent {

    public static final String COMBAT_COMPONENT = "combat_component";

    public CombatComponent() {
        super(COMBAT_COMPONENT);
    }
}
