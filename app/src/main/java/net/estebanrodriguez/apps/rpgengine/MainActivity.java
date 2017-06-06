package net.estebanrodriguez.apps.rpgengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.systems.combat.CombatEngine;
import net.estebanrodriguez.libs.entity_system.systems.combat.CombatGroup;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.factories.Mob;
import net.estebanrodriguez.libs.entity_system.factories.MobFactory;
import net.estebanrodriguez.libs.entity_system.systems.inventory.EquipSystem;
import net.estebanrodriguez.libs.utilities.Dice;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());



        GameEntity weapon1 = GameEntity.getBuilder()
                .add(new WeaponComponent("Prehensile Armpit Hair", BodyPart.ARM_PIT, Dice.D4, 1, 1 ))
                .build();

        GameEntity weapon2 = GameEntity.getBuilder()
                .add(new WeaponComponent("Sharp Toe Nails", BodyPart.FEET, Dice.D2, 2, 1))
                .build();

        MobFactory mobFactory = new MobFactory();
        Mob mob = mobFactory.createLevelTargetedMob(12, 4, MobFactory.LevelPreference.RANDOM_LEVEL);
        for(GameEntity gameEntity: mob.getGameEntities()){
            EquipSystem.equip(gameEntity, weapon1);
            CombatEngine.getInstance().addCombatant(gameEntity, CombatGroup.GROUP_A);
        }

        Mob mob2 = mobFactory.createLevelTargetedMob(12, 4, MobFactory.LevelPreference.RANDOM_LEVEL);
        for(GameEntity gameEntity: mob.getGameEntities()){
            EquipSystem.equip(gameEntity, weapon2);
            CombatEngine.getInstance().addCombatant(gameEntity, CombatGroup.GROUP_B);
        }




//        GameEntity gameEntity1 = GameEntity.getBuilder()
//                .add(new CharacterComponent("Fred (GroupA)"))
//                .add(new StatsComponent())
//                .add(new GearComponent(weapon1))
//                .add(new CombatComponent())
//                .build();
//
//        GameEntity gameEntity2 = GameEntity.getBuilder()
//                .add(new CharacterComponent("Bob (GroupA)"))
//                .add(new StatsComponent())
//                .add(new GearComponent(weapon2))
//                .add(new CombatComponent())
//                .build();
//
//        GameEntity gameEntity3 = GameEntity.getBuilder()
//                .add(new CharacterComponent("Harvey (GroupB)"))
//                .add(new StatsComponent())
//                .add(new GearComponent(weapon1))
//                .add(new CombatComponent())
//                .build();
//
//        GameEntity gameEntity4 = GameEntity.getBuilder()
//                .add(new CharacterComponent("May (GroupB)"))
//                .add(new StatsComponent())
//                .add(new GearComponent(weapon2))
//                .add(new CombatComponent())
//                .build();
//
//        CombatEngine.getInstance().addCombatant(gameEntity1, CombatGroup.GROUP_A);
//        CombatEngine.getInstance().addCombatant(gameEntity2, CombatGroup.GROUP_A);
//        CombatEngine.getInstance().addCombatant(gameEntity3, CombatGroup.GROUP_B);
//        CombatEngine.getInstance().addCombatant(gameEntity4, CombatGroup.GROUP_B);

        String result = CombatEngine.getInstance().fight();
        Timber.v(result);
//
//        TextView character1Text = (TextView) findViewById(R.id.character_1);
//        TextView character2Text = (TextView) findViewById(R.id.character_2);

//        character1Text.setText(gameEntity1.toString());
//        character2Text.setText(gameEntity2.toString());




    }
}
