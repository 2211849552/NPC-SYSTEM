package com.mycompany.npcquestsystem.quest.observe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mycompany.npcquestsystem.quest.Quest;
import com.mycompany.npcquestsystem.quest.QuestStatus;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class QuestObserverTest {

    @Test
    void notifiesOnAcceptAndCompleteOnlyWhenStateChanges() {
        Quest quest = new Quest("q1", "Title", "Desc");
        List<QuestStatus> seen = new ArrayList<>();
        quest.addQuestObserver(q -> seen.add(q.getStatus()));

        quest.accept();
        assertEquals(List.of(QuestStatus.IN_PROGRESS), seen);

        quest.accept();
        assertEquals(List.of(QuestStatus.IN_PROGRESS), seen);

        quest.complete();
        assertEquals(List.of(QuestStatus.IN_PROGRESS, QuestStatus.COMPLETED), seen);

        quest.complete();
        assertEquals(List.of(QuestStatus.IN_PROGRESS, QuestStatus.COMPLETED), seen);
    }

    @Test
    void removeQuestObserverStopsNotifications() {
        Quest quest = new Quest("q1", "Title", "Desc");
        List<String> log = new ArrayList<>();
        QuestObserver o = q -> log.add(q.getId());
        quest.addQuestObserver(o);
        quest.accept();
        assertEquals(List.of("q1"), log);

        quest.removeQuestObserver(o);
        quest.complete();
        assertEquals(List.of("q1"), log);
    }
}
