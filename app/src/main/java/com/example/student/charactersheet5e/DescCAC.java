package com.example.student.charactersheet5e;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import AppModels.CharSheet;
import AppModels.CharacterCardView;

public class DescCAC extends AppCompatActivity {

    private TextView name;
    private CharSheet charSheet;

    private Spinner lawfulSpinner;
    private Spinner goodSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_cac);

        //Set a new character sheet from the old one
        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        name = findViewById(R.id.character_name_text2);
        name.setText(charSheet.getCharacterName());

        setUpSpinners();
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
