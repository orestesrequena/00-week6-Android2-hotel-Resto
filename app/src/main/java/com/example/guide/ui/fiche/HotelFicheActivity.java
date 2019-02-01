package com.example.guide.ui.fiche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guide.R;
import com.example.guide.models.hotel.Records;
import com.example.guide.models.restaurant.Restaurant;
import com.squareup.picasso.Picasso;

public class HotelFicheActivity extends AppCompatActivity {

    TextView ficheTitle, ficheCategory;
    //  ImageView ficheImage;
    Button ficheEmail, ficheTel, ficheUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_fiche);

        ficheTitle = findViewById(R.id.fiche_nom_hotel);
        ficheCategory = findViewById(R.id.fiche_categorie_hotel);
//        ficheImage = findViewById(R.id.fiche_image);
        ficheEmail = findViewById(R.id.fiche_emailHotel);
        ficheTel = findViewById(R.id.fiche_phoneHotel);
        ficheUrl = findViewById(R.id.fiche_urlHotel);

        if (getIntent().getExtras() != null) {
            Records hotel = (Records) getIntent().getSerializableExtra("hotelRecord");
//
            ficheTitle.setText(hotel.fields.nom);
            ficheCategory.setText(hotel.fields.categorie);
            ficheEmail.setText(hotel.fields.adresse);
            ficheTel.setText(hotel.fields.capacite_personne);
            ficheUrl.setText(hotel.fields.commune);
//          //  Picasso.get().load(restaurant.image).into(ficheImage);
        }

    }
}
