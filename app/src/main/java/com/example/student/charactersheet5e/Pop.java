package com.example.student.charactersheet5e;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import AppModels.CharSheet;

//Need to clean this whole thing up
public class Pop extends AppCompatActivity {

    private ReadObject obj = new ReadObject(this);
    private TextView charTest;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //set the window to 80% on both w and h
        getWindow().setLayout((int)(width*.8),(int)(height*.8));


        CharSheet character = new CharSheet();

        //Array for list of filenames
        ArrayList<String>  nameList = new ArrayList<>();

        //Fin all files with .ser
        File[] serFiles = finder(this.getFilesDir().getAbsolutePath());

        charTest = findViewById(R.id.char_one);

        for( File file : serFiles)
        {
            nameList.add(file.getName());
        }

        //If there are files
        if(nameList.size() != 0) {

            for (int i = 0; i < nameList.size(); i++) {
                character = obj.deserialzeCharacter(nameList.get(i));
                //TODO Ask Prof about dynamically creating constraint layouts that are clickable/Recycler view with card view
                charTest.setText(character.getCharRace().getCharacterName() +
                                "\b" + character.getCharRace().getRaceName() +
                                "\b" + character.getCharClass().getClassName() +
                                "\b" + character.getCharLevel() +
                                "\n" + charTest.getText());

            }
        }
        else{
            charTest.setText("No saved characters! \n Create one!");
        }



    }

    //Finds all the files with .ser in the files folder
    public File[] finder( String dirName)
    {
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String filename) {
                return filename.endsWith(".ser");
            }
        });
    }
}
