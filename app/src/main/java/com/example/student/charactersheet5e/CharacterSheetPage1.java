package com.example.student.charactersheet5e;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import AppModels.CharSheet;
import AppModels.Equipment;

public class CharacterSheetPage1 extends Fragment {

    private String filename;
    private CharSheet charSheet;


    private TextView characterName;
    private TextView raceName;
    private TextView className;
    private TextView subclassName;
    private TextView level;
    private TextView experience;

    private TextView initiative;
    private TextView armorClass;
    private TextView speed;

    private TextView hitPoints;

    private TextView hitDie;

    private TextView strScoreText;
    private TextView dexScoreText;
    private TextView conScoreText;
    private TextView intScoreText;
    private TextView wisScoreText;
    private TextView chaScoreText;

    private TextView strModText;
    private TextView dexModText;
    private TextView conModText;
    private TextView intModText;
    private TextView wisModText;
    private TextView chaModText;


    private TextView prof;
    private TextView pasWis;

    private Button profBon;

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.character_sheet_page_1, container, false);

        MainSwipeActivity activity = (MainSwipeActivity) getActivity();
        charSheet = activity.getCharSheet();

        setUpStats();
        setUpAbilityScoreViews();

        profBon = rootView.findViewById(R.id.ProficienciesList_Button);

        profBon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Pop_Prof.class);
                intent.putExtra("characterSheet", charSheet);
                startActivity(intent);
            }
        });



        return rootView;
    }

    private void setUpStats() {
        //Connect variables to layout
        characterName = rootView.findViewById(R.id.char_name_disp_text);
        raceName = rootView.findViewById(R.id.display_race_text);
        className = rootView.findViewById(R.id.display_class_text);
        subclassName = rootView.findViewById(R.id.display_subclass_text);
        level = rootView.findViewById(R.id.display_level_text);
        experience = rootView.findViewById(R.id.display_experience_text);

        initiative = rootView.findViewById(R.id.display_initiative_text);
        //ArmorClass set in function
        speed = rootView.findViewById(R.id.display_speed_text);

        hitPoints = rootView.findViewById(R.id.display_HitPoints_text);

        hitDie = rootView.findViewById(R.id.display_HitDice_text);

        prof = rootView.findViewById(R.id.display_ProfBonus_text);
        pasWis = rootView.findViewById(R.id.display_passiveWisdom_text);

        //SetText
        characterName.setText("Name: " + charSheet.getCharacterName());
        //Set race to race name or subrace name if it exists
        String race = charSheet.getCharRace().getRaceName();
        if(charSheet.getCharRace().getHasSubrace())
            race = charSheet.getCharRace().getSubraceName();
        raceName.setText("Race: " + race);
        className.setText("Class: " + charSheet.getCharClass().getClassName());
        subclassName.setText(charSheet.getCharClass().getSubclassName());
        level.setText("Level: " + charSheet.getCharLevel());
        experience.setText("Exp: " + charSheet.getCharExp());

        int init = charSheet.getCharStats().getDexterity()/2-5;
        initiative.setText("\n+" + init);
        setArmorClass();
        int spd = charSheet.getCharStats().getSpeed();
        speed.setText("\n" + spd);

        int maxHP = charSheet.getCharStats().getHitpoints();
        hitPoints.setText(maxHP + "/" + maxHP);//Todo: make current health/max health

        hitDie.setText("\n" + charSheet.getCharStats().getHitDie());

        prof.setText("+" + Integer.toString(charSheet.getCharStats().getProfBonus()));

        pasWis.setText(Integer.toString(10 + charSheet.getCharStats().getWisdom()/2 -5));


    }

    private void setArmorClass()
    {
        armorClass = rootView.findViewById(R.id.display_armorClass_text);

        int armor = (charSheet.getCharStats().getDexterity()/2 - 5);

        //Test: charSheet.getmEquipment().add(new Equipment("Hide"));

        if( charSheet.getmEquipment().size() != 0) {

            int armorBonus = 0;

            for(Equipment i : charSheet.getmEquipment()) {
                 armorBonus = getWearableArmor(i.getEquipName());
            }
            armor += armorBonus;


        }
        else
        {
            armor += 10;
        }

        armorClass.setText("\n+" + armor);


    }

    private int getWearableArmor(String name) {

        try
        {
            //Open the file
            InputStream inStream = getContext().getAssets().open("equipment_5e.json");
            int size = inStream.available();
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i =0; i < jsonArray.length(); i++)
            {
                //Add class name to list of classes
                JSONObject obj = jsonArray.getJSONObject(i);


                if (obj.getString("name").equals(name) && obj.getString("equipment_category").equals("Armor"))
                {


                    return obj.getJSONObject("armor_class").getInt("base");

                }

            }
            return 10;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    private void setUpAbilityScoreViews(){
        //Set up text views
        strScoreText = rootView.findViewById(R.id.character_sheet_str);
        dexScoreText = rootView.findViewById(R.id.character_sheet_dex);
        conScoreText = rootView.findViewById(R.id.character_sheet_con);
        intScoreText = rootView.findViewById(R.id.character_sheet_int);
        wisScoreText = rootView.findViewById(R.id.character_sheet_wis);
        chaScoreText = rootView.findViewById(R.id.character_sheet_cha);

        strScoreText.setText(Integer.toString(charSheet.getCharStats().getStrength()));
        dexScoreText.setText(Integer.toString(charSheet.getCharStats().getDexterity()));
        conScoreText.setText(Integer.toString(charSheet.getCharStats().getConstitution()));
        intScoreText.setText(Integer.toString(charSheet.getCharStats().getIntelligence()));
        wisScoreText.setText(Integer.toString(charSheet.getCharStats().getWisdom()));
        chaScoreText.setText(Integer.toString(charSheet.getCharStats().getCharisma()));

        strModText = rootView.findViewById(R.id.char_sheet_mod_str);
        dexModText = rootView.findViewById(R.id.char_sheet_mod_dex);
        conModText = rootView.findViewById(R.id.char_sheet_mod_con);
        intModText = rootView.findViewById(R.id.char_sheet_mod_int);
        wisModText = rootView.findViewById(R.id.char_sheet_mod_wis);
        chaModText = rootView.findViewById(R.id.char_sheet_mod_cha);

        strModText.setText(Integer.toString(charSheet.getCharStats().getStrength()/2 - 5));
        dexModText.setText(Integer.toString(charSheet.getCharStats().getDexterity()/2 - 5));
        conModText.setText(Integer.toString(charSheet.getCharStats().getConstitution()/2 - 5));
        intModText.setText(Integer.toString(charSheet.getCharStats().getIntelligence()/2 - 5));
        wisModText.setText(Integer.toString(charSheet.getCharStats().getWisdom()/2 - 5));
        chaModText.setText(Integer.toString(charSheet.getCharStats().getCharisma()/2 - 5));




    }
}