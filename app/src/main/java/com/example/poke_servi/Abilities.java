package com.example.poke_servi;

public class Abilities {
    private String isHidden;
    private int slot;
    private Ability ability;

    public Abilities() {
    }

    public Abilities(String isHidden, int slot, Ability ability) {
        this.isHidden = isHidden;
        this.slot = slot;
        this.ability = ability;
    }

    public String getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}
