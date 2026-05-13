package com.mycompany.npcquestsystem.factory;

import com.mycompany.npcquestsystem.dialogue.strategy.DialogueStrategy;
import com.mycompany.npcquestsystem.dialogue.strategy.FriendlyDialogueStrategy;
import com.mycompany.npcquestsystem.dialogue.strategy.HostileDialogueStrategy;
import com.mycompany.npcquestsystem.dialogue.strategy.NeutralDialogueStrategy;
import com.mycompany.npcquestsystem.domain.Npc;

/** Builds {@link Npc} instances with the right default {@link DialogueStrategy} per {@link NpcArchetype}. */
public final class NpcFactory {

    public Npc createNpc(NpcArchetype archetype) {
        DialogueStrategy strategy = strategyFor(archetype);
        return new Npc(archetype.getId(), archetype.getDisplayName(), strategy);
    }

    private static DialogueStrategy strategyFor(NpcArchetype archetype) {
        return switch (archetype) {
            case QUARTERMASTER -> new NeutralDialogueStrategy();
            case SCOUT -> new FriendlyDialogueStrategy();
            case DESERTER -> new HostileDialogueStrategy();
        };
    }
}
