package com.mycompany.npcquestsystem.factory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mycompany.npcquestsystem.dialogue.strategy.FriendlyDialogueStrategy;
import com.mycompany.npcquestsystem.dialogue.strategy.HostileDialogueStrategy;
import com.mycompany.npcquestsystem.dialogue.strategy.NeutralDialogueStrategy;
import com.mycompany.npcquestsystem.domain.Npc;
import org.junit.jupiter.api.Test;

class NpcFactoryTest {

    private final NpcFactory factory = new NpcFactory();

    @Test
    void quartermasterUsesNeutralStrategy() {
        Npc n = factory.createNpc(NpcArchetype.QUARTERMASTER);
        assertEquals("Quartermaster Hale", n.getDisplayName());
        assertInstanceOf(NeutralDialogueStrategy.class, n.getDialogueStrategy());
    }

    @Test
    void scoutUsesFriendlyStrategy() {
        Npc n = factory.createNpc(NpcArchetype.SCOUT);
        assertInstanceOf(FriendlyDialogueStrategy.class, n.getDialogueStrategy());
    }

    @Test
    void deserterUsesHostileStrategy() {
        Npc n = factory.createNpc(NpcArchetype.DESERTER);
        assertInstanceOf(HostileDialogueStrategy.class, n.getDialogueStrategy());
    }
}
