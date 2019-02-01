package com.example.guide.ui.fiche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.example.guide.R;
import com.example.guide.models.hotel.Records;

import com.example.guide.ui.map.MapboxActivity;
import com.example.guide.ui.map.MapboxFragment;


public class HotelFicheActivity extends AppCompatActivity {

    TextView ficheTitle, ficheCategory;
    //  ImageView ficheImage;
    Button ficheEmail, ficheTel, ficheUrl;
    Records hotel;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_fiche);

        ficheTitle = findViewById(R.id.fiche_nom_hotel);
        ficheCategory = findViewById(R.id.fiche_categorie_hotel);
        ficheEmail = findViewById(R.id.fiche_emailHotel);
        ficheTel = findViewById(R.id.fiche_phoneHotel);
        ficheUrl = findViewById(R.id.fiche_urlHotel);

        if (getIntent().getExtras() != null) {
            hotel = (Records) getIntent().getSerializableExtra("hotelRecord");
            ficheTitle.setText(hotel.fields.nom);
            ficheCategory.setText(hotel.fields.categorie);
            ficheEmail.setText(hotel.fields.commune);
            ficheTel.setText(hotel.fields.capacite_personne);
            ficheUrl.setText(hotel.fields.adresse);
            latitude = hotel.fields.coord_geo_adresse[0];
            longitude = hotel.fields.coord_geo_adresse[1];

        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerMap, MapboxFragment.newInstance(latitude, longitude))
                .commit();
    }

    public void showHotel(View view) {
        Intent intent = new Intent(HotelFicheActivity.this, MapboxActivity.class);
            intent.putExtra("hotelLat", latitude);
            intent.putExtra("hotelLong", longitude);
        startActivity(intent);
    }
}

