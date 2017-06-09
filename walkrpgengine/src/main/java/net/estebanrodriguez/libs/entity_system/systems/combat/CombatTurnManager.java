package net.estebanrodriguez.libs.entity_system.systems.combat;

import net.estebanrodriguez.libs.entity_system.systems.combat.interfaces.CombatTurnListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoooon on 6/8/17.
 */

public class CombatTurnManager {

    private List<CombatTurnListener> mListeners = new ArrayList<>();


    public void addCombatTurnListener(CombatTurnListener combatTurnListener){

        mListeners.add(combatTurnListener);
    }
}
