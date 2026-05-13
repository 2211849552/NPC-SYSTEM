package com.mycompany.npcquestsystem.dialogue.strategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mycompany.npcquestsystem.domain.Npc;
import com.mycompany.npcquestsystem.domain.Player;
import org.junit.jupiter.api.Test;

class DialogueStrategyTest {

    @Test
    void friendlyGreetingContainsPlayerName() {
        Npc npc = new Npc("n1", "Merchant", new FriendlyDialogueStrategy());
        Player p = new Player("Sam", 0);
        assertTrue(npc.greet(p).contains("Sam"));
    }

    @Test
    void strategySwapChangesOutput() {
        Npc npc = new Npc("n2", "Guard", new HostileDialogueStrategy());
        Player p = new Player("Lee", 0);
        String hostile = npc.greet(p);
        npc.setDialogueStrategy(new FriendlyDialogueStrategy());
        String friendly = npc.greet(p);
        assertTrue(!hostile.equals(friendly));
    }
}
