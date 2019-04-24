package AppModels;

import java.io.Serializable;

public class CharClass implements Serializable {
    private String className;
    private String subclassName;



    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }
}
