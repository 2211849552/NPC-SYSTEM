package com.mycompany.npcquestsystem;

import com.mycompany.npcquestsystem.dialogue.strategy.NeutralDialogueStrategy;
import com.mycompany.npcquestsystem.domain.Npc;
import com.mycompany.npcquestsystem.domain.Player;
import com.mycompany.npcquestsystem.factory.NpcArchetype;
import com.mycompany.npcquestsystem.factory.NpcFactory;
import com.mycompany.npcquestsystem.quest.Quest;

/** Console demo (same logic as {@link com.mycompany.npcquestsystem.ui.NpcQuestSwingApplication}). */
public final class NpcQuestApplication {

    public static void main(String[] args) {
        Player hero = new Player("Amina", 5);

        NpcFactory npcFactory = new NpcFactory();
        Npc quartermaster = npcFactory.createNpc(NpcArchetype.QUARTERMASTER);
        Npc scout = npcFactory.createNpc(NpcArchetype.SCOUT);
        Npc deserter = npcFactory.createNpc(NpcArchetype.DESERTER);

        Quest recon = new Quest("q_recon", "Recon the ridge", "Survey enemy positions and return.");

        printSection("Greetings (NPCs built via Factory; each uses a different DialogueStrategy)");
        System.out.println(quartermaster.greet(hero));
        System.out.println(scout.greet(hero));
        System.out.println(deserter.greet(hero));

        printSection("Replies");
        System.out.println(quartermaster.reactTo(hero, "We need supplies."));
        System.out.println(scout.reactTo(hero, "I mapped the patrol routes."));
        System.out.println(deserter.reactTo(hero, ""));

        printSection("Strategy swap: same NPC, new behavior after story beat");
        System.out.println("Before: " + deserter.greet(hero));
        hero.adjustReputation(10);
        deserter.setDialogueStrategy(new NeutralDialogueStrategy());
        System.out.println("After setDialogueStrategy(Neutral): " + deserter.greet(hero));

        printSection("Quest placeholder (for teammates' patterns)");
        System.out.println(recon);
        recon.accept();
        System.out.println("Accepted: " + recon);
        recon.complete();
        System.out.println("Completed: " + recon);
    }

    private static void printSection(String title) {
        System.out.println();
        System.out.println("--- " + title + " ---");
    }
}
