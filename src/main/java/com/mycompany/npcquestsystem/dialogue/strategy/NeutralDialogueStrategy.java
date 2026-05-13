package com.mycompany.npcquestsystem.dialogue.strategy;

import com.mycompany.npcquestsystem.domain.Npc;
import com.mycompany.npcquestsystem.domain.Player;

public final class NeutralDialogueStrategy implements DialogueStrategy {

    @Override
    public String greeting(Npc npc, Player player) {
        return npc.getDisplayName() + ": State your business, " + player.getDisplayName() + ".";
    }

    @Override
    public String reply(Npc npc, Player player, String playerLine) {
        if (playerLine == null || playerLine.isBlank()) {
            return npc.getDisplayName() + " watches you in silence.";
        }
        return npc.getDisplayName() + " acknowledges: noted.";
    }
}
