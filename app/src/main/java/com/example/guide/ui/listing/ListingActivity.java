package com.example.guide.ui.listing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.guide.utils.Constants;
import com.example.guide.utils.FastDialog;
import com.example.guide.utils.Network;
import com.google.gson.Gson;

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
                //TODO : afficher liste de Restaurants
                textViewTitle.setText(getString(R.string.listing_restaurants_title));
                textViewParagraf.setText(getString(R.string.paragraf_restaurant));

                //creation d'arrayList et après on ajouter des restaurants dans la liste, après il faut ajouter cette liste dans la listViewData pour l'aficher
                restaurantList = new ArrayList<>();
                restaurantList.add(new Restaurant("Restaurant pepe", "good food", "pepe@gmail.com", "0603030303", "www.pepe.com", "data:image/jpeg;base64,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"));
                restaurantList.add(new Restaurant("Restaurant popo", "oriental food", "popo@gmail.com", "0603030303", "www.pompom.com", "data:image/jpeg;base64,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"));
                restaurantList.add(new Restaurant("Restaurant Jean Pierre", "good food", "jeanpierre@gmail.com", "0603030303", "www.jeanpierre.com", "data:image/jpeg;base64,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"));

                //llamamos al adapter y le anyadimos el contexto, el layout y los datos , este caso el restuarantList
                listViewData.setAdapter(new RestaurantAdapter(ListingActivity.this, R.layout.item_restaurant, restaurantList));

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
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("response ", "error");
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
