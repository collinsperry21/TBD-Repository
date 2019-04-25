package com.example.student.charactersheet5e;
import android.support.v7.app.AppCompatActivity;

import AppModels.CharClass;
import AppModels.CharSheet;
import AppModels.Race;
import AppModels.Stats;

public class TestActvity extends MainActivity {

    CharSheet bobBarbarian = new CharSheet("Bob", new Race("Dwarf"), new CharClass("Barbarian"),new Stats() );

}
