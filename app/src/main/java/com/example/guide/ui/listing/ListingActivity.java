package com.example.guide.ui.listing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.guide.R;
import com.example.guide.models.hotel.Hotels;
import com.example.guide.models.hotel.Records;
import com.example.guide.models.restaurant.Restaurant;
import com.example.guide.ui.fiche.HotelFicheActivity;
import com.example.guide.ui.fiche.RestaurantFicheActivity;
import com.example.guide.ui.home.HomeActivity;
import com.example.guide.utils.Constants;
import com.example.guide.utils.FastDialog;
import com.example.guide.utils.Network;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private ListView listViewData;
    private TextView textViewParagraf;
    //liste de restaurants qu'on va afficher dans la listViewData
    private List<Restaurant> restaurantList;
    private List<Records> hotelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        textViewTitle = findViewById(R.id.textViewTitle);
        listViewData = findViewById(R.id.listViewData);
        textViewParagraf = findViewById(R.id.textViewParagraf);

        //récupération des données
        if (getIntent().getExtras() != null) {
            //posibles maneras de recuperar el valor del intent de homeActivity
            //  boolean isRestaurant = getIntent().getExtras().getBoolean("isRestaurant");
            boolean isRestaurant = getIntent().getBooleanExtra("isRestaurant", false);
            if (isRestaurant) {
                textViewTitle.setText(getString(R.string.listing_restaurants_title));
                textViewParagraf.setText(getString(R.string.paragraf_restaurant));

                //creation d'arrayList et après on ajouter des restaurants dans la liste, après il faut ajouter cette liste dans la listViewData pour l'aficher
                restaurantList = new ArrayList<>();
                restaurantList.add(new Restaurant("Restaurant pepe", "good food", "pepe@gmail.com", "0603030303", "www.pepe.com", "https://u.tfstatic.com/restaurant_photos/848/37848/169/612/le-resto-vue-de-la-salle-19a9c.jpg"));
                restaurantList.add(new Restaurant("Restaurant popo", "oriental food", "popo@gmail.com", "0603030303", "www.pompom.com", "https://u.tfstatic.com/restaurant_photos/313/9313/169/612/cote-resto-notre-salle-cosy-bcf26.jpg"));
                restaurantList.add(new Restaurant("Restaurant Jean Pierre", "good food", "jeanpierre@gmail.com", "0603030303", "www.jeanpierre.com", "https://www.ahstatic.com/photos/6155_rsr001_00_p_1024x768.jpg"));

                //llamamos al adapter y le anyadimos el contexto, el layout y los datos , este caso el restuarantList
                listViewData.setAdapter(new RestaurantAdapter(ListingActivity.this, R.layout.item_restaurant, restaurantList));

                //creamos el listener para que recoja la posicion del restaurante y podemos pasar un extra con el objeto
                listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListingActivity.this, RestaurantFicheActivity.class);
                        //cogemos la position en el array
                        Restaurant item = restaurantList.get(position);
                        //lopasamos  en el intent
                        intent.putExtra("restaurant",  item);
                        //activamos el intent para que se pase a la actividad RestaurantFicheActivity
                        startActivity(intent);
                    }
                });

            } else {
                textViewTitle.setText(getString(R.string.listing_hotels_title));
                textViewParagraf.setText(getString(R.string.paragraf_hotels));

                if (Network.isNetworkAvailable(ListingActivity.this)) {
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(ListingActivity.this);
                    String url = Constants.URL_HOTEL;

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //on recupere  l'object hotel de l'appel
                                    final Hotels hotels = new Gson().fromJson(response, Hotels.class);
                                    hotelsList = new ArrayList<>();
                                    //on ajoute les valeurs de l'objet hotel à la liste
                                    for (int i = 0; i < hotels.records.size(); i++) {
                                        hotelsList.add(hotels.records.get(i));
                                    }
                                    // on passe à l'adaptateur records/hotel le context listingActivity, le layout et l'array avec les hotels
                                    listViewData.setAdapter(new RecordsAdapter(ListingActivity.this, R.layout.item_restaurant, hotelsList));

                                    //creamos el listener para que recoja la posicion del hotel y podemos pasar un extra con el objeto
                                    listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent = new Intent(ListingActivity.this, HotelFicheActivity.class);
                                            //cogemos la position en el array
                                            Records item = hotelsList.get(position);

                                            //lopasamos  en el intent
                                            intent.putExtra("hotelRecord",  item);
                                            //activamos el intent para que se pase a la actividad RestaurantFicheActivity
                                            startActivity(intent);
                                        }
                                    });


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("response ", "error dans ListingActivity");
                        }
                    });
                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                } else {
                    FastDialog.showDialog(ListingActivity.this, FastDialog.SIMPLE_DIALOG, "No internet connection...");
                }
            }
        }
    }

}
