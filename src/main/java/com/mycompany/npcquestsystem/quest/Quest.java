package com.mycompany.npcquestsystem.quest;

import java.util.Objects;

/** Minimal quest model for teammates to extend with other patterns. */
public final class Quest {

    private final String id;
    private final String title;
    private final String description;
    private QuestStatus status;

    public Quest(String id, String title, String description) {
        this.id = Objects.requireNonNull(id, "id");
        this.title = Objects.requireNonNull(title, "title");
        this.description = Objects.requireNonNull(description, "description");
        this.status = QuestStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void accept() {
        if (status == QuestStatus.AVAILABLE) {
            status = QuestStatus.IN_PROGRESS;
        }
    }

    public void complete() {
        if (status == QuestStatus.IN_PROGRESS) {
            status = QuestStatus.COMPLETED;
        }
    }

    @Override
    public String toString() {
        return title + " [" + status + "]: " + description;
    }
}
