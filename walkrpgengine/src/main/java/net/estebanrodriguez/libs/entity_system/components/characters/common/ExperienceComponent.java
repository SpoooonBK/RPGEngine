package net.estebanrodriguez.libs.entity_system.components.characters.common;


import net.estebanrodriguez.libs.entity_system.components.EntityComponent;

/**
 * Created by spoooon on 4/30/17.
 */

public class ExperienceComponent extends EntityComponent {

    private int mTotalExperience;

    public ExperienceComponent() {
        super(EXPERIENCE_COMPONENT);
        mTotalExperience = 0;
    }

    public int getTotalExperience() {
        return mTotalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        mTotalExperience = totalExperience;
    }

}
