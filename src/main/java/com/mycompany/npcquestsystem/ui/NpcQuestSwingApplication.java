package com.mycompany.npcquestsystem.ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** Swing entry point; same domain + Strategy as {@link com.mycompany.npcquestsystem.NpcQuestApplication}. */
public final class NpcQuestSwingApplication {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // default L&F
        }
        SwingUtilities.invokeLater(() -> {
            NpcQuestFrame frame = new NpcQuestFrame();
            frame.setVisible(true);
        });
    }
}
