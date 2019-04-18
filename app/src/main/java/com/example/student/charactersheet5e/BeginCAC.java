package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Button navigate_next_CAC;
    
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_cac);



        navigate_next_CAC = findViewById(R.id.NextCAC01);



        //Code for navigating to next page
        navigate_next_CAC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(BeginCAC.this, AbilitiesCAC.class);
                startActivity(intent);
            }
        });

        mQueue = newRequestQueue( this);
        jsonParse();
    }

    //Will move to separate class
    private void jsonParse() {
        String url = "http://www.dnd5eapi.co/api/races";
        String url2 = "http://dnd5eapi.co/api/subraces";
        String url3 = "http://dnd5eapi.co/api/classes";
        String url4 = "http://dnd5eapi.co/api/subclasses";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<String> raceSpinnerArrayList = new ArrayList<String>();

                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject races = jsonArray.getJSONObject(i);
                                String firstname = races.getString("name");
                                String urlz = races.getString("url");

                                raceSpinnerArrayList.add(firstname);

                            }

                            final Spinner raceSpinner = (Spinner) findViewById(R.id.race_spinner);

                            ArrayAdapter<String> adapter =
                                    new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, raceSpinnerArrayList);
                            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

                            raceSpinner.setAdapter(adapter);

                            raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                            {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view,
                                                        int position, long id)
                                {
                                    // Get select item
                                    String sid = raceSpinner.getSelectedItem().toString();
                                    Toast.makeText(getBaseContext(), "You have selected Race : " + sid,
                                            Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> parent)
                                {
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
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ArrayList<String> subraceSpinnerArrayList = new ArrayList<String>();
                            subraceSpinnerArrayList.add("None");

                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject classes = jsonArray.getJSONObject(i);
                                String firstname = classes.getString("name");
                                String urlz = classes.getString("url");

                                subraceSpinnerArrayList.add(firstname);

                            }
                            final Spinner subraceSpinner = (Spinner) findViewById(R.id.subrace_spinner);

                            ArrayAdapter<String> adapter =
                                    new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, subraceSpinnerArrayList);
                            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

                            subraceSpinner.setAdapter(adapter);
                            subraceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                            {
                                @Override
                                 public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
                                    {
                                    // Get select item
                                        String sid = subraceSpinner.getSelectedItem().toString();
                                        Toast.makeText(getBaseContext(), "You have selected Subrace : " + sid,
                                        Toast.LENGTH_SHORT).show();
                                        }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

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
        JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, url3, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<String> classSpinnerArrayList = new ArrayList<String>();
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject classes = jsonArray.getJSONObject(i);
                                String firstname = classes.getString("name");
                                String urlz = classes.getString("url");

                                classSpinnerArrayList.add(firstname);

                            }
                            final Spinner classSpinner = (Spinner) findViewById(R.id.class_spinner);

                            ArrayAdapter<String> adapter =
                                    new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, classSpinnerArrayList);
                            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

                            classSpinner.setAdapter(adapter);

                            classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                            {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
                            {
                             // Get select item
                            String sid = classSpinner.getSelectedItem().toString();
                            Toast.makeText(getBaseContext(), "You have selected Class : " + sid,
                            Toast.LENGTH_SHORT).show();
                             }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

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
        mQueue.add(request2);
        mQueue.add(request3);

    }
}
