package AppModels;

import com.example.student.charactersheet5e.ProficienciesCAC;

import java.io.Serializable;
import java.util.ArrayList;

public class CharSheet implements Serializable {
    private Race charRace;
    private CharClass charClass;
    private Proficiencies proficiencies;
    private int charLevel;
    private int charExp;
    private Stats charStats;
    private String characterName;
    private Description characterDescription;


    //Store money
    private int copper;
    private int silver;
    private int electrum;
    private int gold;
    private int platinum;

    private ArrayList<Equipment> mEquipment;


    public CharSheet(String xname, Race race, CharClass cCharClass, Stats stats )
    {
        setCharRace(race);
        setCharClass(cCharClass);
        setCharLevel(1);
        setCharExp(0);
        setCharStats(stats);
        characterName = xname;

        copper = 0;
        silver = 0;
        electrum = 0;
        gold = 0;
        platinum = 0;
    }
    //Default constructor
    public CharSheet() {
        setCharRace(new Race("Race"));
        setCharClass(new CharClass("Class"));
        setCharLevel(1);
        setCharExp(0);
        setCharStats(new Stats());
        mEquipment = new ArrayList<>();

        copper = 0;
        silver = 0;
        electrum = 0;
        gold = 0;
        platinum = 0;
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

    public Description getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(Description characterDescription) {
        this.characterDescription = characterDescription;
    }

    public Proficiencies getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(Proficiencies proficiencies) {
        this.proficiencies = proficiencies;
    }

    public int getCopper() {
        return copper;
    }

    public void setCopper(int copper) {
        this.copper = copper;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getElectrum() {
        return electrum;
    }

    public void setElectrum(int electrum) {
        this.electrum = electrum;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPlatinum() {
        return platinum;
    }

    public void setPlatinum(int platinum) {
        this.platinum = platinum;
    }

}