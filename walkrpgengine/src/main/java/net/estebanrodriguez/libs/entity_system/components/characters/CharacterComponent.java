package net.estebanrodriguez.libs.entity_system.components.characters;


import net.estebanrodriguez.libs.entity_system.components.Component;

/**
 * Created by spoooon on 4/30/17.
 */

public class CharacterComponent extends Component {

    public static final String COMPONENT_NAME = CharacterComponent.class.getSimpleName();
    private String mName;

    public CharacterComponent(String name) {
        super(COMPONENT_NAME);
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }



    @Override
    public String toString() {
        return "CharacterComponent: " + mName + "\n";
    }


}
