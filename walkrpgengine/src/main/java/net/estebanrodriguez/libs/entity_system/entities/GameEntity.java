package net.estebanrodriguez.libs.entity_system.entities;



import net.estebanrodriguez.libs.entity_system.components.Component;
import net.estebanrodriguez.libs.entity_system.systems.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by spoooon on 5/1/17.
 */

public class GameEntity implements Entity{

    private String mId = UUID.randomUUID().toString();
    private Map<String, Component> mComponents = new HashMap<>();

    private GameEntity() {}

    public static Builder getBuilder(){
        return new Builder();
    }

    public String getId() {
        return mId;
    }

    public static Entity getEmptyEntity(){
        return new GameEntity();
    }


    public boolean has(String componentName){
        return mComponents.containsKey(componentName);
    }

    public Component get(String componentName){
            if(has(componentName)){
            return mComponents.get(componentName);
        }else throw new IllegalArgumentException("GameEntity does not have " + componentName);
    }

//Utilizes Builder pattern to create game entities using composition.
//Each GameEntity is made up of a list of components.

    public static class Builder{



        private Builder(){}

        private GameEntity instance = new GameEntity();

        public Builder add(Component component){
            component.bindEntityID(instance.getId());
            instance.mComponents.put(component.getComponentName(), component);
            return this;
        }

        public GameEntity build(){
            World.getInstance().addEntity(instance);
            return instance;
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("ID:" + mId + "\n");
        stringBuilder.append("Components:" + "\n");

        if(mComponents != null){
            for(Map.Entry<String, Component> component: mComponents.entrySet()){
                stringBuilder.append(component.toString());
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
