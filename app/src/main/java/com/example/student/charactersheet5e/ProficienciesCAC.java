package com.example.student.charactersheet5e;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import AppModels.CharSheet;

public class ProficienciesCAC extends AppCompatActivity
{

    private int listCount = 0; //Code assumes the max is 3
    private ArrayList<Integer> optionsCount = new ArrayList<>(); //Holds the number of options in each list
    private List<Integer> numChoices = new ArrayList<>(); // Array containing the number of choices the user gets to choose from each list
    private ArrayList<Integer> userItems = new ArrayList<>();
    private ArrayList <String> choices = new ArrayList<>(); //An array of strings, as long as listCount. Each entry contains a list of options
    private ArrayList <String> chosenProfs = new ArrayList<>();
    private CharSheet charSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiencies_cac);

        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Set action bar
        getSupportActionBar().setTitle("Proficiencies: " + charSheet.getCharClass().getClassName());

        Button firstButton = new Button(ProficienciesCAC.this);
        Button secondButton = new Button(ProficienciesCAC.this);
        Button thirdButton = new Button (ProficienciesCAC.this);
        TextView firstTextView = findViewById(R.id.proficiencies_output_text01);
        TextView secondTextView = findViewById(R.id.proficiencies_output_text02);
        TextView thirdTextView = findViewById(R.id.proficiencies_output_text03);


        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Connect variables to layout
        TextView proficiencyBonusText = findViewById(R.id.proficiencyBonus_Text);
        TextView proficiencyDescText = findViewById(R.id.proficiencies_desc_text);
        LinearLayout profLayout = findViewById(R.id.proficiencies_layout);



        navigate_to_next = findViewById(R.id.nextCAC);

        //Assuming lvl 1 set proficiency Bonus
        proficiencyBonusText.setText("+2");
        charSheet.getCharStats().setProfBonus(2);

        GetListCount(charSheet.getCharClass().getClassName());

        Button currentButton = new Button(ProficienciesCAC.this);
        for (int i = 0; i < listCount; i++)
        {
            switch (i)
            {
                case 0:
                    currentButton = firstButton;
                    currentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProficienciesCAC.this);
                            mBuilder.setTitle("Choose " + numChoices.get(0));
                            boolean[] checkedItems = new boolean[optionsCount.get(0)];
                            String[] choicesList = BreakDownOptions(choices.get(0));
                            mBuilder.setMultiChoiceItems(choicesList, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    if (isChecked)
                                    {
                                        if (!userItems.contains(which)){
                                            userItems.add(which);
                                        }
                                    }else{
                                        userItems.remove(which);
                                    }
                                }
                            });
                            mBuilder.setCancelable(false);
                            mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String item = "";
                                    //todo: fix dis shit
                                    for (int i = 0; i < userItems.size(); i++){
                                        item += choicesList[userItems.get(i)];
                                        if (i != userItems.size()- 1)
                                        {
                                            item += ",\n";
                                        }
                                    }
                                    firstTextView.setText(item);
                                }
                            });
                            mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                }
                            });
                            mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for(int i = 0; i < checkedItems.length; i++)
                                    {
                                        checkedItems[i] = false;
                                        userItems.clear();
                                        firstTextView.setText("");
                                    }
                                }
                            });

                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();
                        }
                    });
                    break;
                case 1:
                    currentButton = secondButton;
                    break;
                case 2:
                    currentButton = thirdButton;
                    break;
            }
            currentButton.setLayoutParams(new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            profLayout.addView(currentButton);
            String buttonText = "Choose " + numChoices.get(i);
            currentButton.setText(buttonText);

        }


        //

        navigate_to_next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ProficienciesCAC.this, DescCAC.class);

                //send the character sheet to the next activity to add scores
                intent.putExtra("characterSheet", charSheet);

                startActivity(intent);
            }
        });
    }

    private void GetListCount(String className)
    {
        try
        {
            //Open file, read in, close file
            InputStream inStream = getAssets().open("classes_5e.json");
            int size = inStream.available();
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            //Step through JSON array
            for (int i =0; i < jsonArray.length(); i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                //Find class
                if (obj.getString("name").equals(className))
                {
                    JSONArray profChoicesJsonArray = obj.getJSONArray("proficiency_choices");
                    listCount = profChoicesJsonArray.length();
                    for (int index =0; index < listCount; index++)
                    {
                        JSONObject choicesObj = profChoicesJsonArray.getJSONObject(index);
                        numChoices.add(choicesObj.getInt("choose"));
                        String profList = new String();
                        JSONArray profArray = choicesObj.getJSONArray("from");
                        optionsCount.add(profArray.length());
                        for (int idx = 0; idx < profArray.length(); idx++)
                        {
                            JSONObject profObject = profArray.getJSONObject(idx);
                            profList += profObject.getString("name");
                            profList += "  ";

                        }
                        choices.add(profList);
                    }
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
    }

    private String[] BreakDownOptions(String optionsString)
    {
        String[] optionsArray = optionsString.split("  ");
        return optionsArray;
    }
}
