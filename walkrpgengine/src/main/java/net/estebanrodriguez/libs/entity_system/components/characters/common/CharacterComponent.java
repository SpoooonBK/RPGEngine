package net.estebanrodriguez.libs.entity_system.components.characters.common;


import net.estebanrodriguez.libs.entity_system.components.EntityComponent;

/**
 * Created by spoooon on 4/30/17.
 */

public class CharacterComponent extends EntityComponent {

    public static final String CHARACTER_COMPONENT = "character_component";

    private String mName;

    public CharacterComponent(String name) {
        super(CHARACTER_COMPONENT);
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
