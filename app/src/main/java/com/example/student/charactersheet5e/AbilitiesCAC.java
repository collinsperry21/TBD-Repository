package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import AppModels.CharSheet;
import AppModels.Stats;


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

    //Testing for writing file
    WriteObject obj = new WriteObject(this);

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

        //Set array list for checking inputs
        final ArrayList <EditText> inputTestArray = new ArrayList<EditText>(
                Arrays.asList(strButton,dexButton,conButton,intButton,wisButton,chaButton));




        //Set a new character sheet from the old one ( may be a better way to do this?? )
        final CharSheet charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));
        //Stats model
        final Stats scoreStats = new Stats();

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
                //if all inputs check out
                if(CheckUserSelection(inputTestArray)) {
                    //add stats to character sheet
                    charSheet.setCharStats(setCharacter(inputTestArray, scoreStats));
                    Intent intent = new Intent(getApplicationContext(), ReviewCAC.class);
                    //send the character sheet to the next activity to add scores
                    intent.putExtra("characterSheet", charSheet);
                    //Testing to see if reading object is working, this is only temporarily here for demo
                    obj.serializeCharacter(charSheet);

                    startActivity(intent);
                }
            }
        });

    }

    private Stats setCharacter(ArrayList<EditText> input, Stats stats){
        //Set the values to each Stat in CharSheet
        stats.setStrength( Integer.valueOf( input.get(0).getText().toString() ) );
        stats.setDexterity( Integer.valueOf( input.get(1).getText().toString() ) );
        stats.setConstitution( Integer.valueOf( input.get(2).getText().toString() ) );
        stats.setIntelligence( Integer.valueOf( input.get(3).getText().toString() ) );
        stats.setWisdom( Integer.valueOf( input.get(4).getText().toString() ) );
        stats.setCharisma( Integer.valueOf( input.get(5).getText().toString() ) );

        return stats;
    }

    private Boolean CheckUserSelection(ArrayList<EditText> inputTest)
    {
        //For every EditText in the array
        int i = 0;
        while( i < inputTest.size() ) {

            //If there is no input
            if(inputTest.get(i).getText().toString().equals(""))
            {
                Toast.makeText(getBaseContext(), "You must complete all Ability Scores",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (Integer.valueOf(inputTest.get(i).getText().toString()) <=2 || Integer.valueOf(inputTest.get(i).getText().toString()) >= 19)
            {
                Toast.makeText(getBaseContext(), "Ability Scores must be between 3 and 18",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            i++;
        }
        //If it passes the loop
        return true;
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
