package com.mycompany.npcquestsystem.factory;

/** Archetype key for {@link NpcFactory#createNpc(NpcArchetype)}. */
public enum NpcArchetype {
    QUARTERMASTER("npc_qm", "Quartermaster Hale"),
    SCOUT("npc_scout", "Scout Rin"),
    DESERTER("npc_deserter", "Deserter Voss");

    private final String id;
    private final String displayName;

    NpcArchetype(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
