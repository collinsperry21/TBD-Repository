package AppModels;

import java.io.Serializable;

public class Equipment implements Serializable {
    private String equipName;
    private String equipType;

    public Equipment(String name)
    { equipName = name; }

    //get/set equipment type
    public String getEquipType() { return equipType; }
    public void setEquipType(String equipType) { this.equipType = equipType; }


    public String getEquipName() { return equipName; }

    public void setEquipName(String equipName) { this.equipName = equipName; }

    }




