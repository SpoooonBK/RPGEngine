package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.components.characters.stats.attributes.StatComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;

/**
 * Created by spoooon on 6/17/17.
 */

public class HitCalculator {

    public static boolean checkForHit(Entity attacker, Entity defender){

        calculateHitChance(attacker);
//        calculateDefense(defender);
//        rollForHit();
        return true;
    }

    private static void calculateHitChance(Entity attacker) {
        StatComponent statComponent;


        if(attacker.has(StatComponent.COMPONENT_NAME)){
            statComponent = (StatComponent) attacker.get(StatComponent.COMPONENT_NAME);

        }
    }

//
//    private static boolean isValidAttacker(GameEntity gameEntity){
//
//    }
}
