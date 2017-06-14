package net.estebanrodriguez.libs.entity_system.components.skills;

import net.estebanrodriguez.libs.entity_system.components.Component;
import net.estebanrodriguez.libs.entity_system.systems.combat.CombatEngine;
import net.estebanrodriguez.libs.entity_system.systems.combat.Combatant;


/**
 * Created by spoooon on 6/7/17.
 */

public class AttackSkill extends Component implements CombatSkill {

    public static final String COMPONENT_NAME = "attack_component";
    private static final String SKILL_NAME = "Attack";

    public AttackSkill() {
        super(COMPONENT_NAME);
    }
    public AttackSkill(String skillName){
        super(COMPONENT_NAME);
    }

    @Override
    public void useSkill(){

        Combatant attacker = CombatEngine.getInstance().getCombatantByID(super.getEntityID());
        if(attacker.getTarget() != attacker){
//            CombatEngine.getInstance().attack(attacker, attacker.getTarget());
        }
    }
}
