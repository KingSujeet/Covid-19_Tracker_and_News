package com.androidwarriors.covid_19trackernews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidwarriors.covid_19trackernews.Adapter.CountryAdapter;
import com.androidwarriors.covid_19trackernews.Adapter.stateAdapter;
import com.androidwarriors.covid_19trackernews.Model.CountryModel;
import com.androidwarriors.covid_19trackernews.Model.stateModel;
import com.androidwarriors.covid_19trackernews.UI.AffectedCountriesDetails;
import com.androidwarriors.covid_19trackernews.UI.indianStateAffected;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IndianStates extends AppCompatActivity {

    EditText edt_search;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<stateModel> countryModelList;
    stateModel countryModel;
    stateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_states);

        getSupportActionBar().setTitle("Affected Indian States");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_search = findViewById(R.id.searchBar);
        listView = findViewById(R.id.listview);
        simpleArcLoader = findViewById(R.id.loader);

        countryModelList = new ArrayList<>();


        fetchData();

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getApplicationContext(), indianStateAffected.class).putExtra("position",position));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {

        String url = "https://api.covidindiatracker.com/state_data.json";


        simpleArcLoader.start();


        StringRequest stringRequest  = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
//                    JSONObject object  = new JSONObject(response);
//                    String getobject = object.getString("state");
                      JSONArray jsonArray = new JSONArray(response);


                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (jsonObject.getString("state")!=null) {
                            String countryName = jsonObject.getString("state");
                            String cases = jsonObject.getString("confirmed");
                            String deaths = jsonObject.getString("deaths");
                            String recovered = jsonObject.getString("recovered");
                            String active = jsonObject.getString("active");


                            countryModel = new stateModel(active, recovered, deaths, cases, countryName);
                            countryModelList.add(countryModel);
                        }
                    }

                    adapter = new stateAdapter(getApplicationContext(),countryModelList);
                    listView.setAdapter(adapter);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);




                } catch (JSONException e) {

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);


                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }




}