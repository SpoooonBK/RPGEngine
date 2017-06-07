package net.estebanrodriguez.apps.rpgengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import net.estebanrodriguez.libs.entity_system.components.gear.ArmorComponent;
import net.estebanrodriguez.libs.entity_system.components.gear.enums.BodyPart;
import net.estebanrodriguez.libs.entity_system.components.gear.WeaponComponent;
import net.estebanrodriguez.libs.entity_system.systems.combat.CombatEngine;
import net.estebanrodriguez.libs.entity_system.entities.GameEntity;
import net.estebanrodriguez.libs.entity_system.factories.Mob;
import net.estebanrodriguez.libs.entity_system.factories.MobFactory;
import net.estebanrodriguez.libs.entity_system.systems.combat.Fight;
import net.estebanrodriguez.libs.entity_system.systems.combat.Team;
import net.estebanrodriguez.libs.utilities.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());



        GameEntity weapon1 = GameEntity.getBuilder()
                .add(new WeaponComponent("Prehensile Armpit Hair", BodyPart.ARM_PIT, Dice.D20, 1, 1 ))
                .build();

        GameEntity weapon2 = GameEntity.getBuilder()
                .add(new WeaponComponent("Sharp Toe Nails", BodyPart.FEET, Dice.D10, 2, 1))
                .build();

        GameEntity armor1 = GameEntity.getBuilder()
                .add(new ArmorComponent("Holey T-shirt", BodyPart.TORSO, 1))
                .build();

        List<GameEntity> gearList = new ArrayList<>();
        gearList.add(weapon1);
        gearList.add(weapon2);
        gearList.add(armor1);

        MobFactory mobFactory = new MobFactory();
        Mob mob = mobFactory.createLevelTargetedMob("Strong", 5, 1, MobFactory.LevelPreference.RANDOM_LEVEL);
        mob.equipMob(gearList);

        Mob mob2 = mobFactory.createLevelTargetedMob("Weak", 3, 1, MobFactory.LevelPreference.RANDOM_LEVEL);
        mob2.equipMob(gearList);

        Mob mob3 = mobFactory.createLevelTargetedMob("Ultra", 8, 1, MobFactory.LevelPreference.RANDOM_LEVEL);
        mob3.equipMob(gearList);

        Team ultra = Team.getBuilder()
                .add(mob3)
                .setName("Ultra")
                .build();

        Team strong = Team.getBuilder()
                .add(mob)
                .setName("Strong")
                .build();

        Team weak = Team.getBuilder()
                .add(mob2)
                .setName("Weak")
                .build();

        CombatEngine.getInstance().addTeam(strong);
        CombatEngine.getInstance().addTeam(weak);
        CombatEngine.getInstance().addTeam(ultra);



        Fight fight = CombatEngine.getInstance().executeCombat();
        Timber.v(fight.toString());



    }
}
