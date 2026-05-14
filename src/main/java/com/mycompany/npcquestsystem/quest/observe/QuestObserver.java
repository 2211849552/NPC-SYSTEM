package com.mycompany.npcquestsystem.quest.observe;

import com.mycompany.npcquestsystem.quest.Quest;

/** Notified when a {@link Quest} publishes a meaningful state change (Observer). */
public interface QuestObserver {

    void questUpdated(Quest quest);
}
