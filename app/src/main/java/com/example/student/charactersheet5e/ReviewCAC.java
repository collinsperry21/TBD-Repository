package com.example.student.charactersheet5e;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import AppModels.CharSheet;

public class ReviewCAC extends AppCompatActivity
{
    private TextView raceModsTextView;
    private TextView raceModsListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cac);

        //Connect variables to layout
        raceModsTextView = findViewById(R.id.raceModsDescriptionText);
        raceModsListTextView = findViewById(R.id.raceModsListText);

        //Set a new character sheet from the old one ( may be a better way to do this?? )
        final CharSheet charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Insert chosen race into race mods description text
        String modsDescriptionText = charSheet.getCharRace().getRaceName() + "s have the following ability score mods:";
        raceModsTextView.setText(modsDescriptionText);

        //Insert a list of mods retrieved from Race into race mods list text
        String modsListText = GetModsList(charSheet.getCharRace().getRaceName());
        raceModsListTextView.setText(modsListText);


    }

    private String GetModsList(String raceName)
    {
        String modsList = new String();
        try
        {
            InputStream inStream = getAssets().open("races_5e.json");
            int size = inStream.available();
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i =0; i < jsonArray.length(); i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);

                if (obj.getString("name").equals(raceName))
                {
                    JSONArray attributeJsonArray = obj.getJSONArray("ability_bonuses");
                    for (int index =0; index < attributeJsonArray.length(); index++)
                    {
                        JSONObject attributeObj = attributeJsonArray.getJSONObject(index);
                        modsList += attributeObj.getString("name") + " (+" +
                                attributeObj.getString("bonus") + ")\t\t";
                        if ((index + 1)%3 == 0)
                        {
                            modsList += "\n";
                        }
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        return modsList;
    }
}


