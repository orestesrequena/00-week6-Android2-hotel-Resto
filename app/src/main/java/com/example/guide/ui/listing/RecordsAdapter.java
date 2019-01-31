package com.example.guide.ui.listing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.guide.R;
import com.example.guide.models.hotel.Records;

import java.util.List;

public class RecordsAdapter extends ArrayAdapter <Records>{
    private  int resId;

    public RecordsAdapter( Context context, int resource, List<Records> objects) {
        super(context, resource, objects);
        resId = resource;// corresponde a R.layout.item_restaurant
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        //ahora el convert view llama el layout que hemos hecho R.layout.restaurant
        convertView = LayoutInflater.from(getContext()).inflate(resId, null);
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewCategory = convertView.findViewById(R.id.textViewCategory);

        Records item = getItem(position);
        textViewTitle.setText(item.fields.nom +" "+ item.fields.categorie);
        textViewCategory.setText(item.fields.adresse.toLowerCase());

        return convertView;
    }
}
