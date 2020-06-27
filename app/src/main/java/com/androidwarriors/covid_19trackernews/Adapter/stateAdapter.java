package com.androidwarriors.covid_19trackernews.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.androidwarriors.covid_19trackernews.IndianStates;
import com.androidwarriors.covid_19trackernews.Model.CountryModel;
import com.androidwarriors.covid_19trackernews.Model.stateModel;
import com.androidwarriors.covid_19trackernews.R;

import java.util.ArrayList;
import java.util.List;

public class stateAdapter extends ArrayAdapter<stateModel> {

    private Context context;
    private List<stateModel> countryModelList;
    private List<stateModel> countryModelListFiltered;

    public stateAdapter( Context context,  List<stateModel> countryModelList) {
        super(context, R.layout.indian_state_layout, countryModelList);

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
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.flag);

        textView.setText(countryModelListFiltered.get(position).getName());
        //Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(imageView);

        imageView.setImageBitmap(bitmap);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public stateModel getItem(int position) {
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


                    List<stateModel> resultModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (stateModel itemModel: countryModelList){

                        if (itemModel.getName().toLowerCase().contains(searchStr)){

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


                countryModelListFiltered = (List<stateModel>) results.values;
                IndianStates.countryModelList = (List<stateModel>) results.values;
                notifyDataSetChanged();


            }
        };



        return filter;
    }
}
