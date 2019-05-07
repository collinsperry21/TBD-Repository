package com.example.student.charactersheet5e;

import java.io.Serializable;

public class WeaponsRecItem implements Serializable {


    private String name;
    private String damage;
    private String weight;
    private String properties;

    public WeaponsRecItem(String newName, String newDamage, String newWeight, String newProperties){
        name = newName;
        damage = newDamage;
        weight = newWeight;
        properties = newProperties;
    }

    public String getName() {
        return name;
    }

    public String getDamage() {
        return damage;
    }

    public String getWeight() {
        return weight;
    }

    public String getProperties() {
        return properties;
    }
}
