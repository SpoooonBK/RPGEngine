package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.components.characters.BodyComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.attributes.StatComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.stats.enums.StatName;
import net.estebanrodriguez.libs.entity_system.components.equipment.Equipment;
import net.estebanrodriguez.libs.entity_system.components.skills.CombatComponent;
import net.estebanrodriguez.libs.entity_system.entities.Entity;

/**
 * Created by spoooon on 5/30/17.
 */

public class Combatant {

    private String mId;
    private String mName;
    private Entity mEntity;
    private StatComponent mStatComponent;
    private BodyComponent mBodyComponent;
    private Combatant mTarget;
    private Equipment mWeapons;
    private Equipment mArmor;


    public Combatant(Entity entity) {

            if(canFight(entity)){
                mEntity = entity;
                mId = entity.getId();
                mName = ((CharacterComponent) entity.get(CharacterComponent.COMPONENT_NAME)).getName();
                mStatComponent = (StatComponent) entity.get(StatComponent.COMPONENT_NAME);
                mBodyComponent = (BodyComponent) entity.get(BodyComponent.COMPONENT_NAME);

            } else throw new IllegalArgumentException("Entity cannot fight");
    }


    public String getId() {
        return mId;
    }

    public Combatant getTarget() {
        return mTarget;
    }

    public void setTarget(Combatant target) {
        mTarget = target;
    }

    public Entity getEntity() {
        return mEntity;
    }

    public StatComponent getStatComponent() {
        return mStatComponent;
    }

    public BodyComponent getBodyComponent() {
        return mBodyComponent;
    }

    public Equipment getWeapons() {
        return getBodyComponent().getAllEquipment();
    }


    public String getName() {
        return mName;
    }


    public static boolean canFight(Entity entity) {
        return entity.has(CombatComponent.COMPONENT_NAME)
                && entity.has(BodyComponent.COMPONENT_NAME)
                && entity.has(StatComponent.COMPONENT_NAME)
                && entity.has(CharacterComponent.COMPONENT_NAME);
    }

    public boolean isAlive(){
        return (mStatComponent.getValue(StatName.HEALTH)> 0);
    }

}
