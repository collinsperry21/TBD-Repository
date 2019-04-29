package AppModels;

import java.io.Serializable;
import java.util.ArrayList;

public class Proficiencies implements Serializable {
    private ArrayList<String> classSkills;
    private ArrayList<String> classProf;
    private ArrayList<String> raceProf;

    public ArrayList<String> getClassSkills() {
        return classSkills;
    }

    public void setClassSkills(ArrayList<String> classSkills) {
        this.classSkills = classSkills;
    }

    public ArrayList<String> getClassProf() {
        return classProf;
    }

    public void setClassProf(ArrayList<String> classProf) {
        this.classProf = classProf;
    }

    public ArrayList<String> getRaceProf() {
        return raceProf;
    }

    public void setRaceProf(ArrayList<String> raceProf) {
        this.raceProf = raceProf;
    }
}
