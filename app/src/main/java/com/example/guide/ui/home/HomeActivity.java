package com.example.guide.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.guide.R;
import com.example.guide.ui.listing.ListingActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showRestaurant(View view) {
        Intent intent = new Intent(HomeActivity.this, ListingActivity.class);
        intent.putExtra("isRestaurant", true);
        startActivity(intent);
    }

    public void showHotel(View view) {
        Intent intent = new Intent(HomeActivity.this, ListingActivity.class);
        intent.putExtra("isRestaurant", false);
        startActivity(intent);
    }
}
