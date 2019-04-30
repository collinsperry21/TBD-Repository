package AppModels;

import java.io.Serializable;

public class Stats implements Serializable {

    private int hitpoints;
    private String hitDie;
    private int speed;
    private int strength;
    private int intelligence;
    private int dexterity;
    private int wisdom;
    private int constitution;
    private int charisma;
    private int modStrength;
    private int modIntelligence;
    private int modDexterity;
    private int modWisdom;
    private int modConstitution;
    private int modCharisma;
    private int armorClass;
    private int profBonus;

    public Stats()
    {
        setStrength(15);
        setDexterity(14);
        setConstitution(13);
        setIntelligence(12);
        setWisdom(10);
        setCharisma(8);
    }
    //Temp stats constructor. Once i know how stats will be linked from CaC, we can update
    //public Stats(int statType){}


    public int getModStrength() {
        return modStrength;
    }

    public void setModStrength(int modStrength) {
        this.modStrength = modStrength;
    }

    public int getModIntelligence() {
        return modIntelligence;
    }

    public void setModIntelligence(int modIntelligence) {
        this.modIntelligence = modIntelligence;
    }

    public int getModDexterity() {
        return modDexterity;
    }

    public void setModDexterity(int modDexterity) {
        this.modDexterity = modDexterity;
    }

    public int getModWisdom() {
        return modWisdom;
    }

    public void setModWisdom(int modWisdom) {
        this.modWisdom = modWisdom;
    }

    public int getModConstitution() {
        return modConstitution;
    }

    public void setModConstitution(int modConstitution) {
        this.modConstitution = modConstitution;
    }

    public int getModCharisma() {
        return modCharisma;
    }

    public void setModCharisma(int modCharisma) {
        this.modCharisma = modCharisma;
    }

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }

    public int getHitpoints() { return hitpoints; }

    public void setHitpoints(int hitpoints) { this.hitpoints = hitpoints; }

    public int getStrength() { return strength; }

    public void setStrength(int strength) { this.strength = strength; }

    public int getIntelligence() { return intelligence; }

    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    public int getDexterity() { return dexterity; }

    public void setDexterity(int dexterity) { this.dexterity = dexterity; }

    public int getWisdom() { return wisdom; }

    public void setWisdom(int wisdom) { this.wisdom = wisdom; }

    public int getConstitution() { return constitution; }

    public void setConstitution(int constitution) { this.constitution = constitution; }

    public int getCharisma() { return charisma; }

    public void setCharisma(int charisma) { this.charisma = charisma; }


    public String getHitDie() {
        return hitDie;
    }

    public void setHitDie(String hitDie) {
        this.hitDie = hitDie;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getProfBonus() {
        return profBonus;
    }

    public void setProfBonus(int profBonus) {
        this.profBonus = profBonus;
    }
}
