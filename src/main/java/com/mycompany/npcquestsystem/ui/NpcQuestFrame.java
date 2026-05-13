package com.mycompany.npcquestsystem.ui;

import com.mycompany.npcquestsystem.dialogue.strategy.FriendlyDialogueStrategy;
import com.mycompany.npcquestsystem.dialogue.strategy.HostileDialogueStrategy;
import com.mycompany.npcquestsystem.dialogue.strategy.NeutralDialogueStrategy;
import com.mycompany.npcquestsystem.domain.Npc;
import com.mycompany.npcquestsystem.domain.Player;
import com.mycompany.npcquestsystem.quest.Quest;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

final class NpcQuestFrame extends JFrame {

    private final Player hero;
    private final Npc quartermaster;
    private final Npc scout;
    private final Npc deserter;
    private final Quest recon;
    private final Npc[] npcChoices;

    private final JTextArea log = new JTextArea();
    private final JComboBox<String> npcCombo = new JComboBox<>();
    private final JTextField playerLineField = new JTextField(28);
    private final JLabel questLabel = new JLabel(" ");

    NpcQuestFrame() {
        super("NPC Dialogue & Quest — Strategy demo");
        this.hero = new Player("Amina", 5);
        this.quartermaster = new Npc("npc_qm", "Quartermaster Hale", new NeutralDialogueStrategy());
        this.scout = new Npc("npc_scout", "Scout Rin", new FriendlyDialogueStrategy());
        this.deserter = new Npc("npc_deserter", "Deserter Voss", new HostileDialogueStrategy());
        this.recon = new Quest("q_recon", "Recon the ridge", "Survey enemy positions and return.");
        this.npcChoices = new Npc[] {quartermaster, scout, deserter};

        npcCombo.addItem(quartermaster.getDisplayName());
        npcCombo.addItem(scout.getDisplayName());
        npcCombo.addItem(deserter.getDisplayName());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(720, 520));
        setLocationRelativeTo(null);

        log.setEditable(false);
        log.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(log);
        scroll.setPreferredSize(new Dimension(680, 280));

        JButton greetBtn = new JButton("Greet");
        greetBtn.addActionListener(e -> appendDialogue(selectedNpc().greet(hero)));

        JButton replyBtn = new JButton("Reply");
        replyBtn.addActionListener(
                e -> appendDialogue(selectedNpc().reactTo(hero, playerLineField.getText())));

        JButton swapBtn = new JButton("Story: calm Deserter (swap Strategy)");
        swapBtn.addActionListener(
                e -> {
                    appendSection("Strategy swap (same NPC, new DialogueStrategy)");
                    appendLine("Before: " + deserter.greet(hero));
                    hero.adjustReputation(10);
                    deserter.setDialogueStrategy(new NeutralDialogueStrategy());
                    appendLine("After Neutral strategy: " + deserter.greet(hero));
                });

        JPanel dialoguePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dialoguePanel.setBorder(BorderFactory.createTitledBorder("Dialogue (Strategy)"));
        dialoguePanel.add(new JLabel("NPC:"));
        dialoguePanel.add(npcCombo);
        dialoguePanel.add(greetBtn);
        dialoguePanel.add(new JLabel("Your line:"));
        dialoguePanel.add(playerLineField);
        dialoguePanel.add(replyBtn);
        dialoguePanel.add(swapBtn);

        JButton acceptQuestBtn = new JButton("Accept quest");
        acceptQuestBtn.addActionListener(
                e -> {
                    recon.accept();
                    refreshQuest();
                    appendLine("[Quest] " + recon);
                });
        JButton completeQuestBtn = new JButton("Complete quest");
        completeQuestBtn.addActionListener(
                e -> {
                    recon.complete();
                    refreshQuest();
                    appendLine("[Quest] " + recon);
                });

        JPanel questPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questPanel.setBorder(BorderFactory.createTitledBorder("Quest (placeholder for teammates)"));
        questPanel.add(questLabel);
        questPanel.add(acceptQuestBtn);
        questPanel.add(completeQuestBtn);

        JPanel north = new JPanel(new BorderLayout());
        north.add(dialoguePanel, BorderLayout.NORTH);
        north.add(questPanel, BorderLayout.SOUTH);

        add(north, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        refreshQuest();
        appendLine("Select an NPC, use Greet / Reply, or run the quest buttons.");
        appendLine("\"Story: calm Deserter\" demonstrates setDialogueStrategy (Strategy).");
        pack();
    }

    private Npc selectedNpc() {
        int i = npcCombo.getSelectedIndex();
        if (i < 0 || i >= npcChoices.length) {
            return npcChoices[0];
        }
        return npcChoices[i];
    }

    private void refreshQuest() {
        questLabel.setText(recon.toString());
    }

    private void appendDialogue(String text) {
        appendLine(text);
    }

    private void appendSection(String title) {
        appendLine("");
        appendLine("--- " + title + " ---");
    }

    private void appendLine(String line) {
        SwingUtilities.invokeLater(
                () -> {
                    log.append(line + "\n");
                    log.setCaretPosition(log.getDocument().getLength());
                });
    }
}
