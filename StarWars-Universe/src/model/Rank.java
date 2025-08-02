package model;

public enum Rank {
    YOUNGLING,
    INITIATE,
    PADAWAN,
    KNIGHT_ASPIRANT,
    KNIGHT,
    MASTER,
    BATTLE_MASTER,
    GRAND_MASTER;
    public int getHierarchyLevel() {
        return this.ordinal();
    }
}
