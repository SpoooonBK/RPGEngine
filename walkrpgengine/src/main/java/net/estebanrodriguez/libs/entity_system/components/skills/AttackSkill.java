package net.estebanrodriguez.libs.entity_system.components.skills;

import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.systems.combat.CombatEngine;
import net.estebanrodriguez.libs.entity_system.systems.combat.Combatant;


/**
 * Created by spoooon on 6/7/17.
 */

public class AttackSkill extends EntityComponent implements CombatSkill {

    public static final String COMPONENT_NAME = "attack_component";
    private String mSkillName;

    public AttackSkill() {
        super(COMPONENT_NAME);
        mSkillName= "Attack";
    }
    public AttackSkill(String skillName){
        super(COMPONENT_NAME);
        mSkillName = skillName;
    }

    @Override
    public void useSkill(){

        Combatant attacker = CombatEngine.getInstance().getCombatantByID(super.getEntityID());
        if(attacker.getTarget() != attacker){
            CombatEngine.getInstance().attack(attacker, attacker.getTarget());
        }
    }
}
