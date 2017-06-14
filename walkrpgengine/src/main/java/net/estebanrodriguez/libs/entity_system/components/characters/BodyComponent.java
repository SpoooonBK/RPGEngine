package net.estebanrodriguez.libs.entity_system.components.characters;

import net.estebanrodriguez.libs.entity_system.components.Component;
import net.estebanrodriguez.libs.entity_system.components.equipment.Equipment;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.systems.World;
import net.estebanrodriguez.libs.utilities.DiceRoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.estebanrodriguez.libs.entity_system.components.characters.BodyPart.*;

/**
 * Created by spoooon on 5/30/17.
 */

public class BodyComponent extends Component {


    public static final String COMPONENT_NAME = BodyComponent.class.getSimpleName();
    private Map<Part, BodyPart> mBodyParts = new HashMap<>();

    public BodyComponent(){
        super(COMPONENT_NAME);

    }

    public Equipment getAllEquipment(){

        Equipment equipment = new Equipment();
        equipment.add(getEquippedWeapons());
        equipment.add(getEquippedArmor());

        return equipment;
    }

    public Equipment getEquippedWeapons(){
        Equipment weapons = new Equipment();
        for(BodyPart bodyPart: mBodyParts.values()){
            if(bodyPart.hasWeapon()){
                String id = bodyPart.getWeaponId();
                GameEntity gameEntity = World.getInstance().getGameEntity(id);
                weapons.add(gameEntity);
            }
        }
        return weapons;
    }


    public Equipment getEquippedArmor(){
        Equipment armor = new Equipment();
        for(BodyPart bodyPart: mBodyParts.values()){
            if(bodyPart.hasArmor()){
                String id = bodyPart.getArmorId();
                GameEntity gameEntity = World.getInstance().getGameEntity(id);
                armor.add(gameEntity);
            }
        }
        return armor;
    }

    public int getTotalWeaponsEquipped(){
        int totalWeapons = 0;
        for(BodyPart bodyPart:mBodyParts.values()){
            if(bodyPart.hasWeapon()){
                totalWeapons++;
            }
        }
        return totalWeapons;
    }

    public BodyPart getBodyPart(Part part){
        if(mBodyParts.containsKey(part)){
            return mBodyParts.get(part);
        }
        else return createAndAddBodyPart(part);
    }

    private BodyPart createAndAddBodyPart(Part part) {
        BodyPart bodyPart = new BodyPart(part);
        mBodyParts.put(part, bodyPart);
        return bodyPart;
    }

    public BodyPart getRandomBodyPart(){
        if(!mBodyParts.isEmpty()){
            List<BodyPart> values = getBodyPartValuesList();
            int highIndex = values.size()-1;
            int lowIndex = 0;
            if(highIndex > 0){
                int roll = DiceRoller.rollRandomInt(lowIndex, highIndex);
                return values.get(roll);
            }else if (highIndex == 0){
                return values.get(0);
            }
        }
        return createAndAddBodyPart(Part.ARM_PIT);
    }


    private List<BodyPart> getBodyPartValuesList(){
        List<BodyPart> values = new ArrayList<>(mBodyParts.values());
        return values;
    }

    public void unequip(GameEntity equipment){
        String equipmentID = equipment.getId();
        for(BodyPart bodypart: mBodyParts.values()){
            bodypart.unequip(equipmentID);
        }
    }


}
