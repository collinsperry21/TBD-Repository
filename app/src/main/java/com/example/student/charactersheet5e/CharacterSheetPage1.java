package com.example.student.charactersheet5e;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private TextView characterName;
    private TextView characterLvl;

    private TextView hitPoints;
    private TextView hitDie;
    private TextView armorClass;
    private TextView initiative;
    private TextView speed;
    private TextView prof;
    
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



        return rootView;
    }

    private void setUpStats() {
        characterName = rootView.findViewById(R.id.char_name_disp_text);
        characterLvl = rootView.findViewById(R.id.character_sheet_level);
        initiative = rootView.findViewById(R.id.character_sheet_initiative);
        hitDie = rootView.findViewById(R.id.character_sheet_hitdice);
        hitPoints = rootView.findViewById(R.id.character_sheet_hp);
        speed = rootView.findViewById(R.id.character_sheet_speed);
        prof = rootView.findViewById(R.id.character_sheet_prof);

        characterName.setText(charSheet.getCharacterName());
        characterLvl.setText(Integer.toString(charSheet.getCharLevel()));
        hitPoints.setText(Integer.toString(charSheet.getCharStats().getHitpoints()));
        hitDie.setText(charSheet.getCharStats().getHitDie());
        speed.setText(Integer.toString(charSheet.getCharStats().getSpeed()));
        prof.setText("+" + Integer.toString(charSheet.getCharStats().getProfBonus()));
        initiative.setText(Integer.toString(charSheet.getCharStats().getDexterity()/2-5));

        setArmorClass();
    }

    private void setArmorClass()
    {
        armorClass = rootView.findViewById(R.id.character_sheet_armorclass);

        int armor = (charSheet.getCharStats().getDexterity()/2 - 5);

        charSheet.getmEquipment().add(new Equipment("Spear"));

        if( charSheet.getmEquipment() != null) {

            int armorBonus = 0;

            for(Equipment i : charSheet.getmEquipment()) {
                 armorBonus = getWearableArmor(i.getEquipName());
            }
            armor += armorBonus;


        }
        else {
            armor += 10;
        }

        armorClass.setText(Integer.toString(armor));


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