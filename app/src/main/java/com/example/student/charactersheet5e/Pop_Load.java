package com.example.student.charactersheet5e;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import AppModels.CharSheet;
import AppModels.CharacterCardView;
import IO.LoadAdapter;
import IO.ReadObject;
import IO.SwipeToDeleteCallback;

//Need to clean this whole thing up
public class Pop_Load extends AppCompatActivity {

    private ReadObject obj = new ReadObject(this);

    private RecyclerView mRecyclerView;
    private LoadAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView noFiles;

    private ArrayList<CharacterCardView> characterCardViews;

    private CharSheet character;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_CustomPopTheme);
        setContentView(R.layout.activity_pop_load);

        noFiles = findViewById(R.id.no_files);
        noFiles.setText("No saved characters. \n Create a new one!");

        mRecyclerView = findViewById(R.id.character_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //set the window to 80% on both w and h
        getWindow().setLayout((int)(width*.9),(int)(height*.8));



        //Array list for cards
        characterCardViews = new ArrayList<>();

        setUpView();

        mAdapter.setOnItemClickListener(new LoadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Pop_Load.this, MainSwipeActivity.class);
                intent.putExtra("characterSheet", characterCardViews.get(position).getCharacterSheet());
                startActivity(intent);
                finish();
            }
        });




    }

    private void setUpView() {
        setUpNameList();
        setUpRecyclerView();
    }

    private void setUpNameList() {

        CharSheet character = new CharSheet();

        //Array for list of filenames
        ArrayList<String>  nameList = new ArrayList<>();

        //Fin all files with .ser
        File[] serFiles = finder(this.getFilesDir().getAbsolutePath());

        for( File file : serFiles)
        {
            nameList.add(file.getName());
        }

        //If there are files
        if(nameList.size() != 0) {

            for (int i = 0; i < nameList.size(); i++) {
                character = obj.deserialzeCharacter(nameList.get(i));

                characterCardViews.add(new CharacterCardView(getIconID(character.getCharClass().getClassName()),
                        character.getCharacterName(),
                        character.getCharRace().getRaceName(),
                        character.getCharClass().getClassName(),
                        Integer.toString(character.getCharLevel()),
                        nameList.get(i),
                        character
                ));
            }
        }
        else{
            noFiles.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);

        }
    }
    private void setUpRecyclerView() {
        mAdapter = new LoadAdapter(characterCardViews, this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private int getIconID(String className) {
        switch (className)
        {
            case "Barbarian":
                return R.drawable.ic_barbarian;

            case "Bard":
                return R.drawable.ic_bard;

            case "Cleric":
                return R.drawable.ic_cleric;

            case "Druid":
                return R.drawable.ic_druid;

            case "Fighter":
                return R.drawable.ic_fighter;

            case "Monk":
                return R.drawable.ic_monk;

            case "Paladin":
                return R.drawable.ic_paladin;

            case "Ranger":
                return R.drawable.ic_ranger;

            case "Rogue":
                return R.drawable.ic_rogue;

            case "Sorcerer":
                return R.drawable.ic_sorcerer;

            case "Warlock":
                return R.drawable.ic_warlock;

            case "Wizard":
                return R.drawable.ic_wizard;
                
        }
        return R.drawable.ic_android;
    }

    public void changeItem(int position, String text){
        characterCardViews.get(position).changeName(text);
        mAdapter.notifyItemChanged(position);
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
