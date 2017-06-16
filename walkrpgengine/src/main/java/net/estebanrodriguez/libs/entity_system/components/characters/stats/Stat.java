package net.estebanrodriguez.libs.entity_system.components.characters.stats;

/**
 * Created by spoooon on 6/15/17.
 */

interface Stat {

    int getValue();
    void setValue(int value);
    void incrementValue();
    void decrementValue();
    void increaseValueBy(int value);
    void decreaseValueBy(int value);
}
