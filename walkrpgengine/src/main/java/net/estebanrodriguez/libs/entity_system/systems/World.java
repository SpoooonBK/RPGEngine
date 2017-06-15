package net.estebanrodriguez.libs.entity_system.systems;

import net.estebanrodriguez.libs.entity_system.entities.Entity;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spoooon on 6/13/17.
 */

public class World {



    private Map<String, Entity> mEntityMap = new HashMap<>();

    private World(){}

    private static class SingletonHelper{
        private static final World INSTANCE = new World();
    }

    public static World getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public void addEntity(Entity entity){

    }

    public Entity getEntity(String id){
        if(mEntityMap.containsKey(id)){
            return mEntityMap.get(id);
        }else return GameEntity.getEmptyEntity();
    }

    public void removeGameEntity(String id){
        if(mEntityMap.containsKey(id)){
            mEntityMap.remove(id);
        }
    }


}
