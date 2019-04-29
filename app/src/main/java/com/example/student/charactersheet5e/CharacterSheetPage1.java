package com.example.student.charactersheet5e;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import AppModels.CharSheet;
import IO.ReadObject;

public class CharacterSheetPage1 extends AppCompatActivity {

    private String filename;
    private CharSheet charSheet;
    private ReadObject obj = new ReadObject(this);

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_sheet_page_1);

        //Read the file in and store it in a charsheet object
        filename = (String) (getIntent().getSerializableExtra("characterSheetFile"));
        charSheet = obj.deserialzeCharacter(filename);

        setUpAbilityScoreViews();
        setUpStats();






    }

    private void setUpStats() {
        characterName = findViewById(R.id.char_name_disp_text);
        characterLvl = findViewById(R.id.character_sheet_level);
        initiative = findViewById(R.id.character_sheet_initiative);
        hitDie = findViewById(R.id.character_sheet_hitdice);
        hitPoints = findViewById(R.id.character_sheet_hp);
        speed = findViewById(R.id.character_sheet_speed);

        characterName.setText(charSheet.getCharacterName());
        characterLvl.setText(Integer.toString(charSheet.getCharLevel()));
        hitPoints.setText(Integer.toString(charSheet.getCharStats().getHitpoints()));
        hitDie.setText(charSheet.getCharStats().getHitDie());
        speed.setText(Integer.toString(charSheet.getCharStats().getSpeed()));
    }

    private void setUpAbilityScoreViews(){
        //Set up text views
        strScoreText = findViewById(R.id.character_sheet_str);
        dexScoreText = findViewById(R.id.character_sheet_dex);
        conScoreText = findViewById(R.id.character_sheet_con);
        intScoreText = findViewById(R.id.character_sheet_int);
        wisScoreText = findViewById(R.id.character_sheet_wis);
        chaScoreText = findViewById(R.id.character_sheet_cha);

        strScoreText.setText(Integer.toString(charSheet.getCharStats().getStrength()));
        dexScoreText.setText(Integer.toString(charSheet.getCharStats().getDexterity()));
        conScoreText.setText(Integer.toString(charSheet.getCharStats().getConstitution()));
        intScoreText.setText(Integer.toString(charSheet.getCharStats().getIntelligence()));
        wisScoreText.setText(Integer.toString(charSheet.getCharStats().getWisdom()));
        chaScoreText.setText(Integer.toString(charSheet.getCharStats().getCharisma()));


    }
}
