package AppModels;

import java.io.Serializable;
import java.util.ArrayList;

public class CharSheet implements Serializable {
    private Race charRace;
    private CharClass charClass;
    private int charLevel;
    private int charExp;
    private Stats charStats;
    private String characterName;

    private ArrayList<Equipment> mEquipment;

    public CharSheet(String xname, Race race, CharClass cCharClass, Stats stats )
    {
        setCharRace(race);
        setCharClass(cCharClass);
        setCharLevel(1);
        setCharExp(0);
        setCharStats(stats);
        characterName = xname;


    }
    //Default constructor
    public CharSheet()
    {
        setCharRace(new Race("Race"));
        setCharClass(new CharClass("Class"));
        setCharLevel(1);
        setCharExp(0);
        setCharStats(new Stats());

    }



    public Stats getCharStats() {
        return charStats;
    }

    public void setCharStats(Stats charStats) {
        this.charStats = charStats;
    }

    public int getCharLevel () {
        return charLevel;
    }

    public void setCharLevel ( int charLevel){
        this.charLevel = charLevel;
    }

    public int getCharExp () {
        return charExp;
    }

    public void setCharExp ( int charExp){
        this.charExp = charExp;
    }

    public Race getCharRace () {
        return charRace;
    }

    public void setCharRace (Race charRace){
        this.charRace = charRace;
    }

    public CharClass getCharClass () {
        return charClass;
    }

    public void setCharClass (CharClass charClass){
        this.charClass = charClass;
    }


    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public ArrayList<Equipment> getmEquipment() {
        return mEquipment;
    }

    public void setmEquipment(ArrayList<Equipment> mEquipment) {
        this.mEquipment = mEquipment;
    }
}