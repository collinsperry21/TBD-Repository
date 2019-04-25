package AppModels;


import java.io.Console;

public class charSheet {

    private String charName;
    private Race charRace;
    private Class charClass;
    private int charLevel;
    private int charExp;
    private Stats charStats;

    public charSheet(String name, Race race, Class cClass, Stats stats) {
        setCharName(name);
        setCharRace(race);
        setCharClass(cClass);
        setCharLevel(1);
        setCharExp(0);
        setCharStats(stats);
        stats.setSpeed(race.getBaseSpeed());


    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Stats getCharStats() {
        return charStats;
    }

    public void setCharStats(Stats charStats) {
        this.charStats = charStats;
    }

    public int getCharLevel() {
        return charLevel;
    }

    public void setCharLevel(int charLevel) {
        this.charLevel = charLevel;
    }

    public int getCharExp() {
        return charExp;
    }

    public void setCharExp(int charExp) {
        this.charExp = charExp;
    }

    public Race getCharRace() {
        return charRace;
    }

    public void setCharRace(Race charRace) {
        this.charRace = charRace;
    }

    public Class getCharClass() {
        return charClass;
    }

    public void setCharClass(Class charClass) {
        this.charClass = charClass;
    }

    public void modStats() {

    }


    public void levelUp() {


    }
}



