package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class BeginCAC extends AppCompatActivity {

    private Button navigate_next_CAC;
    Spinner raceSpinner;
    Spinner subraceSpinner;
    Spinner classSpinner;


    String[] races = //Replace with a function to call for a list of races
    {
        "Choose One",
        "Dragonborn",
        "Dwarf",
        "Elf",
        "etc..."
    };

    String[] subraces = //Replace with a function to call for a list of
                        // subraces with a check to see if list is empty
    {
        "Choose One",
        "None"
    };

    String[] classes = //Replace with a function to call for a list of classes
    {
        "Choose One",
        "Barbarian",
        "Bard",
        "Cleric",
        "etc..."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_cac);

        // Get references from layout/main_activity.xml
        raceSpinner =(Spinner)findViewById(R.id.RaceSpinner);
        subraceSpinner = (Spinner)findViewById(R.id.SubraceSpinner);
        classSpinner = (Spinner)findViewById(R.id.ClassSpinner);
        navigate_next_CAC = findViewById(R.id.NextCAC01);

        //Set Array Adaptor
        //Race
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item ,races);
        raceSpinner.setAdapter(adapter);
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                // Get select item
                int sid = raceSpinner.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected Race : " + races[sid],
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // TODO Auto-generated method stub
            }
        });

        //Subrace
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, subraces);
        subraceSpinner.setAdapter(adapter);
        subraceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                // Get select item
                int sid = subraceSpinner.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected Subrace : " + subraces[sid],
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // TODO Auto-generated method stub
            }
        });

        //Class
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, classes);
        classSpinner.setAdapter(adapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                // Get select item
                int sid = classSpinner.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected Class : " + classes[sid],
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // TODO Auto-generated method stub
            }
        });

        //Code for navigating to next page
        navigate_next_CAC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(BeginCAC.this, AbilitiesCAC.class);
                startActivity(intent);
            }
        });
    }
}
