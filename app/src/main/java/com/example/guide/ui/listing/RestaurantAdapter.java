package com.example.guide.ui.listing;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guide.R;
import com.example.guide.models.restaurant.Restaurant;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter <Restaurant>{
    private  int resId;

    public RestaurantAdapter( Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);
        resId = resource;// corresponde a R.layout.item_restaurant
    }
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        //ahora el convert view llama el layout que hemos hecho R.layout.restaurant
        convertView = LayoutInflater.from(getContext()).inflate(resId, null);
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewCategory = convertView.findViewById(R.id.textViewCategory);
        Restaurant item = getItem(position);
        textViewTitle.setText(item.name);
        textViewCategory.setText(item.category.toLowerCase());

        return convertView;
    }
}
