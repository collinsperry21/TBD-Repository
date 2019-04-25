package AppModels;

import android.os.Parcelable;

import java.io.Serializable;

import AppModels.CharClass;
import AppModels.Race;
import AppModels.Stats;

public class CharSheet implements Serializable {
    private Race charRace;
    private CharClass charClass;
    private int charLevel;
    private int charExp;
    private Stats charStats;
    private String mName;

    public CharSheet(String name, Race race, CharClass cCharClass, Stats stats )
    {
        setCharRace(race);
        setCharClass(cCharClass);
        setCharLevel(1);
        setCharExp(0);
        setCharStats(stats);
        mName = name;


    }
    //Default constructor
    public CharSheet()
    {

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


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}