package net.estebanrodriguez.apps.rpgengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import net.estebanrodriguez.libs.entity_system.components.characters.GearComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CharacterComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.CombatComponent;
import net.estebanrodriguez.libs.entity_system.components.characters.common.StatsComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.components.skills.AttackSkill;
import net.estebanrodriguez.libs.entity_system.systems.combat.CombatEngine;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.systems.combat.Combatant;
import net.estebanrodriguez.libs.entity_system.systems.combat.Team;
import net.estebanrodriguez.libs.entity_system.systems.inventory.Equipper;
import net.estebanrodriguez.libs.utilities.Die;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());


        GameEntity weapon1 = GameEntity.getBuilder()
                .add(new WeaponComponent("Boo Power", BodyPart.MOUTH, Die.StandardDie.D4, 1, WeaponComponent.WeaponSize.NORMAL, ))
                .build();

        GameEntity weapon2 = GameEntity.getBuilder()
                .add(new WeaponComponent("Squiggle Power", BodyPart.TAIL, Die.StandardDie.D2, 2, WeaponComponent.WeaponSize.NORMAL, ))
                .build();

        final GameEntity johnny =GameEntity.getBuilder()
                .add(new CharacterComponent("Johnny Boo!"))
                .add(new StatsComponent(3))
                .add(new CombatComponent())
                .add(new GearComponent())
                .add(new AttackSkill())
                .build();
        Equipper.equip(johnny, weapon1);

        final GameEntity squiggle = GameEntity.getBuilder()
                .add(new CharacterComponent("Squiggle"))
                .add(new StatsComponent(3))
                .add(new CombatComponent())
                .add(new GearComponent())
                .add(new AttackSkill())
                .build();
        Equipper.equip(squiggle, weapon2);

        Team johnnyBoo = Team.getBuilder()
                .add(new Combatant(johnny))
                .setName("Ghosts")
                .build();

        Team squiggleTeam = Team.getBuilder()
                .add(new Combatant(squiggle))
                .setName("Squiggles")
                .build();

        CombatEngine.getInstance().addTeam(johnnyBoo);
        CombatEngine.getInstance().addTeam(squiggleTeam);
        CombatEngine.getInstance().rollForInitiative();


        LinearLayout character1Layout = (LinearLayout) findViewById(R.id.character_1_holder);
        TextView character1Text = (TextView) findViewById(R.id.character_1);
        Button character1SkillButton = (Button) findViewById(R.id.character_1_skill);


        character1Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        character1Text.setText(((CharacterComponent)johnny.get(CharacterComponent.COMPONENT_NAME)).getName());
        character1SkillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AttackSkill)johnny.get(AttackSkill.COMPONENT_NAME)).useSkill();
            }
        });


        LinearLayout character2Layout = (LinearLayout) findViewById(R.id.character_2_holder);
        TextView character2Text = (TextView) findViewById(R.id.character_2);
        Button character2SkillButton = (Button) findViewById(R.id.character_2_skill);

        character2Text.setText(((CharacterComponent) squiggle.get(CharacterComponent.COMPONENT_NAME)).getName());
        character2SkillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AttackSkill)squiggle.get(AttackSkill.COMPONENT_NAME)).useSkill();
            }
        });






//        GameEntity armor1 = GameEntity.getBuilder()
//                .add(new ArmorComponent("Holey T-shirt", BodyPart.TORSO, 1))
//                .build();
//
//        List<GameEntity> gearList = new ArrayList<>();
//        gearList.add(weapon1);
//        gearList.add(weapon2);
//        gearList.add(armor1);
//
//        MobFactory mobFactory = new MobFactory();
//        Mob mob = mobFactory.createLevelTargetedMob("Strong", 5, 1, MobFactory.LevelPreference.RANDOM_LEVEL);
//        mob.equipMob(gearList);
//
//        Mob mob2 = mobFactory.createLevelTargetedMob("Weak", 3, 1, MobFactory.LevelPreference.RANDOM_LEVEL);
//        mob2.equipMob(gearList);
//
//        Mob mob3 = mobFactory.createLevelTargetedMob("Ultra", 8, 1, MobFactory.LevelPreference.RANDOM_LEVEL);
//        mob3.equipMob(gearList);
//
//        Team ultra = Team.getBuilder()
//                .add(mob3)
//                .setName("Ultra")
//                .build();
//
//        Team strong = Team.getBuilder()
//                .add(mob)
//                .setName("Strong")
//                .build();
//
//        Team weak = Team.getBuilder()
//                .add(mob2)
//                .setName("Weak")
//                .build();
//
//        CombatEngine.getInstance().addTeam(strong);
//        CombatEngine.getInstance().addTeam(weak);
//        CombatEngine.getInstance().addTeam(ultra);



//        Fight fight = CombatEngine.getInstance().executeAutoCombat();
//        Timber.v(fight.toString());

    }

}
