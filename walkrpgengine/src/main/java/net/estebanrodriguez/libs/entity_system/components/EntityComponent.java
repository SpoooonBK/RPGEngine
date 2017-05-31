package net.estebanrodriguez.libs.entity_system.components;

/**
 * Created by spoooon on 5/1/17.
 */

public abstract class EntityComponent {

    public final static String ARMOR_COMPONENT = "armor_component";
    public static final String STATS_COMPONENT = "stats_component";
    public static final String CHARACTER_COMPONENT = "character_component";
    public static final String COMBAT_COMPONENT = "combat_component";
    public static final String EXPERIENCE_COMPONENT = "experience_component";
    public static final String HEROIC_TYPE_COMPONENT = "heroic_type_component";
    public static final String LEVEL_COMPONENT = "level_component";
    public static final String WEAPON_COMPONENT = "weapon_component";
    private String mComponentName;
    private String mEntityID;

    protected EntityComponent(String componentName) {
        this.mComponentName = componentName;
    }

    public String getComponentName() {
        return mComponentName;
    }

    public String getEntityID() {
        return mEntityID;
    }

    public void bindEntityID(String entityID) {
        mEntityID = entityID;
    }
}

