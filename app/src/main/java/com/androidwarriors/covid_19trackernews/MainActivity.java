package com.androidwarriors.covid_19trackernews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidwarriors.covid_19trackernews.UI.OtherNewsAcitvity;
import com.google.android.material.navigation.NavigationView;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv_cases, tv_deaths, tv_recovered, tv_active , tv_Active_per_one_million, tv_affected_countries;

    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = findViewById(R.id.activity_main);
        t  = new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nav);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                int id = menuItem.getItemId();

                switch (id){

                    case R.id.homee:
                       startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       finish();
                        break;
                    case R.id.cnews:
                        startActivity(new Intent(getApplicationContext(),NewsActivity.class));
                        break;
                    case R.id.state:
                        startActivity(new Intent(getApplicationContext(), IndianStates.class));
                        break;

                    case R.id.news:
                        startActivity(new Intent(getApplicationContext(), OtherNewsAcitvity.class));
                        break;
                    case R.id.web:
                        Uri url = Uri.parse("https://www.linkedin.com/in/sujeet-thakur-0aa492180");
                        startActivity(new Intent(Intent.ACTION_VIEW, url));
                        break;

                    default:
                        return true;
                }

                return true;
            }
        });


        tv_cases = findViewById(R.id.tv_today_casess);
        tv_deaths = findViewById(R.id.tv_deaths);
        tv_recovered = findViewById(R.id.tv_recovered);
        tv_active = findViewById(R.id.tv_active);
        tv_Active_per_one_million = findViewById(R.id.tv_activepermil);
        tv_affected_countries = findViewById(R.id.tv_countries);


        scrollView = findViewById(R.id.scrollView);
        pieChart  = findViewById(R.id.piechart);
        simpleArcLoader = findViewById(R.id.loader);

        fetchData();


    }

    private void fetchData() {

        String url = "https://corona.lmao.ninja/v2/all";


           simpleArcLoader.start();






        StringRequest stringRequest  = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tv_cases.setText(jsonObject.getString("cases"));
                    tv_deaths.setText(jsonObject.getString("deaths"));
                    tv_recovered.setText(jsonObject.getString("recovered"));
                    tv_active.setText(jsonObject.getString("active"));
                    tv_Active_per_one_million.setText(jsonObject.getString("activePerOneMillion"));
                    tv_affected_countries.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tv_cases.getText().toString()), Color.parseColor("#FFC107")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tv_recovered.getText().toString()), Color.parseColor("#1BBD22")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tv_active.getText().toString()), Color.parseColor("#03A9F4")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tv_deaths.getText().toString()), Color.parseColor("#FF1100")));

                    pieChart.startAnimation();


                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {



                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);



                scrollView.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    public void nextActivity(View view) {

        startActivity(new Intent(getApplicationContext(),DetailsActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}