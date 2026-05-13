package com.mycompany.npcquestsystem.dialogue.strategy;

import com.mycompany.npcquestsystem.domain.Npc;
import com.mycompany.npcquestsystem.domain.Player;

/** Interchangeable dialogue algorithms (Strategy); extend without modifying {@link Npc}. */
public interface DialogueStrategy {

    String greeting(Npc npc, Player player);

    String reply(Npc npc, Player player, String playerLine);
}
