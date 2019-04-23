import AppModels.Race;
import AppModels.Class;
import AppModels.Stats;

public class charSheet {
    private Race charRace;
    private Class charClass;
    private int charLevel;
    private int charExp;
    private Stats charStats;

    public charSheet(Race race, Class cClass ,int level,int xp, Stats stats )
    {
        setCharRace(race);
        setCharClass(cClass);
        setCharLevel(1);
        setCharExp(0);
        setCharStats(stats);


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

        public Class getCharClass () {
        return charClass;
    }

        public void setCharClass (Class charClass){
        this.charClass = charClass;
    }

    public void modStats(){
        
    }




}
