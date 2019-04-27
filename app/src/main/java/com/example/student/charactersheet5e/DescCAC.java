package com.example.student.charactersheet5e;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import AppModels.CharSheet;
import AppModels.CharacterCardView;

public class DescCAC extends AppCompatActivity {

    private TextView name;
    private CharSheet charSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_cac);

        //Set a new character sheet from the old one ( may be a better way to do this?? )
        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        name = findViewById(R.id.character_name_text2);
        name.setText(charSheet.getCharacterName());
    }
}
