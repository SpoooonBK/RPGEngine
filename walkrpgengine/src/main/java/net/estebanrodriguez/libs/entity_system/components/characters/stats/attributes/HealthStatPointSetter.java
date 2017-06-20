package net.estebanrodriguez.libs.entity_system.components.characters.stats.attributes;

import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.StatPointSetter;

/**
 * Created by spoooon on 6/19/17.
 */

public class HealthStatPointSetter implements StatPointSetter {


    StatComponent mStatComponent;

    public HealthStatPointSetter(StatComponent statComponent) {
        mStatComponent = statComponent;
    }

    @Override
    public void setPoints() {

        int level = mStatComponent.getLevel();
        int toughness = mStatComponent.getValue(StatName.TOUGHNESS);
        int maxHealth = (int)((toughness * 5.0) +  (level * 3.0));
        mStatComponent.setStatMaximumValue(StatName.HEALTH, maxHealth);
        mStatComponent.setStatValue(StatName.HEALTH, maxHealth);
    }


}
