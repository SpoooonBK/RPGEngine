package net.estebanrodriguez.libs.entity_system.components.characters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spoooon on 6/14/17.
 */

public class BaseStats {

    private Map<Stat.StatType, Stat> mStats = new HashMap<>();

    private BaseStats() {
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public Stat getStat(Stat.StatType statType){
        if(hasStatType(statType)){
            return mStats.get(statType);
        }else throw new IllegalArgumentException(statType.toString() + " not found");
    }

    public List<Stat> getStatList(){
        List<Stat> statList = new ArrayList<>(mStats.values());
        return statList;
    }

    private boolean hasStatType(Stat.StatType statType) {
        return mStats.containsKey(statType);
    }

    public static class Builder{

        private BaseStats instance = new BaseStats();

        public Builder add(Stat stat){
                Stat.StatType statType = stat.getStatType();
                instance.mStats.put(statType, stat);
            return this;
        }

        public BaseStats build(){
            if(hasAllStatTypes()){
                return instance;
            } else {
                initializeMissingStatTypes();
                return instance;
            }
        }

        private void initializeMissingStatTypes() {
            for(Stat.StatType statType: Stat.StatType.values()){
                if(!instance.mStats.containsKey(statType)){
                    Stat stat = new Stat(statType);
                    add(new Stat(statType));
                }
            }
        }


        private boolean hasAllStatTypes(){
            for(Stat.StatType statType: Stat.StatType.values()){
                if(!instance.mStats.containsKey(statType)){
                    return false;
                }
            }
            return true;
        }

    }
}