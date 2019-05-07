package com.example.student.charactersheet5e;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import AppModels.CharSheet;

public class MainSwipeActivity extends AppCompatActivity {

    private CharSheet charSheet;

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
