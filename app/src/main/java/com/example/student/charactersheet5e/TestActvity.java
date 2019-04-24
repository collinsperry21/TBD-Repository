package com.example.student.charactersheet5e;
import android.support.v7.app.AppCompatActivity;

import AppModels.Class;
import AppModels.Race;
import AppModels.Stats;
import AppModels.charSheet;

public class TestActvity extends MainActivity {

    charSheet bobBarbarian = new charSheet("Bob", new Race("Dwarf"), new Class("Barbarian"),new Stats() );

}
