package AppModels;

public class Class {
    private String className;
    private int statMod;
    private String primaryStat;

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


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
