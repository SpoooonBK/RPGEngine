package net.estebanrodriguez.libs.entity_system.components;

/**
 * Created by spoooon on 5/1/17.
 */

public abstract class EntityComponent {

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

