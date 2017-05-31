package net.estebanrodriguez.libs.entity_system.components.characters.common;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;

/**
 * Created by spoooon on 5/26/17.
 */

public class LevelComponent extends EntityComponent {

    private int level;

    public LevelComponent(int level) {
        super(LEVEL_COMPONENT);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
