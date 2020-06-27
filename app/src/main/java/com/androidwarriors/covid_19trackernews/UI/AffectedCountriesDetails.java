package com.androidwarriors.covid_19trackernews.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.androidwarriors.covid_19trackernews.DetailsActivity;
import com.androidwarriors.covid_19trackernews.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class AffectedCountriesDetails extends AppCompatActivity {

    private int positionCountry;

    TextView tv_cases,tv_today_case, tv_deaths,tv_today_deaths, tv_recovered, tv_active, tv_stat;

    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries_details);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle( DetailsActivity.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pieChart  = findViewById(R.id.piechart);



        tv_stat = findViewById(R.id.stats);
        tv_cases = findViewById(R.id.tv_case);
        tv_today_case = findViewById(R.id.tv_today_cases);
        tv_deaths = findViewById(R.id.tv_deaths);
        tv_today_deaths = findViewById(R.id.tv_today_deaths);
        tv_recovered = findViewById(R.id.tv_recovered);
        tv_active = findViewById(R.id.tv_active);

        tv_stat.setText(DetailsActivity.countryModelList.get(positionCountry).getCountry() + "\' s"+ " Stats");
        tv_cases.setText(DetailsActivity.countryModelList.get(positionCountry).getCases());
        tv_today_case.setText(DetailsActivity.countryModelList.get(positionCountry).getToadyCases());
        tv_deaths.setText(DetailsActivity.countryModelList.get(positionCountry).getDeaths());
        tv_today_deaths.setText(DetailsActivity.countryModelList.get(positionCountry).getTodayDeaths());
        tv_recovered.setText(DetailsActivity.countryModelList.get(positionCountry).getRecovered());
        tv_active.setText(DetailsActivity.countryModelList.get(positionCountry).getActive());

        pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tv_cases.getText().toString()), Color.parseColor("#FFC107")));
        pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tv_recovered.getText().toString()), Color.parseColor("#1BBD22")));
        pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tv_active.getText().toString()), Color.parseColor("#03A9F4")));
        pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tv_deaths.getText().toString()), Color.parseColor("#FF1100")));

        pieChart.startAnimation();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}