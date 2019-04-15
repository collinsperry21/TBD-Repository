package AppModels;

public class Race {

    private String raceName;
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



}
