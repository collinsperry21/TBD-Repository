package com.example.student.charactersheet5e;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import AppModels.CharSheet;
import AppModels.Proficiencies;

public class Pop_Prof extends Activity
{

    private ArrayList<ProficienciesRecItem> defaultProficiencies = new ArrayList<>();
    private CharSheet charSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_prof);

        //Renew character sheet
        charSheet = (CharSheet) (getIntent().getSerializableExtra("characterSheet"));

        DisplayMetrics dispMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dispMetrics);

        int width = dispMetrics.widthPixels;
        int height = dispMetrics.heightPixels;

        getWindow().setLayout((int)(width*0.9), (int)(height*0.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
        setProfRecView();
        populateRecView();

    }

    private void populateRecView() {
        //Parse JSON for class related proficiency lists
        GetListCount(charSheet.getCharClass().getClassName());
        for(int i = 0; i <  charSheet.getmProficiencies().size(); i++)
        {
            defaultProficiencies.add(new ProficienciesRecItem(charSheet.getmProficiencies().get(i).getProf(),
                    GetProfDescription(charSheet.getmProficiencies().get(i).getProf()) ,
                    getIcon(charSheet.getmProficiencies().get(i).getProf())));
        }

    }

    private void setProfRecView() {
        //Connect Recycler view variables
        RecyclerView defaultRecView = findViewById(R.id.char_prof);
        defaultRecView.setHasFixedSize(true);
        RecyclerView.Adapter RecViewAdapter = new ProficienciesRecAdapter(defaultProficiencies);
        RecyclerView.LayoutManager RecViewManager = new LinearLayoutManager(getApplicationContext());
        defaultRecView.setLayoutManager(RecViewManager);
        defaultRecView.setAdapter(RecViewAdapter);

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
                    //Get proficiencies defaults
                    JSONArray defaultsJsonArray = obj.getJSONArray("proficiencies");
                    for (int index = 0; index < defaultsJsonArray.length(); index++) {
                        JSONObject defaultObj = defaultsJsonArray.getJSONObject(index);
                        String profName = defaultObj.getString("name");
                        defaultProficiencies.add(new ProficienciesRecItem(profName, GetProfDescription(profName), getIcon(profName)));
                    }
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
        }
        else if (profName.toLowerCase().contains("weapon")||profName.toLowerCase().contains("crossbow")) {
            return R.drawable.ic_weapon;
        }
        else if (profName.toLowerCase().contains("shield")) {
            return R.drawable.ic_shield;
        }
        else if (profName.toLowerCase().contains("music")) {
            return R.drawable.ic_bard;
        }
        else if (profName.toLowerCase().contains("skill")) {
            return R.drawable.ic_scroll;
        }

        else {
            String cat = getProfCat(profName);
            if (cat.toLowerCase().contains("armor")) {
                return R.drawable.ic_armor;
            }
            else if (cat.toLowerCase().contains("weapon")) {
                return R.drawable.ic_weapon;
            }
            else if (cat.toLowerCase().contains("shield")) {
                return R.drawable.ic_shield;
            }
            else if (cat.toLowerCase().contains("music")) {
                return R.drawable.ic_bard;
            }
            else if (cat.toLowerCase().contains("tool")&&charSheet.getCharClass().getClassName().contains("Bard")) {
                return R.drawable.ic_bard;
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

        if (profName.toLowerCase().contains("skill")) {
            profName = profName.replace("Skill: ", "");
            try {
                //Open file, read in, close file
                InputStream inStream = getAssets().open("skills_5e.json");
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
                        description = obj.getString("desc");

                        description = description.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","");
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
        else {
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
}
