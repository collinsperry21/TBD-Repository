package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import AppModels.CharSheet;

public class MainActivity extends AppCompatActivity {

    private Button navigate_to_CAC;
    private Button loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigate_to_CAC = findViewById(R.id.Start_CAC);
        navigate_to_CAC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,BeginCAC.class);
                startActivity(intent);
            }
        });


        if( getIntent().hasExtra("EXIT"))
        {
            //Set a new character sheet from the old one
            Intent intent = new Intent(MainActivity.this, CharacterSheetPage1.class);
            intent.putExtra("characterSheet", (CharSheet) (getIntent().getSerializableExtra("characterSheet")));
            startActivity(intent);

        }



        loadButton =  findViewById(R.id.load_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Pop_Load.class);
                startActivity(intent);
            }
        });

    }
}
