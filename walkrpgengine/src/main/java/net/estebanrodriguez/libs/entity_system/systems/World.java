package net.estebanrodriguez.libs.entity_system.systems;

import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spoooon on 6/13/17.
 */

public class World {



    private Map<String, GameEntity> mGameEntityMap = new HashMap<>();

    private World(){}

    private static class SingletonHelper{
        private static final World INSTANCE = new World();
    }

    public static World getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public void addGameEntity(GameEntity gameEntity){

    }

    public GameEntity getGameEntity(String id){
        if(mGameEntityMap.containsKey(id)){
            return mGameEntityMap.get(id);
        }else return GameEntity.getEmptyGameEntity();
    }

    public void removeGameEntity(String id){
        if(mGameEntityMap.containsKey(id)){
            mGameEntityMap.remove(id);
        }
    }


}
