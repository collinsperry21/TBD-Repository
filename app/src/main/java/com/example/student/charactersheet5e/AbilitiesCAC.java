package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import AppModels.CharSheet;

public class AbilitiesCAC extends AppCompatActivity {
    private TextView charName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abilities_cac);

        CharSheet charSheet = (CharSheet) (getIntent().getSerializableExtra("charSheet"));

        charName = findViewById(R.id.character_name_text);
        charName.setText(charSheet.getCharRace().getCharacterName());

    }
}
