package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import AppModels.Race;
import AppModels.CharClass;
import AppModels.CharSheet;

import static com.android.volley.toolbox.Volley.newRequestQueue;


public class BeginCAC extends AppCompatActivity
{
    //For character name input
    private TextInputEditText charName;

    //subrace/subclass array lists for reference, not use
    private ArrayList subraceList;
    private ArrayList subclassList;

    private ArrayAdapter <String> subraceAdapter;
    private ArrayAdapter <String> subclassAdapter;

    //next page
    private ImageButton navigate_next_CAC;

    Race raceClass =  new Race();
    CharClass charClass = new CharClass();
    CharSheet characterSheet = new CharSheet();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_cac);

        //Connect variables to layout
        charName = findViewById(R.id.char_name);
        navigate_next_CAC = findViewById(R.id.NextCAC01);

        //?
        //mQueue = newRequestQueue(this);

        //Create Array List for race, subrace, class, subclass
        ArrayList <String> raceList = GetRaceList ();
        ArrayList <String> classList = GetClassList();

        ArrayList <String> subraceTempList = new ArrayList <>();
        subraceTempList.add("None");
        ArrayList <String> subclassTempList = new ArrayList <>();
        subclassTempList.add("None");

        //Create race adapter
        ArrayAdapter<String> raceAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, raceList);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Create class adapter
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, classList);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Create subrace adapter
        subraceAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, subraceTempList);
        subraceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Create subclass adapter
        subclassAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, subclassTempList);
        subclassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set Spinners
        final Spinner raceSpinner = (Spinner) findViewById(R.id.race_spinner);
        raceSpinner.setAdapter(raceAdapter);
        final Spinner classSpinner = (Spinner) findViewById(R.id.class_spinner);
        classSpinner.setAdapter(classAdapter);
        final Spinner subraceSpinner = (Spinner) findViewById(R.id.subrace_spinner);
        subraceSpinner.setAdapter(subraceAdapter);
        final Spinner subclassSpinner = (Spinner) findViewById(R.id.subclass_spinner);
        subclassSpinner.setAdapter(subclassAdapter);

        //Set on Click Listeners
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // Get select item
                int sid = raceSpinner.getSelectedItemPosition();
                if (sid > 0 && sid < subraceList.size())
                {
                    UpdateSubrace(sid);
                    Toast.makeText(getBaseContext(), "Subrace options updated.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // Get select item
                int sid = classSpinner.getSelectedItemPosition();
                if (sid > 0 && sid < subclassList.size())
                {
                    UpdateSubclass(sid);
                    Toast.makeText(getBaseContext(), "Subclass options updated.",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //Code for navigating to next page
        navigate_next_CAC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String race = raceSpinner.getSelectedItem().toString();
                String subrace = subraceSpinner.getSelectedItem().toString();
                String Class = classSpinner.getSelectedItem().toString();
                String subclass = subclassSpinner.getSelectedItem().toString();

                Boolean selectionsMade = CheckUserSelection(race, subrace, Class, subclass, charName.getText().toString());
                if (selectionsMade)
                {
                    //sending the information to the character sheet object
                    setCharacter(charName.getText().toString(), raceSpinner.getSelectedItem().toString(),
                            subraceSpinner.getSelectedItem().toString(), classSpinner.getSelectedItem().toString(),
                            subclassSpinner.getSelectedItem().toString());

                    Intent intent = new Intent(BeginCAC.this, AbilitiesCAC.class);

                    //send the character sheet to the next activity to add scores
                    intent.putExtra("characterSheet", characterSheet);

                    startActivity(intent);
                }
            }
        });

    }

    //Get a list of races and subraces from the local JSON file
    private ArrayList<String> GetRaceList ()
    {
        ArrayList <String> raceList = new ArrayList<>();
        ArrayList <String> newSubraceList = new ArrayList<>();

        //Add "Choose One to both lists
        raceList.add("Choose One");

        try
        {
            InputStream inStream = getAssets().open("races_5e.json");
            int size = inStream.available();
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i =0; i < jsonArray.length(); i++)
            {
                //Add race name to list of races
                JSONObject obj = jsonArray.getJSONObject(i);
                raceList.add(obj.getString("name"));

                //retrieve subraces for subrace list
                //If there are no subraces add "none"
                if (obj.isNull("subraces"))
                {
                    newSubraceList.add("None");
                }
                //else put all subraces into a string, separated by a space, then add to array
                else
                {
                    JSONArray subracesJsonArray = obj.getJSONArray("subraces");
                    String subraces = new String();
                    subraces += "Choose One  ";
                    for (int index = 0; index < subracesJsonArray.length(); index++) {
                        JSONObject subraceObj = subracesJsonArray.getJSONObject(index);
                        subraces += subraceObj.getString("name") + "  ";
                    }
                    newSubraceList.add(subraces);
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
        //save race and subrace arrays
        subraceList = newSubraceList;
        return raceList;
    }

    //Updates the subrace spinner
    private void UpdateSubrace(int chosenRace)
    {
        String subraceUpdatedList = subraceList.get(chosenRace - 1).toString();
        String[] subraceStringList = subraceUpdatedList.split("  ");
        ArrayList<String> newSubraceArrayList = new ArrayList<>();
        newSubraceArrayList.add(subraceStringList[0]);
        newSubraceArrayList.add("None");
        for (int i = 1; i < subraceStringList.length; i++) {
            newSubraceArrayList.add(subraceStringList[i]);
        }

        subraceAdapter.clear();
        subraceAdapter.addAll(newSubraceArrayList);
        subraceAdapter.notifyDataSetChanged();
    }

    //Get classes and subclasses from local JSON file
    private ArrayList<String> GetClassList ()
    {
        ArrayList <String> classList = new ArrayList<>();
        ArrayList <String> newSubclassList = new ArrayList<>();

        //Add "Choose One to both lists
        classList.add("Choose One");

        try
        {
            //Open the file
            InputStream inStream = getAssets().open("classes_5e.json");
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
                classList.add(obj.getString("name"));

                //retrieve subclasses for subclass list
                //If there are no subclasses add "none"
                if (obj.isNull("subclasses"))
                {
                    newSubclassList.add("None");
                }
                //else put all subclasses into a string, separated by a space, then add to array
                else
                {
                    JSONArray subclassesJsonArray = obj.getJSONArray("subclasses");
                    String subclasses = new String();
                    subclasses += "Choose One  ";
                    for (int index = 0; index < subclassesJsonArray.length(); index++) {
                        JSONObject subclassObj = subclassesJsonArray.getJSONObject(index);
                        subclasses += subclassObj.getString("name") + "  ";
                    }
                    newSubclassList.add(subclasses);
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
        //save race and subrace arrays
        subclassList = newSubclassList;
        return classList;
    }

    private void UpdateSubclass(int chosenClass)
    {
        String subclassUpdatedList = subclassList.get(chosenClass - 1).toString();
        String[] subclassStringList = subclassUpdatedList.split("  ");
        ArrayList<String> newSubclassArrayList = new ArrayList<>();
        newSubclassArrayList.add(subclassStringList[0]);
        newSubclassArrayList.add("None");
        for (int i = 1; i < subclassStringList.length; i++)
        {
            newSubclassArrayList.add(subclassStringList[i]);
        }

        subclassAdapter.clear();
        subclassAdapter.addAll(newSubclassArrayList);
        subclassAdapter.notifyDataSetChanged();
    }

    //Check that all options have been properly selected
    private Boolean CheckUserSelection(String race, String subrace, String Class, String subclass, String name)
    {
        Boolean acceptChoices = true;

        //Check users choices
        if (name.isEmpty())
        {
            acceptChoices = false;
            Toast.makeText(getBaseContext(), "You must choose a Name" ,
                    Toast.LENGTH_SHORT).show();
        }
        else if (race.equals("Choose One"))
        {
            acceptChoices = false;
            Toast.makeText(getBaseContext(), "You must choose a Race" ,
                    Toast.LENGTH_SHORT).show();
        }
        else if (subrace.equals("Choose One"))
        {
            acceptChoices = false;
            Toast.makeText(getBaseContext(), "You must choose a Subrace" ,
                    Toast.LENGTH_SHORT).show();
        }
        else if (Class.equals("Choose One"))
        {
            acceptChoices = false;
            Toast.makeText(getBaseContext(), "You must choose a Class" ,
                    Toast.LENGTH_SHORT).show();
        }
        else if (subclass.equals("Choose One"))
        {
            acceptChoices = false;
            Toast.makeText(getBaseContext(), "You must choose a Subclass" ,
                    Toast.LENGTH_SHORT).show();
        }

        return acceptChoices;
    }

    //Set the data models with the race and class info
    private void setCharacter(String name, String race, String subrace, String c, String subclass)
    {
        characterSheet.setCharacterName(name);
        raceClass.setRaceName(race);
        raceClass.setSubraceName(subrace);

        charClass.setClassName(c);

        characterSheet.setCharRace(raceClass);
        characterSheet.setCharClass(charClass);
        characterSheet.setCharLevel(1);
        characterSheet.setCharExp(0);

    }
}

