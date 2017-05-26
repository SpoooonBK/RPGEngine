package net.estebanrodriguez.libs.entity_system.components;

/**
 * Created by spoooon on 5/1/17.
 */

public abstract class EntityComponent {

    private String componentName;

    protected EntityComponent(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentName() {
        return componentName;
    }
}