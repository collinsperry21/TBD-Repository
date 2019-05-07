package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Pop_Add_Equipment extends AppCompatActivity {

    private Button saveButton;
    private TextView WeaponName;
    private TextView WeaponDamage;
    private TextView WeaponWeight;
    private TextView WeaponProperties;

    private String[] addWeaponStrings = {"", "", "", ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop__add__equipment);

        DisplayMetrics dispMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dispMetrics);

        int width = dispMetrics.widthPixels;
        int height = dispMetrics.heightPixels;

        getWindow().setLayout((int)(width*0.9), (int)(height*0.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 90;

        getWindow().setAttributes(params);

        //Connect variables to layout
        saveButton = findViewById(R.id.SaveEquipment_button);
        WeaponName = findViewById(R.id.addWeaponName_text);
        WeaponDamage = findViewById(R.id.addWeaponDamage_text);
        WeaponWeight = findViewById(R.id.addWeaponWeight_text);
        WeaponProperties = findViewById(R.id.addWeaponProperties_text);



        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(WeaponName.getText().toString().isEmpty())
                    addWeaponStrings[0] = "";
                else
                    addWeaponStrings[0] = (WeaponName.getText().toString());
                if(WeaponDamage.getText().toString().isEmpty())
                    addWeaponStrings[1] = "";
                else
                    addWeaponStrings[1] = WeaponDamage.getText().toString();
                if(WeaponWeight.getText().toString().isEmpty())
                    addWeaponStrings[2] = "";
                else
                    addWeaponStrings[2] = WeaponWeight.getText().toString();
                if(WeaponProperties.getText().toString().isEmpty())
                    addWeaponStrings[3] = "";
                else
                    addWeaponStrings[3] = WeaponProperties.getText().toString();

                Intent intent = new Intent(getBaseContext(), CharacterSheetPage4.class);
                intent.putExtra("result", addWeaponStrings);
                setResult(1606, intent);

            }
        });
        Intent intent = new Intent(getBaseContext(), CharacterSheetPage4.class);
        intent.putExtra("result", addWeaponStrings);
        setResult(1607, intent);
    }

}
