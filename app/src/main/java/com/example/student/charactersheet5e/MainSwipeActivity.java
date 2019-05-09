package com.example.student.charactersheet5e;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

import AppModels.CharSheet;
import IO.WriteObject;

public class MainSwipeActivity extends AppCompatActivity {

    private CharSheet charSheet;



    @Override
    public void onBackPressed() {
        // Save crap here

        saveNewData();

        super.onBackPressed();
    }

    private void saveNewData() {
        TextView copper = findViewById(R.id.copper_text);
        charSheet.setCopper(Integer.parseInt(copper.getText().toString()));

        TextView silver = findViewById(R.id.silver_text);
        charSheet.setSilver(Integer.parseInt(silver.getText().toString()));

        TextView electrum = findViewById(R.id.electrum_text);
        charSheet.setElectrum(Integer.parseInt(electrum.getText().toString()));

        TextView gold = findViewById(R.id.gold_text);
        charSheet.setGold(Integer.parseInt(gold.getText().toString()));

        TextView plat = findViewById(R.id.platinum_text);
        charSheet.setPlatinum(Integer.parseInt(plat.getText().toString()));

        //At the end
        updateCharSheet();
    }

    private void updateCharSheet() {

        //Delete old file
        File dir = this.getFilesDir();
        File file = new File(dir, charSheet.getCharacterName().replaceAll(" ", "_") + ".ser");
        boolean deleted = file.delete();

        WriteObject obj = new WriteObject(this);
        //Save object
        obj.serializeCharacter(charSheet);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_sheet_swipe);

        getSupportActionBar().hide();

        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);



    }

    public CharSheet getCharSheet() {
        return charSheet;
    }

    public void setCharSheet(CharSheet charSheet) {
        this.charSheet = charSheet;
    }
}


