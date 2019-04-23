package AppModels;

public class Class {
    private String className;
    private String primaryStat;

    public Class(String className)
    {
        setClassName(className);
    }

    public String getPrimaryStat() {
        return primaryStat;
    }

    public void setPrimaryStat(String primaryStat) {
        this.primaryStat = primaryStat;
    }

    public String getClassName() { return className; }

    public void setClassName(String className) { this.className = className; }
}
