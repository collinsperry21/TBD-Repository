package com.example.student.charactersheet5e;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new CharacterSheetPage2(); //CharacterSheetPage1 at position 0
            case 1:
                return new CharacterSheetPage1(); //CharacterSheetPage2 at position 1
            case 2:
                return new CharacterSheetPage3(); //CharacterSheetPage3 at position 2
            case 3:
                return new CharacterSheetPage4(); //CharacterSheetPage3 at position 3
        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        return 4; //three fragments
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch(position)
        {
            case 0:
                title = "Background";
                break;
            case 1:
                title = "Stats";
                break;
            case 2:
                title = "Attacks and Spells";
                break;
            case 3:
                title = "Equipment";
                break;
        }
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}