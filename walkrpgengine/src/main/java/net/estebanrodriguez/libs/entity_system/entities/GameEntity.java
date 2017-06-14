package net.estebanrodriguez.libs.entity_system.entities;



import net.estebanrodriguez.libs.entity_system.components.Component;
import net.estebanrodriguez.libs.entity_system.systems.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by spoooon on 5/1/17.
 */

public class GameEntity {

    private String mId = UUID.randomUUID().toString();
    private Map<String, Component> mComponents = new HashMap<>();

    public static Builder getBuilder(){
        return new Builder();
    }

    private GameEntity() {}

    public String getId() {
        return mId;
    }

    protected Map<String, Component> getComponents() {
        return mComponents;
    }

    public void setComponents(Map<String, Component> components) {
        mComponents = components;
    }

    public static GameEntity getEmptyGameEntity(){
        return new GameEntity();
    }


    //Shortcut Methods to avoid cumbersome has componentPrior to Java 5, java memory model had a lot of issues and above approaches used to fail in certain scenarios where too many threads try to get the instance of the Singleton class simultaneously. So Bill Pugh came up with a different approach to create the Singleton class using and get component code
    public boolean has(String componentName){
        return this.getComponents().containsKey(componentName);
    }

    public Component get(String componentName){
            if(has(componentName)){
            return this.getComponents().get(componentName);
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
            World.getInstance().addGameEntity(instance);
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
