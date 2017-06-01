package net.estebanrodriguez.libs.entity_system.factories;

import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

/**
 * Created by spoooon on 5/31/17.
 */

public abstract interface GameEntityFactory  {

    public GameEntity createGameEntity();

}
