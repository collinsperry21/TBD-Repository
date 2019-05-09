package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import AppModels.CharSheet;
import AppModels.CharacterCardView;
import AppModels.Description;
import IO.WriteObject;

public class DescCAC extends AppCompatActivity {

    private TextView name;
    private CharSheet charSheet;

    private Spinner lawfulSpinner;
    private Spinner goodSpinner;
    private EditText age;
    private EditText sex;
    private EditText eyes;
    private EditText hair;
    private EditText weight;
    private EditText height;
    private EditText skin;
    private EditText bg;


    private ImageButton endCAC;
    private Description description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_cac);

        //Testing for writing file
        WriteObject obj = new WriteObject(this);

        //Set a new character sheet from the old one
        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));
        description = new Description("Background");

        name = findViewById(R.id.character_name_text2);
        name.setText(charSheet.getCharacterName());

        endCAC = findViewById(R.id.endCAC);

        endCAC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DescCAC.this,MainActivity.class);

                setDescription();

                //Send to character sheet
                intent.putExtra("characterSheet", charSheet);

                intent.putExtra("EXIT", true);

                //Save object
                obj.serializeCharacter(charSheet);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });

        setUpSpinners();



    }

    private void setDescription() {
        age = findViewById(R.id.editAge);
        sex = findViewById(R.id.editSex);
        hair = findViewById(R.id.editHair);
        eyes = findViewById(R.id.editEyes);
        skin = findViewById(R.id.editSkin);
        height = findViewById(R.id.editHeight);
        weight = findViewById(R.id.editWeight);
        bg = findViewById(R.id.editBackground);

        description.setAge(age.getText().toString());
        description.setGender(sex.getText().toString());
        description.setHair(hair.getText().toString());
        description.setEyeColor(eyes.getText().toString());
        description.setSkin(skin.getText().toString());
        description.setHeight(height.getText().toString());
        description.setWeight(weight.getText().toString());
        description.setBackground(bg.getText().toString());

        description.setAlignment(lawfulSpinner.getSelectedItem().toString() + " " + goodSpinner.getSelectedItem().toString());

        charSheet.setCharacterDescription(description);

    }

    private void setUpSpinners() {
        ArrayList<String> lawfulList = new ArrayList<>();
        ArrayList<String> goodList = new ArrayList<>();

        lawfulList.add("Lawful");
        lawfulList.add("Neutral");
        lawfulList.add("Chaotic");

        goodList.add("Good");
        goodList.add("Neutral");
        goodList.add("Evil");
        lawfulSpinner = findViewById(R.id.law_spinner);
        goodSpinner = findViewById(R.id.good_spinner);

        ArrayAdapter<String> lawfulAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, lawfulList);
        lawfulAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> goodAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, goodList);
        goodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner lawfulSpinner = (Spinner) findViewById(R.id.law_spinner);
        lawfulSpinner.setAdapter(lawfulAdapter);

        Spinner goodSpinner = (Spinner) findViewById(R.id.good_spinner);
        goodSpinner.setAdapter(goodAdapter);

    }
}
