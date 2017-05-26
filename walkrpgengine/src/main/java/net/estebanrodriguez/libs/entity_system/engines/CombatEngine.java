package net.estebanrodriguez.libs.entity_system.engines;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import java.util.List;

/**
 * Created by spoooon on 5/26/17.
 */

public class CombatEngine {

    private CombatEngine(){}

    private static class CombatEngineHelper{
        private  static final CombatEngine INSTANCE = new CombatEngine();
    }

    public static CombatEngine getInstance(){
        return CombatEngineHelper.INSTANCE;
    }

    private List<GameEntity> mGameEntities;

    public void setCombatants(List<GameEntity> gameEntities){
        mGameEntities = gameEntities;
    }

    public void fight(){
        if(checkIfCharacters()){

        }
    }


    //Checks to see if GameEntities has character and stat components.  Only GameEntities with each
    //may fight
    private boolean checkIfCharacters() {

            for(GameEntity gameEntity: mGameEntities){
                boolean isCharacter
                        = gameEntity.getComponents().containsKey(CharacterComponent.CHARACTER_COMPONENT);
                boolean hasStats
                        = gameEntity.getComponents().containsKey(StatsComponent.STATS_COMPONENT);
                if(!isCharacter || !hasStats){
                    return false;
                }
            }
        return true;
    }
}
