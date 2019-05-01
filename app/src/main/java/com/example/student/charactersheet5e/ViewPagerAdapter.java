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
                return new CharacterSheetPage1(); //CharacterSheetPage1 at position 0
            case 1:
                return new CharacterSheetPage2(); //CharacterSheetPage2 at position 1
            case 2:
                return new CharacterSheetPage3(); //CharacterSheetPage3 at position 2
        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        return 3; //three fragments
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "Page " + Integer.toString(position + 1);
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}