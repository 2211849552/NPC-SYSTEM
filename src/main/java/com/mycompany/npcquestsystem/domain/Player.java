package com.mycompany.npcquestsystem.domain;

import java.util.Objects;

/** Player identity and state used by dialogue strategies. */
public final class Player {

    private final String displayName;
    private int reputation;

    public Player(String displayName, int initialReputation) {
        this.displayName = Objects.requireNonNull(displayName, "displayName");
        this.reputation = initialReputation;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getReputation() {
        return reputation;
    }

    public void adjustReputation(int delta) {
        this.reputation += delta;
    }
}
