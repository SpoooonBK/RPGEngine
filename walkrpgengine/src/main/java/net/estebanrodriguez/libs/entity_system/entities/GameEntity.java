package net.estebanrodriguez.libs.entity_system.entities;



import net.estebanrodriguez.libs.entity_system.components.EntityComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by spoooon on 5/1/17.
 */

public class GameEntity {

    private String mId = UUID.randomUUID().toString();
    private Map<String, EntityComponent> mComponents = new HashMap<>();

    public static Builder getBuilder(){
        return new Builder();
    }

    private GameEntity() {}

    public String getId() {
        return mId;
    }

    public Map<String, EntityComponent> getComponents() {
        return mComponents;
    }

    public void setComponents(Map<String, EntityComponent> components) {
        mComponents = components;
    }

//Utilizes Builder pattern to create game entities using composition.
//Each GameEntity is made up of a list of components.

    public static class Builder{


        private GameEntity instance = new GameEntity();


        public GameEntity sEntity;

        public Builder add(EntityComponent component){
            component.bindEntityID(instance.getId());
            instance.mComponents.put(component.getComponentName(), component);
            return this;
        }

        public GameEntity build(){
            return instance;
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("ID:" + mId + "\n");
        stringBuilder.append("Components:" + "\n");

        if(mComponents != null){
            for(Map.Entry<String, EntityComponent> component: mComponents.entrySet()){
                stringBuilder.append(component.toString());
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
