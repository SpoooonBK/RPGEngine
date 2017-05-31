package net.estebanrodriguez.apps.rpgengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import net.estebanrodriguez.libs.entity_system.components.characters.common.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CombatComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.engines.combat.CombatEngine;
import net.estebanrodriguez.libs.entity_system.engines.combat.CombatGroup;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.utilities.Dice;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());



        GameEntity weapon1 = GameEntity.getBuilder()
                .add(new WeaponComponent("Prehensile Armpit Hair", BodyPart.ARM_PIT, Dice.D4, 1 ))
                .build();

        GameEntity weapon2 = GameEntity.getBuilder()
                .add(new WeaponComponent("Sharp Toe Nails", BodyPart.FEET, Dice.D2, 2))
                .build();

        GameEntity gameEntity1 = GameEntity.getBuilder()
                .add(new CharacterComponent("Fred"))
                .add(new StatsComponent())
                .add(new GearComponent(weapon1))
                .add(new CombatComponent())
                .build();

        GameEntity gameEntity2 = GameEntity.getBuilder()
                .add(new CharacterComponent("Bob"))
                .add(new StatsComponent())
                .add(new GearComponent(weapon2))
                .add(new CombatComponent())
                .build();

        CombatEngine.getInstance().addCombatant(gameEntity1, CombatGroup.GROUP_A);
        CombatEngine.getInstance().addCombatant(gameEntity2, CombatGroup.GROUP_B);

        String result = CombatEngine.getInstance().fight();
        Timber.v(result);

        TextView character1Text = (TextView) findViewById(R.id.character_1);
        TextView character2Text = (TextView) findViewById(R.id.character_2);

        character1Text.setText(gameEntity1.toString());
        character2Text.setText(gameEntity2.toString());




    }
}
