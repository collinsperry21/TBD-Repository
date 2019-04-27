package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import AppModels.CharSheet;

public class ProficienciesCAC extends AppCompatActivity {
    private CharSheet charSheet;
    private ImageButton navigate_to_next;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiencies_cac);
        //Change action bar text
        getSupportActionBar().setTitle("Proficiencies");

        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Connect variables to layout
        TextView proficiencyBonusText = findViewById(R.id.proficiencyBonus_Text);
        TextView proficiencyDescText = findViewById(R.id.proficiencies_desc_text);

        navigate_to_next = findViewById(R.id.nextCAC);

        //Assuming lvl 1 set proficiency Bonus
        proficiencyBonusText.setText("+2");
        //TODO: Save proficiency bonus

        //

        navigate_to_next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ProficienciesCAC.this, DescCAC.class);

                //send the character sheet to the next activity to add scores
                intent.putExtra("characterSheet", charSheet);

                startActivity(intent);
            }
        });
    }
}
