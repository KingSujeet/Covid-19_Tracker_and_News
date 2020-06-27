package com.androidwarriors.covid_19trackernews.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidwarriors.covid_19trackernews.DetailsActivity;
import com.androidwarriors.covid_19trackernews.Model.CountryModel;
import com.androidwarriors.covid_19trackernews.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;

    public CountryAdapter( Context context,  List<CountryModel> countryModelList) {
        super(context, R.layout.country_custom_layout, countryModelList);

        this.context = context;
        this.countryModelList = countryModelList;
        this.countryModelListFiltered = countryModelList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_custom_layout,null,true);

        TextView textView = view.findViewById(R.id.textView);
        ImageView imageView = view.findViewById(R.id.imageView);

        textView.setText(countryModelListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(imageView);


        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();

                if (constraint == null || constraint.length() == 0){


                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;
                }else{


                    List<CountryModel> resultModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (CountryModel itemModel: countryModelList){

                        if (itemModel.getCountry().toLowerCase().contains(searchStr)){

                            resultModel.add(itemModel);
                        }

                        filterResults.count = resultModel.size();
                        filterResults.values = resultModel;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {


                countryModelListFiltered = (List<CountryModel>) results.values;
                DetailsActivity.countryModelList = (List<CountryModel>) results.values;
                notifyDataSetChanged();


            }
        };



        return filter;
    }
}
