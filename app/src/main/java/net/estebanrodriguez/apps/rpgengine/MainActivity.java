package net.estebanrodriguez.apps.rpgengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.LevelComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.engines.combat.CombatEngine;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());

        GameEntity gameEntity1 = GameEntity.getBuilder()
                .add(new CharacterComponent("Fred"))
                .add(new StatsComponent(new LevelComponent(3), 20))
                .build();

        GameEntity gameEntity2 = GameEntity.getBuilder()
                .add(new CharacterComponent("Bob"))
                .add(new StatsComponent())
                .build();

        TextView character1Text = (TextView) findViewById(R.id.character_1);
        TextView character2Text = (TextView) findViewById(R.id.character_2);

        character1Text.setText(gameEntity1.toString());
        character2Text.setText(gameEntity2.toString());

        CombatEngine.getInstance().setCombatants();
    }
}
