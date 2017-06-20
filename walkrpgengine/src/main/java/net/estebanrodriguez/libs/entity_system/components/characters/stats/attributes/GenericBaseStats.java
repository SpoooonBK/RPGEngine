package net.estebanrodriguez.libs.entity_system.components.characters.stats.attributes;

import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.expendable.GenericStat;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.BaseStats;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.Stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by spoooon on 6/14/17.
 */

public class GenericBaseStats implements BaseStats {

    private Map<StatName, Stat> mStats = new HashMap<>();

    public GenericBaseStats() {
        setStats();
    }

    private void setStats() {
        for(StatName statName : StatName.values()){
                mStats.put(statName, new GenericStat(statName));
        }
    }

    @Override
    public Stat get(StatName statName){
        if(has(statName)){
            return mStats.get(statName);
        }else throw new IllegalArgumentException(statName.toString() + " not found");
    }

    @Override
    public int size() {
        return mStats.size();
    }

    @Override
    public Set<StatName> getStatNamesSet() {
        return mStats.keySet();
    }

    @Override
    public List<Stat> getStatList() {
        List<Stat> stats = new ArrayList<>(mStats.values());
        return stats;
    }

    @Override
    public boolean has(StatName statName) {
        return mStats.containsKey(statName);
    }

}
