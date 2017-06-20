package net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces;

import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;

import java.util.List;
import java.util.Set;

/**
 * Created by spoooon on 6/15/17.
 */

public interface BaseStats {

    boolean has(StatName statName);
    Stat get(StatName statName);
    int size();
    Set<StatName> getStatNamesSet();
    List<Stat> getStatList();

}
