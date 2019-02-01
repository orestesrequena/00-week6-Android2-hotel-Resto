package com.example.guide.ui.fiche;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guide.R;
import com.example.guide.models.restaurant.Restaurant;
import com.squareup.picasso.Picasso;

public class RestaurantFicheActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ficheTitle, ficheCategory;
    ImageView ficheImage;
    Button ficheEmail, ficheTel, ficheUrl, ficheSms;

    // funcion para decidir  si puede enviar un email, si puede llamar o si puede ver la pagina web
    @Override
    public void onClick(View v) {
        //esto es para caster la variable v y poder usar los metodos getText.toString para tener la informacion del boton
        Button button = (Button)v;
        switch (v.getId()){
            case R.id.fiche_email:
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setType("message/rfc822");
                intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {(button.getText().toString())});
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "le sujet");
                intentEmail.putExtra(Intent.EXTRA_TEXT, "le message");
                //test fallido,  no detecta aplicacion para enviar el email en el emulador
              //  startActivity(Intent.createChooser(intentEmail, "Message"));

                try {
                    startActivity(Intent.createChooser(intentEmail, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RestaurantFicheActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.fiche_phone:
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                intentCall.setData(Uri.parse("tel:"+button.getText().toString()));

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);
                    }
                    return;
                }
                startActivity(intentCall);
                break;
            case R.id.fiche_sms:
                Intent intentSms = new Intent(Intent.ACTION_SENDTO);
                intentSms.setData(Uri.parse("smsto:"+button.getText().toString()));
                intentSms.putExtra("sms_body", "The SMS text");

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 234);
                    }
                    return;
                }
                startActivity(intentSms);
                break;
            case R.id.fiche_url:
                Intent intentUrl = new Intent(Intent.ACTION_VIEW);
                intentUrl.setData(Uri.parse("http://"+button.getText().toString()));
                startActivity(intentUrl);
                break;
        }
    }

        //gestion de callback para la autorizacionde la actividad del callPHONE, resquest code es 123, las permision es el string[} perlision CALL_PHONE
        //el resquest code ponerlo mejor en constant
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==123){
            if(permissions[0].equals(Manifest.permission.CALL_PHONE) && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //esto simula el click en el boton para hacer la llamada
                ficheTel.performClick();
            }
        }
        if(requestCode ==234){
            if(permissions[0].equals(Manifest.permission.SEND_SMS) && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //esto simula el click en el boton para enviar el mensaje
                ficheSms.performClick();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_fiche);

        ficheTitle = findViewById(R.id.fiche_nom_restaurant);
        ficheCategory = findViewById(R.id.fiche_categorie_restaurant);
        ficheImage = findViewById(R.id.fiche_image);
        ficheEmail = findViewById(R.id.fiche_email);
        ficheTel = findViewById(R.id.fiche_phone);
        ficheSms = findViewById(R.id.fiche_sms);
        ficheUrl = findViewById(R.id.fiche_url);
        //para dar acceso a la funcion onclick a los 3 botones
        ficheEmail.setOnClickListener(this);
        ficheTel.setOnClickListener(this);
        ficheSms.setOnClickListener(this);
        ficheUrl.setOnClickListener(this);

        if (getIntent().getExtras() != null){
            Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");

            ficheTitle.setText(restaurant.name);
            ficheCategory.setText(restaurant.category);
            ficheEmail.setText(restaurant.email);
            ficheTel.setText(restaurant.phone);
            ficheSms.setText(restaurant.phone);
            ficheUrl.setText(restaurant.url);
            Picasso.get().load(restaurant.image).into(ficheImage);
        }

    }
}
