package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import AppModels.CharSheet;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class AbilitiesCAC extends AppCompatActivity
{
    private TextView charName;
    private Button helpButton;
    private Button autoGen;
    private Button navigateToNext;
    //score fields
    private EditText strButton;
    private EditText dexButton;
    private EditText conButton;
    private EditText intButton;
    private EditText wisButton;
    private EditText chaButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abilities_cac);

        //Connect variables to layout
        charName = findViewById(R.id.character_name_text);
        helpButton = findViewById(R.id.help_Button);
        autoGen = findViewById(R.id.auto_button);
        navigateToNext = findViewById(R.id.navigate_review_CAC);

        //Ability score text inputs
        strButton = findViewById(R.id.StrengthInput);
        dexButton = findViewById(R.id.DexterityInput);
        conButton = findViewById(R.id.ConstitutionInput);
        intButton = findViewById(R.id.IntelligenceInput);
        wisButton = findViewById(R.id.WisdomInput);
        chaButton = findViewById(R.id.CharismaInput);



        //Set a new character sheet from the old one ( may be a better way to do this?? )
        final CharSheet charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Set the text to the name of the character
        charName.setText(charSheet.getCharRace().getCharacterName());

        //Help button
        helpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), Pop_Help_Activity.class);
                startActivity(i);
            }
        });

        //Auto Gen Button
        autoGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AutoGenerate(charSheet.getCharClass().getClassName());
            }
        });

        //Next Button
        navigateToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewCAC.class);
                //send the character sheet to the next activity to add scores
                intent.putExtra("characterSheet", charSheet);
                startActivity(intent);
            }
        });

    }

    public void AutoGenerate(String charClass){

        switch (charClass)
        {
            case "Barbarian":
                strButton.setText("15");
                conButton.setText("14");
                dexButton.setText("13");
                intButton.setText("10");
                wisButton.setText("12");
                chaButton.setText("8");
                break;
            case "Bard":
                strButton.setText("10");
                conButton.setText("13");
                dexButton.setText("14");
                intButton.setText("12");
                wisButton.setText("8");
                chaButton.setText("15");
                break;
            case "Cleric":
                strButton.setText("14");
                conButton.setText("13");
                dexButton.setText("10");
                intButton.setText("8");
                wisButton.setText("15");
                chaButton.setText("12");
                break;
            case "Druid":
                strButton.setText("8");
                conButton.setText("13");
                dexButton.setText("14");
                intButton.setText("10");
                wisButton.setText("15");
                chaButton.setText("12");
                break;
            case "Fighter":
                strButton.setText("13");
                conButton.setText("14");
                dexButton.setText("15");
                intButton.setText("8");
                wisButton.setText("10");
                chaButton.setText("12");
                break;
            case "Monk":
                strButton.setText("8");
                conButton.setText("12");
                dexButton.setText("15");
                intButton.setText("10");
                wisButton.setText("14");
                chaButton.setText("13");
                break;
            case "Paladin":
                strButton.setText("15");
                conButton.setText("14");
                dexButton.setText("12");
                intButton.setText("8");
                wisButton.setText("10");
                chaButton.setText("13");
                break;
            case "Ranger":
                strButton.setText("8");
                conButton.setText("13");
                dexButton.setText("15");
                intButton.setText("8");
                wisButton.setText("14");
                chaButton.setText("12");
                break;
            case "Rogue":
                strButton.setText("13");
                conButton.setText("12");
                dexButton.setText("15");
                intButton.setText("8");
                wisButton.setText("10");
                chaButton.setText("14");
                break;
            case "Sorcerer":
                strButton.setText("8");
                conButton.setText("13");
                dexButton.setText("14");
                intButton.setText("10");
                wisButton.setText("12");
                chaButton.setText("15");
                break;
            case "Warlock":
                strButton.setText("10");
                conButton.setText("14");
                dexButton.setText("13");
                intButton.setText("8");
                wisButton.setText("12");
                chaButton.setText("15");
                break;
            case "Wizard":
                strButton.setText("8");
                conButton.setText("12");
                dexButton.setText("13");
                intButton.setText("15");
                wisButton.setText("14");
                chaButton.setText("10");
                break;
        }
        Toast.makeText(getBaseContext(), "Ability scores assigned based on " + charClass + " Class" ,
                Toast.LENGTH_SHORT).show();

    }


}
