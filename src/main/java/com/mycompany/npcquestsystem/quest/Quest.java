package com.mycompany.npcquestsystem.quest;

import com.mycompany.npcquestsystem.quest.observe.QuestObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Quest model; status changes notify {@link QuestObserver} subscribers (Observer). */
public final class Quest {

    private final String id;
    private final String title;
    private final String description;
    private final List<QuestObserver> observers = new ArrayList<>();
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

    public void addQuestObserver(QuestObserver observer) {
        observers.add(Objects.requireNonNull(observer, "observer"));
    }

    public void removeQuestObserver(QuestObserver observer) {
        observers.remove(observer);
    }

    public void accept() {
        if (status == QuestStatus.AVAILABLE) {
            status = QuestStatus.IN_PROGRESS;
            notifyQuestObservers();
        }
    }

    public void complete() {
        if (status == QuestStatus.IN_PROGRESS) {
            status = QuestStatus.COMPLETED;
            notifyQuestObservers();
        }
    }

    private void notifyQuestObservers() {
        for (QuestObserver observer : new ArrayList<>(observers)) {
            observer.questUpdated(this);
        }
    }

    @Override
    public String toString() {
        return title + " [" + status + "]: " + description;
    }
}
