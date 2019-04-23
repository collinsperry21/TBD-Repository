package AppModels;

public class Race {

    private String raceName;
    private int statMod;
    private int baseSpeed;

    public Race(String raceName){
      setRaceName(raceName);
    }


    public void setBaseSpeed(int baseSpeed){ this.baseSpeed = baseSpeed; }

    public int getBaseSpeed() { return baseSpeed; }


    public int getStatMod() { return statMod; }

    public void setStatMod(int statMod) { this.statMod = statMod; }

    public String getRaceName() { return raceName; }

    public void setRaceName(String raceName) { this.raceName = raceName; }



}
