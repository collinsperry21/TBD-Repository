package com.example.student.charactersheet5e;

public class CharacterCardView {
    private int mPortrait;
    private String mRace;
    private String mName;

    public CharacterCardView( int portrait, String name, String race)
    {
        mPortrait = portrait;
        mName = name;
        mRace = race;

    }

    public int getPortrait() {
        return mPortrait;
    }

    public void setPortrait(int mPortrait) {
        this.mPortrait = mPortrait;
    }

    public String getRace() {
        return mRace;
    }

    public void setRace(String mRace) {
        this.mRace = mRace;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
