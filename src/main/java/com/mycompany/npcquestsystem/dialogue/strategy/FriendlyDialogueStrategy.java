package com.mycompany.npcquestsystem.dialogue.strategy;

import com.mycompany.npcquestsystem.domain.Npc;
import com.mycompany.npcquestsystem.domain.Player;

public final class FriendlyDialogueStrategy implements DialogueStrategy {

    @Override
    public String greeting(Npc npc, Player player) {
        return "Welcome, " + player.getDisplayName() + "! " + npc.getDisplayName() + " is glad to see you.";
    }

    @Override
    public String reply(Npc npc, Player player, String playerLine) {
        if (playerLine == null || playerLine.isBlank()) {
            return npc.getDisplayName() + " smiles and waits for you to say something.";
        }
        return npc.getDisplayName() + " nods warmly: \"" + playerLine + "\" — interesting!";
    }
}
