package net.estebanrodriguez.libs.entity_system.components.characters;


import net.estebanrodriguez.libs.entity_system.components.Component;

/**
 * Created by spoooon on 4/30/17.
 */

public class ExperienceComponent extends Component {

    public static final String COMPONENT_NAME = ExperienceComponent.class.getSimpleName();
    private int mTotalExperience;

    public ExperienceComponent() {
        super(COMPONENT_NAME);
        mTotalExperience = 0;
    }

    public int getTotalExperience() {
        return mTotalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        mTotalExperience = totalExperience;
    }

}
