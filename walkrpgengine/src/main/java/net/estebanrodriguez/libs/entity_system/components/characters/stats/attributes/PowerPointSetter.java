package net.estebanrodriguez.libs.entity_system.components.characters.stats.attributes;

import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.StatPointSetter;

/**
 * Created by spoooon on 6/19/17.
 */

public class PowerPointSetter implements StatPointSetter {


    StatComponent mStatComponent;

    public PowerPointSetter(StatComponent statComponent) {
        mStatComponent = statComponent;
    }

    @Override
    public void setPoints() {
        int level = mStatComponent.getLevel();
        int muscles = mStatComponent.getValue(StatName.MUSCLES);
        int brains = mStatComponent.getValue(StatName.BRAINS);

        int maxPower = (int)((muscles * 2.5)+(brains * 2.5) + (level *3));
        mStatComponent.setStatMaximumValue(StatName.POWER, maxPower);
        mStatComponent.setStatValue(StatName.POWER, maxPower);
    }
}
