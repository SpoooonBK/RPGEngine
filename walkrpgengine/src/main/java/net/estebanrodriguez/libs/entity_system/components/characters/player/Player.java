package net.estebanrodriguez.libs.entity_system.components.characters.player;


import net.estebanrodriguez.libs.entity_system.components.EntityComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.ExperienceComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;

/**
 * Created by spoooon on 4/30/17.
 */

public class Player extends EntityComponent {

    private StatsComponent mStats;
    private String mName;
    private ExperienceComponent mExperience;
}
