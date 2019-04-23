package com.example.student.charactersheet5e;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import AppModels.CharSheet;

public class ReviewCAC extends AppCompatActivity
{
    private TextView raceModsTextView;
    private TextView raceModsListTextView;
    private TextView strScoreText;
    private TextView dexScoreText;
    private TextView conScoreText;
    private TextView intScoreText;
    private TextView wisScoreText;
    private TextView chaScoreText;

    private TextView strModText;
    private TextView dexModText;
    private TextView conModText;
    private TextView intModText;
    private TextView wisModText;
    private TextView chaModText;

    private CharSheet charSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cac);

        //Connect variables to layout
        raceModsTextView = findViewById(R.id.raceModsDescriptionText);
        raceModsListTextView = findViewById(R.id.raceModsListText);

        strScoreText = findViewById(R.id.strength_score_text);
        dexScoreText = findViewById(R.id.dexterity_score_text);
        conScoreText = findViewById(R.id.constitution_score_text);
        intScoreText = findViewById(R.id.intelligence_score_text);
        wisScoreText = findViewById(R.id.wisdom_score_text);
        chaScoreText = findViewById(R.id.charisma_score_text);

        strModText = findViewById(R.id.strength_mod_disp_text);
        dexModText = findViewById(R.id.dexterity_mod_disp_text);
        conModText = findViewById(R.id.constitution_mod_disp_text);
        intModText = findViewById(R.id.intelligence_mod_disp_text);
        wisModText = findViewById(R.id.wisdom_mod_disp_text);
        chaModText = findViewById(R.id.charisma_mod_disp_text);




        //Set a new character sheet from the old one ( may be a better way to do this?? )
        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Insert chosen race into race mods description text
        String modsDescriptionText = charSheet.getCharRace().getRaceName() + "s have the following ability score mods:";
        raceModsTextView.setText(modsDescriptionText);

        //Insert a list of mods retrieved from Race into race mods list text
        String modsListText = GetModsList(charSheet.getCharRace().getRaceName());
        raceModsListTextView.setText(modsListText);

        SetAbilityScore();



    }

    private void SetAbilityScore() {
        //Create an array with all ability scores
        int[] abilityScores;
        abilityScores = new int[] {charSheet.getCharStats().getStrength(),
                charSheet.getCharStats().getDexterity(),
                charSheet.getCharStats().getConstitution(),
                charSheet.getCharStats().getIntelligence(),
                charSheet.getCharStats().getWisdom(),
                charSheet.getCharStats().getCharisma()};

        strScoreText.setText(Integer.toString(abilityScores[0]));
        dexScoreText.setText(Integer.toString(abilityScores[1]));
        conScoreText.setText(Integer.toString(abilityScores[2]));
        intScoreText.setText(Integer.toString(abilityScores[3]));
        wisScoreText.setText(Integer.toString(abilityScores[4]));
        chaScoreText.setText(Integer.toString(abilityScores[5]));

        strModText.setText( Integer.toString( (abilityScores[0]/2) - 5) );
        dexModText.setText( Integer.toString( (abilityScores[1]/2) - 5));
        conModText.setText(Integer.toString( (abilityScores[2]/2) - 5));
        intModText.setText(Integer.toString( (abilityScores[3]/2) - 5));
        wisModText.setText(Integer.toString( (abilityScores[4]/2) - 5));
        chaModText.setText(Integer.toString( (abilityScores[5]/2) - 5));
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


