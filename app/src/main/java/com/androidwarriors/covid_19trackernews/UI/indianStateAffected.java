package com.androidwarriors.covid_19trackernews.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.androidwarriors.covid_19trackernews.DetailsActivity;
import com.androidwarriors.covid_19trackernews.IndianStates;
import com.androidwarriors.covid_19trackernews.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class indianStateAffected extends AppCompatActivity {

    TextView tv_cases,tv_today_case, tv_deaths,tv_today_deaths, tv_recovered, tv_active, tv_stat;

    PieChart pieChart;
    private int positionCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_state_affected);


        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle( IndianStates.countryModelList.get(positionCountry).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pieChart  = findViewById(R.id.piechart);



        tv_stat = findViewById(R.id.stats);
        tv_cases = findViewById(R.id.tv_case);
        tv_deaths = findViewById(R.id.tv_deaths);
        tv_recovered = findViewById(R.id.tv_recovered);
        tv_active = findViewById(R.id.tv_active);

        tv_stat.setText(IndianStates.countryModelList.get(positionCountry).getName() + "\' s"+ " Stats");
        tv_cases.setText(IndianStates.countryModelList.get(positionCountry).getTotal());
        tv_deaths.setText(IndianStates.countryModelList.get(positionCountry).getDeath());
        tv_recovered.setText(IndianStates.countryModelList.get(positionCountry).getCured());
        tv_active.setText(IndianStates.countryModelList.get(positionCountry).getActive());

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