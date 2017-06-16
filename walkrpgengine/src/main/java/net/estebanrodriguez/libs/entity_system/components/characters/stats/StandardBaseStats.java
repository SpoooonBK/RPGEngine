package net.estebanrodriguez.libs.entity_system.components.characters.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by spoooon on 6/14/17.
 */

public class StandardBaseStats implements BaseStats{

    private Map<StatType, Stat> mStats = new HashMap<>();

    private StandardBaseStats() {
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    @Override
    public Stat get(StatType statType){
        if(has(statType)){
            return mStats.get(statType);
        }else throw new IllegalArgumentException(statType.toString() + " not found");
    }

    @Override
    public int size() {
        return mStats.size();
    }

    @Override
    public Set<StatType> getStatTypesSet() {
        return mStats.keySet();
    }

    @Override
    public boolean has(StatType statType) {
        return mStats.containsKey(statType);
    }


    public List<Stat> getStatList(){
        List<Stat> statList = new ArrayList<>(mStats.values());
        return statList;
    }


    public static class Builder{

        private StandardBaseStats instance = new StandardBaseStats();

        public Builder add(AttributeStat stat){
                StatType statType = stat.getStatType();
                instance.mStats.put(statType, stat);
            return this;
        }

        public StandardBaseStats build(){
            if(hasAllStatTypes()){
                return instance;
            } else {
                initializeMissingStatTypes();
                return instance;
            }
        }

        private void initializeMissingStatTypes() {
            for(StatType statType: StatType.values()){
                if(!instance.mStats.containsKey(statType)){
                    add(new AttributeStat(statType));
                }
            }
        }


        private boolean hasAllStatTypes(){
            for(StatType statType: StatType.values()){
                if(!instance.mStats.containsKey(statType)){
                    return false;
                }
            }
            return true;
        }

    }
}
