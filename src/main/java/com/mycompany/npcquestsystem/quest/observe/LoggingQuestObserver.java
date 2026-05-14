package com.mycompany.npcquestsystem.quest.observe;

import com.mycompany.npcquestsystem.quest.Quest;
import java.util.Objects;
import java.util.function.Consumer;

/** Forwards quest updates to a sink (console, UI, tests). */
public final class LoggingQuestObserver implements QuestObserver {

    private final Consumer<Quest> sink;

    public LoggingQuestObserver(Consumer<Quest> sink) {
        this.sink = Objects.requireNonNull(sink, "sink");
    }

    @Override
    public void questUpdated(Quest quest) {
        sink.accept(quest);
    }
}
