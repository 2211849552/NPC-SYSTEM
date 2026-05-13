package com.mycompany.npcquestsystem.dialogue.strategy;

import com.mycompany.npcquestsystem.domain.Npc;
import com.mycompany.npcquestsystem.domain.Player;

public final class HostileDialogueStrategy implements DialogueStrategy {

    @Override
    public String greeting(Npc npc, Player player) {
        String suffix = player.getReputation() < 0 ? " Trouble follows you." : " Do not waste my time.";
        return npc.getDisplayName() + " glares at " + player.getDisplayName() + "." + suffix;
    }

    @Override
    public String reply(Npc npc, Player player, String playerLine) {
        if (playerLine == null || playerLine.isBlank()) {
            return npc.getDisplayName() + " spits at the ground. Say something or leave.";
        }
        return npc.getDisplayName() + " sneers: I do not care.";
    }
}
