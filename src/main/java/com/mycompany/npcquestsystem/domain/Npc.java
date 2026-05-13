package com.mycompany.npcquestsystem.domain;

import com.mycompany.npcquestsystem.dialogue.strategy.DialogueStrategy;
import java.util.Objects;

/** NPC; dialogue delegated to {@link DialogueStrategy} (Strategy). */
public final class Npc {

    private final String id;
    private final String displayName;
    private DialogueStrategy dialogueStrategy;

    public Npc(String id, String displayName, DialogueStrategy dialogueStrategy) {
        this.id = Objects.requireNonNull(id, "id");
        this.displayName = Objects.requireNonNull(displayName, "displayName");
        this.dialogueStrategy = Objects.requireNonNull(dialogueStrategy, "dialogueStrategy");
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DialogueStrategy getDialogueStrategy() {
        return dialogueStrategy;
    }

    public void setDialogueStrategy(DialogueStrategy dialogueStrategy) {
        this.dialogueStrategy = Objects.requireNonNull(dialogueStrategy, "dialogueStrategy");
    }

    public String greet(Player player) {
        return dialogueStrategy.greeting(this, player);
    }

    public String reactTo(Player player, String playerLine) {
        return dialogueStrategy.reply(this, player, playerLine);
    }
}
