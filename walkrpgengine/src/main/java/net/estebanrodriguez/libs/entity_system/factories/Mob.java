package net.estebanrodriguez.libs.entity_system.factories;

import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.entities.CharacterManager;

import java.util.List;

/**
 * Created by spoooon on 6/1/17.
 * A Mob is a collection of GameEntities that can participate in combat.  They are particularly
 * used as combatants against the player character
 */

public class Mob {

    private List<GameEntity> mGameEntities;
    private int mTotalLevels;


    public Mob(List<GameEntity> gameEntities) {
        mGameEntities = gameEntities;
    }

    public List<GameEntity> getGameEntities() {
        return mGameEntities;
    }

    public boolean addGameEntityToMob(GameEntity gameEntity){
        if (CharacterManager.canFight(gameEntity)){
             return mGameEntities.add(gameEntity);
        }
        return false;
    }

    public int getTotalLevels() {
        return mTotalLevels;
    }

    private int size(){
        return mGameEntities.size();
    }

    private void calcuateTotalLevels(){
        mTotalLevels = 0;
        for(GameEntity gameEntity: mGameEntities){
            mTotalLevels = mTotalLevels + CharacterManager.getLevel(gameEntity);
        }
    }
}
