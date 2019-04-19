package com.example.student.charactersheet5e;

import android.content.ContentQueryMap;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.toolbox.Volley.newRequestQueue;


public class BeginCAC extends AppCompatActivity {
    //?
    private RequestQueue mQueue;
    //next page
    private Button navigate_next_CAC;
    private TextInputEditText charName;

    WriteObject obj = new WriteObject(this);
    Character character = new Character();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_cac);

        navigate_next_CAC = findViewById(R.id.NextCAC01);

        charName = findViewById(R.id.char_name);

        mQueue = newRequestQueue(this);

        //Create Race Spinner
        final Spinner raceSpinner = (Spinner) findViewById(R.id.race_spinner);
        jsonParse("http://www.dnd5eapi.co/api/races", "name", raceSpinner);
        //Create subRaceSpinner
        final Spinner subraceSpinner = (Spinner) findViewById(R.id.subrace_spinner);
        jsonParse("http://dnd5eapi.co/api/subraces", "name", subraceSpinner);
        //Create Class Spinner
        final Spinner classSpinner = (Spinner) findViewById(R.id.class_spinner);
        jsonParse("http://dnd5eapi.co/api/classes", "name", classSpinner);


        //Code for navigating to next page
        navigate_next_CAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sending the information to the character object
                setCharacter(charName.getText().toString(), raceSpinner.getSelectedItem().toString(), subraceSpinner.getSelectedItem().toString(), classSpinner.getSelectedItem().toString());
                obj.serializeCharacter(character);


                Intent intent = new Intent(BeginCAC.this, AbilitiesCAC.class);
                startActivity(intent);
            }
        });

    }

    private void setCharacter(String name, String race, String subrace, String c)
    {

        character.setName(name);
        character.setRace(race);
        character.setSubrace(subrace);
        character.setCharClass(c);
    }

    private void jsonParse(String url, final String searchTerm, final Spinner currentSpinner)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Create string array to hold list, add "Choose One as the first option"
                            ArrayList<String> SpinnerArrayList = new ArrayList<String>();
                            SpinnerArrayList.add("Choose One");

                            //Step through JSON object and add the rest of the list
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String listEntry = jsonObject.getString(searchTerm);
                                String urlz = jsonObject.getString("url");
                                SpinnerArrayList.add(listEntry);
                            }

                            //Assign array of strings to adapter and assign Android layout style
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_dropdown_item, SpinnerArrayList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            final Spinner finalSpinner = currentSpinner;
                            //Assign adapter to spinner
                            finalSpinner.setAdapter(adapter);

                            //Add Toast popup to page
                            finalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view,
                                                           int position, long id) {
                                    // Get select item
                                    String sid = finalSpinner.getSelectedItem().toString();
                                    Toast.makeText(getBaseContext(), "You have selected: " + sid,
                                            Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // TODO Auto-generated method stub
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}