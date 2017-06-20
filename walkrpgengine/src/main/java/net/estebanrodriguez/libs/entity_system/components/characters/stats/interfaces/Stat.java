package net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces;

import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;

/**
 * Created by spoooon on 6/15/17.
 */

public interface Stat {

    int value();
    void setValue(int value);
    void incrementValue();
    void decrementValue();
    void increaseValueBy(int value);
    void decreaseValueBy(int value);
    void setMaximumValue(int value);
    void setMinimumValue(int value);
    StatName getStatName();
    int getMaximum();
    int getMinimum();
}
