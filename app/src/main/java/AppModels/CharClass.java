package AppModels;

import java.io.Serializable;

public class CharClass implements Serializable
{
    private String className;
    private String subclassName;

    public CharClass(String className){
        setClassName(className);
    }

    public CharClass(String className, String subclassName){
        setClassName(className);
        setSubclassName(subclassName);
    }



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
