package com.example.poke_servi;

import java.util.ArrayList;

public class AtribuPoke {

    private ArrayList<Abilities> abilities;
    private int base_experience;
    private ArrayList<Forms> forms  ;

    public int getBase_experience() {

        return base_experience;
    }

    public ArrayList<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Abilities> abilities) {
        this.abilities = abilities;
    }

    public void setBase_experience(int base_experience) {

        this.base_experience = base_experience;
    }

    public ArrayList<Forms> getForms() {
        return forms;
    }

    public void setForms(ArrayList<Forms> forms) {
        this.forms = forms;
    }
}
