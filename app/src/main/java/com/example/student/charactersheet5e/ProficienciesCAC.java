package com.example.student.charactersheet5e;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import AppModels.Proficiencies;

public class ProficienciesCAC extends AppCompatActivity {
    //variables updated by JSON parse
    private int numOfLists = 0; //Code assumes the max is 3
    private ArrayList<Integer> numOfOptionsAvailable = new ArrayList<>();
    private ArrayList<Integer> numOfChoicesAllowed = new ArrayList<>();
    private ArrayList<String> optionsForLists = new ArrayList<>();
    private ArrayList<String> listTypes = new ArrayList<>();
    //will hold list of default proficiencies for Recycler View
    private ArrayList<ProficienciesRecItem> defaultProficiencies = new ArrayList<>();

    //variables used for layout
    private ArrayList<Integer> userChoices = new ArrayList<>();
    //private TextView currentTextView = new TextView(ProficienciesCAC.this);

    //character sheet
    private CharSheet charSheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiencies_cac);

        //Renew character sheet
        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        //Set action bar
        getSupportActionBar().setTitle("Proficiencies: " + charSheet.getCharClass().getClassName());

        //Connect dynamic layout variables to layout
        Button firstButton = new Button(ProficienciesCAC.this);
        Button secondButton = new Button(ProficienciesCAC.this);
        TextView firstTextView = findViewById(R.id.proficiencies_output_text01);
        TextView secondTextView = findViewById(R.id.proficiencies_output_text02);
        TextView firstDescText = findViewById(R.id.ListOne_Text);
        TextView secondDescText = findViewById(R.id.ListTwo_Text);

        //Connect Recycler view variables
        RecyclerView defaultRecView = findViewById(R.id.defaultProficiencies_Recycler);
        defaultRecView.setHasFixedSize(false);
        RecyclerView.Adapter RecViewAdapter = new ProficienciesRecAdapter(defaultProficiencies);
        RecyclerView.LayoutManager RecViewManager = new LinearLayoutManager(ProficienciesCAC.this);
        defaultRecView.setLayoutManager(RecViewManager);
        defaultRecView.setAdapter(RecViewAdapter);

        //Connect static layout variables to layout
        TextView proficiencyBonusText = findViewById(R.id.proficiencyBonus_Text);
        TextView defaultText = findViewById(R.id.defaultList_text);
        LinearLayout profLayout = findViewById(R.id.proficiencies_layout);
        ImageButton navigate_to_next = findViewById(R.id.nextCAC);

        //display proficiency bonus assuming lvl 1 set proficiency Bonus
        proficiencyBonusText.setText("+2");
        charSheet.getCharStats().setProfBonus(2);

        //Parse JSON for class related proficiency lists
        GetListCount(charSheet.getCharClass().getClassName());

        //Set 'Other' text to be underlined
        defaultText.setPaintFlags(firstDescText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //Create buttons and dialogs
        Button currentButton = new Button(ProficienciesCAC.this);
        for (int i = 0; i < numOfLists; i++) {
            switch (i) {
                case 0:
                    currentButton = firstButton;
                    firstDescText.setText(listTypes.get(i));
                    firstDescText.setPaintFlags(firstDescText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    break;
                case 1:
                    currentButton = secondButton;
                    secondDescText.setText(listTypes.get(i));
                    secondDescText.setPaintFlags(firstDescText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    break;
            }
            //Pull necessary variables from arrays
            int maxChoices = numOfChoicesAllowed.get(i);
            int listLength = numOfOptionsAvailable.get(i);
            String[] optionsList = optionsForLists.get(i).split("  ");
            int currentListNum = i;

            currentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Clear Text View and user choices each time button is clicked
                    switch (currentListNum) {
                        case 0:
                            firstTextView.setText("");
                            break;
                        case 1:
                            secondTextView.setText("");
                            break;
                    }
                    userChoices.clear();

                    //Set list defaults
                    AlertDialog.Builder listBuilder = new AlertDialog.Builder(ProficienciesCAC.this);
                    listBuilder.setTitle("Choose " + maxChoices);

                    //Create boolean array to hold if chosen or not
                    boolean[] checkedItems = new boolean[listLength];
                    listBuilder.setMultiChoiceItems(optionsList, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        int count = 0;

                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            //Count the number of selections and prevent user from choosing too many
                            count += isChecked ? 1 : -1;

                            if (count > maxChoices) {
                                checkedItems[which] = false;
                                count--;
                                ((AlertDialog) dialog).getListView().setItemChecked(which, false);
                            } else if (isChecked) {
                                //Update array holding user choices
                                if (!userChoices.contains(which)) {
                                    userChoices.add(which);
                                }
                            } else {
                                //Update array holding user choices
                                userChoices.remove(which);
                            }
                        }
                    });

                    listBuilder.setCancelable(false);
                    listBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String item = "";
                            for (int i = 0; i < userChoices.size(); i++) {
                                item += "-";
                                item += optionsList[userChoices.get(i)];
                                Proficiencies newProf = new Proficiencies();
                                newProf.setProf(optionsList[userChoices.get(i)]);
                                charSheet.addToProfList(newProf);
                                if (i != userChoices.size() - 1) {
                                    item += ",\n";
                                }
                            }
                            switch (currentListNum) {
                                case 0:
                                    firstTextView.setText(item);
                                    break;
                                case 1:
                                    secondTextView.setText(item);
                                    break;
                            }
                        }

                    });





                    AlertDialog dialog = listBuilder.create();
                    dialog.show();

                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 210);
            currentButton.setLayoutParams(params);
            currentButton.setAllCaps(false);
            currentButton.setBackgroundResource(R.drawable.button_background);
            profLayout.addView(currentButton);
            String buttonText = "Choose " + maxChoices;
            currentButton.setText(buttonText);
        }

        //Send user to descriptions page
        navigate_to_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProficienciesCAC.this, DescCAC.class);


                //send the character sheet to the next activity
                intent.putExtra("characterSheet", charSheet);

                startActivity(intent);
            }
        });
    }

    private void GetListCount(String className) {
        try {
            //Open file, read in, close file
            InputStream inStream = getAssets().open("classes_5e.json");
            int size = inStream.available();
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            //Step through JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                //Find class
                if (obj.getString("name").equals(className)) {
                    //Get proficiencies choices
                    JSONArray profChoicesJsonArray = obj.getJSONArray("proficiency_choices");
                    numOfLists = profChoicesJsonArray.length();
                    for (int index = 0; index < numOfLists; index++) {
                        JSONObject choicesObj = profChoicesJsonArray.getJSONObject(index);
                        numOfChoicesAllowed.add(choicesObj.getInt("choose"));
                        listTypes.add(choicesObj.getString("type"));
                        String profList = new String();
                        JSONArray profArray = choicesObj.getJSONArray("from");
                        numOfOptionsAvailable.add(profArray.length());
                        for (int idx = 0; idx < profArray.length(); idx++) {
                            JSONObject profObject = profArray.getJSONObject(idx);
                            profList += profObject.getString("name");
                            profList += "  ";

                        }
                        optionsForLists.add(profList);
                    }

                    //Get proficiencies defaults
                    JSONArray defaultsJsonArray = obj.getJSONArray("proficiencies");
                    for (int index = 0; index < defaultsJsonArray.length(); index++) {
                        JSONObject defaultObj = defaultsJsonArray.getJSONObject(index);
                        String profName = defaultObj.getString("name");
                        defaultProficiencies.add(new ProficienciesRecItem(profName, GetProfDescription(profName), getIcon(profName)));
                    }
                    return;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int getIcon(String profName) {


        if (profName.toLowerCase().contains("armor")) {
            return R.drawable.ic_armor;
        } else if (profName.toLowerCase().contains("weapon")||profName.toLowerCase().contains("crossbow")) {
            return R.drawable.ic_weapon;
        } else if (profName.toLowerCase().contains("shield")) {
            return R.drawable.ic_shield;
        }
        else if (profName.toLowerCase().contains("music")) {
            return R.drawable.ic_bard;
        }
        else {
            String cat = getProfCat(profName);
            if (cat.toLowerCase().contains("armor")) {
                return R.drawable.ic_armor;
            } else if (cat.toLowerCase().contains("weapon")) {
                return R.drawable.ic_weapon;
            } else if (cat.toLowerCase().contains("shield")) {
                return R.drawable.ic_shield;
            }
            else
            {
                return R.drawable.ic_5e_dnd_logo;
            }


        }
    }

    private String getProfCat(String profName) {

        try {
            //Open file, read in, close file
            InputStream inStream = getAssets().open("equipment_categories.json");
            int size = inStream.available();
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            //Step through JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                //Find equipment category
                JSONArray descriptionList = obj.getJSONArray("equipment");
                for(int j = 0; j < descriptionList.length(); j++) {
                    JSONObject equipObj = descriptionList.getJSONObject(j);
                    if (profName.toLowerCase().contains(equipObj.getString("name").toLowerCase())) {

                        String cat =obj.getString("name");

                        return cat;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    private String GetProfDescription(String profName) {
        String description = "";
        try {
            //Open file, read in, close file
            InputStream inStream = getAssets().open("equipment_categories.json");
            int size = inStream.available();
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            //Step through JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                //Find equipment category
                if (obj.getString("name").equalsIgnoreCase(profName)) {
                    //Get proficiencies choices
                    JSONArray descriptionList = obj.getJSONArray("equipment");
                    for (int index = 0; index < 7 && index < descriptionList.length(); index++) {
                        JSONObject descriptionObj = descriptionList.getJSONObject(index);
                        description += descriptionObj.getString("name");
                        if (index < 6 && index < descriptionList.length() - 1) {
                            description += ", ";
                        }
                    }
                    if (descriptionList.length() > 7)
                        description += ", etc...";
                    return description;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return description;
    }
}
