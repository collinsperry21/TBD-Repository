package com.example.student.charactersheet5e;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import AppModels.CharClass;
import AppModels.CharSheet;
import AppModels.Race;
import AppModels.Stats;

public class CharSheetActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_sheet_page_1);
        CharSheet bobBarbarian = new CharSheet("Bob", new Race("Dwarf"), new CharClass("Barbarian"), new Stats());
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

        private TextView charNameText;
        private TextView charLvlText;
        private TextView initiativeText;
        private TextView hpText;

        /*strScoreText = findViewById(R.id.);
        dexScoreText = findViewById(R.id.);
        conScoreText = findViewById(R.id.);
        intScoreText = findViewById(R.id.);
        wisScoreText = findViewById(R.id.);
        chaScoreText = findViewById(R.id.);
*/

        hpText = findViewById(R.id.character_sheet_hitpoints);
        charLvlText = findViewById(R.id.char_name_disp_text);
        hpText.setText(bobBarbarian.getCharStats().getHitpoints());
        charLvlText.setText(bobBarbarian.getCharLevel());
        strScoreText.setText(bobBarbarian.getCharStats().getStrength());
        dexScoreText.setText(bobBarbarian.getCharStats().getDexterity());
        conScoreText.setText(bobBarbarian.getCharStats().getConstitution());
        intScoreText.setText(bobBarbarian.getCharStats().getIntelligence());
        wisScoreText.setText(bobBarbarian.getCharStats().getWisdom());
        chaScoreText.setText(bobBarbarian.getCharStats().getCharisma());




    }
}
