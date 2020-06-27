package com.androidwarriors.covid_19trackernews.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidwarriors.covid_19trackernews.Model.NewsModel;
import com.androidwarriors.covid_19trackernews.Model.stateModel;
import com.androidwarriors.covid_19trackernews.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsModel> {

    private Context context;
    private List<NewsModel> newsModelList;


    public NewsAdapter(Context context, List<NewsModel> newsModelList) {
        super(context, R.layout.news_custom_layout, newsModelList);

        this.context = context;
        this.newsModelList = newsModelList;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_custom_layout,null,true);

        TextView title = view.findViewById(R.id.title);
        TextView content = view.findViewById(R.id.content);
        TextView read = view.findViewById(R.id.linkTitle);
        TextView url = view.findViewById(R.id.url);
        ImageView imageView = view.findViewById(R.id.imageView2);

        title.setText(newsModelList.get(position).getTitle());
        content.setText(newsModelList.get(position).getDescription());
        read.setText("Read full Article...");
        url.setText(newsModelList.get(position).getUrl());
        Glide.with(context).load(newsModelList.get(position).getFlag()).into(imageView);

        return view;
    }

}
