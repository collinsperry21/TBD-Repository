package com.example.student.charactersheet5e;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import AppModels.CharSheet;

public class ProficienciesCAC extends AppCompatActivity {
    private CharSheet charSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiencies_cac);

        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Connect variables to layout
        TextView proficiencyBonusText = findViewById(R.id.proficiencyBonus_Text);
        TextView proficiencyDescText = findViewById(R.id.proficiencies_desc_text);

        //Assuming lvl 1 set proficiency Bonus
        proficiencyBonusText.setText("+2");
        //TODO: Save proficiency bonus

        //
    }
}
