package com.example.student.charactersheet5e;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import AppModels.CharSheet;

public class CharacterSheetPage2 extends Fragment {

    private TextView age;
    private TextView sex;
    private TextView eyes;
    private TextView weight;
    private TextView height;
    private TextView skin;
    private TextView bg;
    private TextView align;
    private CharSheet charSheet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.character_sheet_page_2, container, false);


        MainSwipeActivity activity = (MainSwipeActivity) getActivity();
        charSheet = activity.getCharSheet();
        setUpDesc(rootView);

        return rootView;


    }

    private void setUpDesc(View rootView) {
        age = rootView.findViewById(R.id.disp_age_text);
        sex = rootView.findViewById(R.id.disp_hair_text);
        eyes = rootView.findViewById(R.id.disp_eyes_text);
        skin = rootView.findViewById(R.id.disp_skin_text);
        height = rootView.findViewById(R.id.disp_height_text);
        weight = rootView.findViewById(R.id.disp_weight_text);
        bg = rootView.findViewById(R.id.display_background_text);
        align = rootView.findViewById(R.id.disp_alignment_text);


        age.setText(charSheet.getCharacterDescription().getAge());
        sex.setText(charSheet.getCharacterDescription().getGender());
        eyes.setText(charSheet.getCharacterDescription().getEyeColor());
        skin.setText(charSheet.getCharacterDescription().getSkin());
        height.setText(charSheet.getCharacterDescription().getHeight());
        weight.setText(charSheet.getCharacterDescription().getWeight());
        bg.setText(charSheet.getCharacterDescription().getBackground());
        align.setText(charSheet.getCharacterDescription().getAlignment());




    }
}