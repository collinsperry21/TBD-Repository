package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import AppModels.CharSheet;

public class AbilitiesCAC extends AppCompatActivity
{
    private TextView charName;
    private Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abilities_cac);

        //Connect variables to layout
        helpButton = findViewById(R.id.help_Button);

        //Set a new character sheet from the old one ( may be a better way to do this?? )
        CharSheet charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        charName = findViewById(R.id.character_name_text);
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

    }
}
