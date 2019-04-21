package AppModels;

import java.io.Serializable;

public class Race implements Serializable {

    private String raceName;
    private String subraceName;
    private String characterName;
    private int statMod;
    private String primaryStat;
    private boolean hasSubrace;

    public String getPrimaryStat() {
        return primaryStat;
    }

    public void setPrimaryStat(String primaryStat) {
        this.primaryStat = primaryStat;
    }


    public int getStatMod() {
        return statMod;
    }

    public void setStatMod(int statMod) {
        this.statMod = statMod;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }


    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getSubraceName() {
        return subraceName;
    }

    public void setSubraceName(String subraceName) {
        this.subraceName = subraceName;
    }

    public boolean getHasSubrace() {
        return hasSubrace;
    }

    public void setHasSubrace(boolean hasSubrace) {
        this.hasSubrace = hasSubrace;
    }
}
