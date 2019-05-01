package com.example.student.charactersheet5e;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import AppModels.CharSheet;

public class ProficienciesCAC extends AppCompatActivity
{
    //variables updated by JSON parse
    private int numOfLists = 0; //Code assumes the max is 3
    private ArrayList<Integer> numOfOptionsAvailable = new ArrayList<>();
    private ArrayList<Integer> numOfChoicesAllowed = new ArrayList<>();
    private ArrayList<String> optionsForLists = new ArrayList<>();

    //variables used for layout
    private ArrayList<Integer> userChoices = new ArrayList<>();
    //private TextView currentTextView = new TextView(ProficienciesCAC.this);

    //character sheet
    private CharSheet charSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiencies_cac);

        //Renew character sheet
        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Set action bar
        getSupportActionBar().setTitle("Proficiencies: " + charSheet.getCharClass().getClassName());

        //Connect dynamic layout variables to layout
        Button firstButton = new Button(ProficienciesCAC.this);
        Button secondButton = new Button(ProficienciesCAC.this);
        Button thirdButton = new Button (ProficienciesCAC.this);
        TextView firstTextView = findViewById(R.id.proficiencies_output_text01);
        TextView secondTextView = findViewById(R.id.proficiencies_output_text02);
        TextView thirdTextView = findViewById(R.id.proficiencies_output_text03);

        //Connect static layout variables to layout
        TextView proficiencyBonusText = findViewById(R.id.proficiencyBonus_Text);
        LinearLayout profLayout = findViewById(R.id.proficiencies_layout);
        ImageButton navigate_to_next = findViewById(R.id.nextCAC);

        //display proficiency bonus assuming lvl 1 set proficiency Bonus
        proficiencyBonusText.setText("+2");
        charSheet.getCharStats().setProfBonus(2);

        //Parse JSON for class related proficiency lists
        GetListCount(charSheet.getCharClass().getClassName());

        //Create buttons and dialogs
        Button currentButton = new Button(ProficienciesCAC.this);
        for (int i = 0; i < numOfLists; i++)
        {
            switch (i)
            {
                case 0:
                    currentButton = firstButton;
                    break;
                case 1:
                    currentButton = secondButton;
                    break;
                case 2:
                    currentButton = thirdButton;
                    break;
            }
            //Pull necessary variables from arrays
            int maxChoices = numOfChoicesAllowed.get(i);
            int listLength = numOfOptionsAvailable.get(i);
            String[] optionsList = optionsForLists.get(i).split("  ");
            int currentListNum = i;

            currentButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Clear Text View and user choices each time button is clicked
                    switch (currentListNum)
                    {
                        case 0:
                            firstTextView.setText("");
                            break;
                        case 1:
                            secondTextView.setText("");
                            break;
                        case 2:
                            thirdTextView.setText("");
                            break;
                    }
                    userChoices.clear();

                    //Set list defaults
                    AlertDialog.Builder listBuilder = new AlertDialog.Builder(ProficienciesCAC.this);
                    listBuilder.setTitle("Choose " + maxChoices);

                    //Create boolean array to hold if chosen or not
                    boolean[] checkedItems = new boolean[listLength];
                    listBuilder.setMultiChoiceItems(optionsList, checkedItems, new DialogInterface.OnMultiChoiceClickListener()
                    {
                        int count = 0;

                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked)
                        {

                            //Count the number of selections and prevent user from choosing too many
                            count += isChecked ? 1 : -1;

                            if (count > maxChoices)
                            {
                                checkedItems[which] = false;
                                count--;
                                ((AlertDialog)dialog).getListView().setItemChecked(which, false);
                            }
                            else if (isChecked)
                            {
                                //Update array holding user choices
                                if (!userChoices.contains(which))
                                {
                                    userChoices.add(which);
                                }
                            }
                            else
                            {
                                //Update array holding user choices
                                userChoices.remove(which);
                            }
                        }
                    });
                    listBuilder.setCancelable(false);
                    listBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            String item = "";
                            for (int i = 0; i < userChoices.size(); i++)
                            {
                                item += optionsList[userChoices.get(i)];
                                if (i != userChoices.size()- 1)
                                {
                                    item += ",\n";
                                }
                            }
                            switch (currentListNum)
                            {
                                case 0:
                                    firstTextView.setText(item);
                                    break;
                                case 1:
                                    secondTextView.setText(item);
                                    break;
                                case 2:
                                    thirdTextView.setText(item);
                                    break;
                            }
                        }
                    });

                    //Todo: save user selections

                    AlertDialog dialog = listBuilder.create();
                    dialog.show();
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT );
            params.setMargins(0, 10, 0, 140);
            currentButton.setLayoutParams(params);
            currentButton.setBackgroundResource(R.drawable.button_background);
            profLayout.addView(currentButton);
            String buttonText = "Choose " + maxChoices;
            currentButton.setText(buttonText);
        }

        //Send user to descriptions page
        navigate_to_next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ProficienciesCAC.this, DescCAC.class);

                //send the character sheet to the next activity
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
                    numOfLists = profChoicesJsonArray.length();
                    for (int index =0; index < numOfLists; index++)
                    {
                        JSONObject choicesObj = profChoicesJsonArray.getJSONObject(index);
                        numOfChoicesAllowed.add(choicesObj.getInt("choose"));
                        String profList = new String();
                        JSONArray profArray = choicesObj.getJSONArray("from");
                        numOfOptionsAvailable.add(profArray.length());
                        for (int idx = 0; idx < profArray.length(); idx++)
                        {
                            JSONObject profObject = profArray.getJSONObject(idx);
                            profList += profObject.getString("name");
                            profList += "  ";

                        }
                        optionsForLists.add(profList);
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
}
