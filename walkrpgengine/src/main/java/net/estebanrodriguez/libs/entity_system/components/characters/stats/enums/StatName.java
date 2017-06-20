package net.estebanrodriguez.libs.entity_system.components.characters.stats.enums;

/**
 * Created by spoooon on 6/9/17.
 */

public enum StatName {



    MUSCLES(StatType.ATTRIBUTE),
    BRAINS(StatType.ATTRIBUTE),
    SPEED(StatType.ATTRIBUTE),
    TOUGHNESS(StatType.ATTRIBUTE),
    HEALTH(StatType.EXPENDABLE),
    POWER(StatType.EXPENDABLE),
    LEVEL(StatType.LEVEL);

    private final StatType mStatType;

    StatName(StatType statType) {

        mStatType = statType;
    }

    public StatType getStatType() {
        return mStatType;
    }

    public enum StatType {
        ATTRIBUTE, EXPENDABLE, LEVEL
    }
}
