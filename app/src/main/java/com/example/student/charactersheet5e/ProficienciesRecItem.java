package com.example.student.charactersheet5e;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ProficienciesRecItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public ProficienciesRecItem(String text1, String text2)
    {
        //mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public int getmImageResource()
    {
        return mImageResource;
    }

    public String getmText1(){
        return mText1;
    }

    public String getmText2()
    {
        return mText2;
    }

}
