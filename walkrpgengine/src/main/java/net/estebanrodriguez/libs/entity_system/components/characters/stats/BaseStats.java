package net.estebanrodriguez.libs.entity_system.components.characters.stats;

import java.util.Set;

/**
 * Created by spoooon on 6/15/17.
 */

public interface BaseStats {

    boolean has(StatType statType);
    Stat get(StatType statType);
    int size();
    Set<StatType> getStatTypesSet();



}
