package com.example.student.charactersheet5e;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import AppModels.CharSheet;
import AppModels.CharacterCardView;
import IO.LoadAdapter;
import IO.ReadObject;

//Need to clean this whole thing up
public class Pop_Load extends AppCompatActivity {

    private ReadObject obj = new ReadObject(this);

    private RecyclerView mRecyclerView;
    private LoadAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<CharacterCardView> characterCardViews;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_CustomPopTheme);
        setContentView(R.layout.activity_pop_load);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //set the window to 80% on both w and h
        getWindow().setLayout((int)(width*.9),(int)(height*.8));

        CharSheet character = new CharSheet();

        //Array for list of filenames
        ArrayList<String>  nameList = new ArrayList<>();

        //Fin all files with .ser
        File[] serFiles = finder(this.getFilesDir().getAbsolutePath());

        //Array list for cards
        characterCardViews = new ArrayList<>();





        for( File file : serFiles)
        {
            nameList.add(file.getName());
        }

        //If there are files
        if(nameList.size() != 0) {

            for (int i = 0; i < nameList.size(); i++) {
                character = obj.deserialzeCharacter(nameList.get(i));
                characterCardViews.add(new CharacterCardView(R.drawable.ic_android,character.getCharRace().getCharacterName(),character.getCharRace().getRaceName()));
            }
        }
        else{

        }

        mRecyclerView = findViewById(R.id.character_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LoadAdapter(characterCardViews);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

       // ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
       // new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        mAdapter.setOnItemClickListener(new LoadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");            }
        });




    }

    public void removeItem(int position)
    {
        characterCardViews.remove(position);
        mAdapter.notifyDataSetChanged();
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
