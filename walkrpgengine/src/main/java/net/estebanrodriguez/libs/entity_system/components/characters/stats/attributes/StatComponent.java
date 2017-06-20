package net.estebanrodriguez.libs.entity_system.components.characters.stats.attributes;

import net.estebanrodriguez.libs.entity_system.components.Component;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.BaseStats;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.Stat;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.interfaces.StatPointSetter;


/**
 * Created by spoooon on 4/30/17.
 */

public class StatComponent extends Component{

    public static final String COMPONENT_NAME = StatComponent.class.getSimpleName();

    private BaseStats mBaseStats;

    public StatComponent(int level){
        super(COMPONENT_NAME);
        setStats();
        setStatValue(StatName.LEVEL, level);
    }

    public StatComponent(int level, BaseStats baseStats){
        super(COMPONENT_NAME);
        mBaseStats = baseStats;
        setStatValue(StatName.LEVEL, level);
    }

    private void setStats(){
        setAttributes();
        setHealth();
        setPower();
    }

    private void setAttributes() {
        mBaseStats = new GenericBaseStats();
        StatPointSetter pointSetter = new AttributeStatPointSetter(this);
        pointSetter.setPoints();
    }

    private void setPower(){
        StatPointSetter pointSetter = new PowerPointSetter(this);
        pointSetter.setPoints();
    }

    private void setHealth(){
        StatPointSetter pointSetter = new HealthStatPointSetter(this);
        pointSetter.setPoints();
    }

    public BaseStats getBaseStats() {
        return mBaseStats;
    }


    public int getValue(StatName statName) {
        if(hasStat(statName)){
            Stat stat = getStat(statName);
            return stat.value();
        }else return 0;
    }


    private Stat getStat(StatName statName) {
        if(hasStat(statName)){
            return mBaseStats.get(statName);
        }else throw new IllegalArgumentException();

    }

    public void incrementStat(StatName statName){
        Stat stat = getStat(statName);
        stat.incrementValue();
    }

    public void decrementStat(StatName statName){
        Stat stat = getStat(statName);
        stat.decrementValue();
    }

    public void increaseStatBy(StatName statName, int increase){
        Stat stat = getStat(statName);
        stat.increaseValueBy(increase);
    }

    public void decreaseStatBy(StatName statName, int decrease){
        Stat stat = getStat(statName);
        stat.decreaseValueBy(decrease);
    }

    public void setStatValue(StatName statName, int value){
        Stat stat = getStat(statName);
        stat.setValue(value);
    }

    public void setStatMaximumValue(StatName statName, int maxValue){
        Stat stat = getStat(statName);
        stat.setMaximumValue(maxValue);
    }

    public void setStatMinimumValue(StatName statName, int minValue){
        Stat stat = getStat(statName);
        stat.setMinimumValue(minValue);
    }

    public boolean hasStat(StatName statName){
        return mBaseStats.has(statName);
    }


    public int getLevel(){return getValue(StatName.LEVEL);}



}
