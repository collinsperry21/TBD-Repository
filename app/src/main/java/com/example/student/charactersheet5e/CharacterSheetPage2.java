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

    private TextView name;
    private TextView age;
    private TextView sex;
    private TextView eyes;
    private TextView hair;
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
        name = rootView.findViewById(R.id.disp_name_text);
        age = rootView.findViewById(R.id.disp_age_text);
        sex = rootView.findViewById(R.id.disp_sex_text);
        eyes = rootView.findViewById(R.id.disp_eyes_text);
        hair = rootView.findViewById(R.id.disp_hair_text);
        skin = rootView.findViewById(R.id.disp_skin_text);
        height = rootView.findViewById(R.id.disp_height_text);
        weight = rootView.findViewById(R.id.disp_weight_text);
        bg = rootView.findViewById(R.id.display_background_text);
        align = rootView.findViewById(R.id.disp_alignment_text);

        name.setText(charSheet.getCharacterName() + ", the" + charSheet.getCharClass().getClassName());
        age.setText("Age: " + charSheet.getCharacterDescription().getAge());
        sex.setText("Gender: " + charSheet.getCharacterDescription().getGender());
        eyes.setText("Eyes: " + charSheet.getCharacterDescription().getEyeColor());
        hair.setText("Hair: " + charSheet.getCharacterDescription().getHair());
        skin.setText("Skin: " + charSheet.getCharacterDescription().getSkin());
        height.setText("Height: " + charSheet.getCharacterDescription().getHeight());
        weight.setText("Weight: " + charSheet.getCharacterDescription().getWeight());
        bg.setText("Background: " + charSheet.getCharacterDescription().getBackground());
        align.setText("Alignment: " + charSheet.getCharacterDescription().getAlignment());




    }
}