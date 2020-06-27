package com.androidwarriors.covid_19trackernews.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidwarriors.covid_19trackernews.Adapter.NewsAdapter;
import com.androidwarriors.covid_19trackernews.Model.NewsModel;
import com.androidwarriors.covid_19trackernews.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OtherNewsAcitvity extends AppCompatActivity {

    ListView listView;
    SimpleArcLoader simpleArcLoader;
    String content;

    public static List<NewsModel> newsModelList;
    NewsModel newsModel;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_news_acitvity);

        getSupportActionBar().setTitle("Other News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        listView = findViewById(R.id.newsListView);
        simpleArcLoader = findViewById(R.id.loader2);

        newsModelList = new ArrayList<>();

        fetchData();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {

        String url = "http://newsapi.org/v2/top-headlines?country=in&apiKey=a7e8ae26e3514dc491a436ed660680b7";

        simpleArcLoader.start();


        StringRequest stringRequest  = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object  = new JSONObject(response);
                    String getobject = object.getString("articles");
                    JSONArray jsonArray = new JSONArray(getobject);


                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String title = jsonObject.getString("title");
                        if (jsonObject.getString("content").equals("null")){

                             content  = "";
                        }else {
                             content = jsonObject.getString("content");
                        }
                        String url = jsonObject.getString("url");
                        String flag = jsonObject.getString("urlToImage");


                        newsModel = new NewsModel(title,content,url,flag);
                        newsModelList.add(newsModel);

                    }

                    adapter = new NewsAdapter(getApplicationContext(),newsModelList);
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