package com.example.guide.ui.fiche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guide.R;
import com.example.guide.models.restaurant.Restaurant;
import com.squareup.picasso.Picasso;

public class RestaurantFicheActivity extends AppCompatActivity {

    TextView ficheTitle, ficheCategory;
    ImageView ficheImage;
    Button ficheEmail, ficheTel, ficheUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_fiche);

        ficheTitle = findViewById(R.id.fiche_nom_restaurant);
        ficheCategory = findViewById(R.id.fiche_categorie_restaurant);
        ficheImage = findViewById(R.id.fiche_image);
        ficheEmail = findViewById(R.id.fiche_email);
        ficheTel = findViewById(R.id.fiche_phone);
        ficheUrl = findViewById(R.id.fiche_url);

        if (getIntent().getExtras() != null){
            Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");

            ficheTitle.setText(restaurant.name);
            ficheCategory.setText(restaurant.category);
            ficheEmail.setText(restaurant.email);
            ficheTel.setText(restaurant.phone);
            ficheUrl.setText(restaurant.url);
            Picasso.get().load(restaurant.image).into(ficheImage);
        }

    }
}
