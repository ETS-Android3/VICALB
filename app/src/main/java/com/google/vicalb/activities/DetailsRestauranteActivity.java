package com.google.vicalb.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.vicalb.R;
import com.google.vicalb.models.Restaurante;

public class DetailsRestauranteActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int val;
    TextView tvC,tvD,textView9,tvL;
    TextView tvM;
    TextView titul;

    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Zoom = "zoomKey";

    private float zoomMap = 17.0f;

    Restaurante currentRestautante=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_restaurante);

        Bundle extras = getIntent().getExtras();
        val = extras.getInt("Restaurante");
        currentRestautante = (Restaurante) extras.getSerializable("currentRestautante");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        actionBar();

        getVar();

        if(val == 1) setInfoC();

        titul.setText(currentRestautante.getRestaurante());
        tvD.setText(currentRestautante.getDescripcion());
        textView9.setText(currentRestautante.getHorario());
        tvL.setText(currentRestautante.getLocalización());

    }

    private void getVar(){
        titul = findViewById(R.id.tvNom);
        tvC = findViewById(R.id.tvC);
        tvD = findViewById(R.id.tvD);
        tvM = findViewById(R.id.tvM);
        tvL = findViewById(R.id.tvL);

        textView9 = findViewById(R.id.textView9);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    private void check(){
        if(sharedpreferences != null) {
            zoomMap = sharedpreferences.getFloat(Zoom, 0.0f);
            if(zoomMap == 0.0f) zoomMap = 17.0f;

        }

    }

    public void setInfoC(){
        TextView tvD = findViewById(R.id.tvD);
        TextView tvL = findViewById(R.id.tvL);

        tvD.setText(R.string.americanoD);
        tvL.setText(R.string.americanoL);
        titul.setText(R.string.ame);
    }

    public void actionBar(){
        Toolbar tb = findViewById(R.id.toolbar3);
        setSupportActionBar(tb);

        // Añadir flecha atras en toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng recto;
        if(val == 1) {
            recto = new LatLng(41.609155, 0.624183);
        } else
            recto = new LatLng(41.615451, 0.618851);

        recto = new LatLng(currentRestautante.getLat(), currentRestautante.getLon());

        check();
        mMap.addMarker(new MarkerOptions().position(recto));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(recto, zoomMap));
    }

    public void btnR(View view){
        Intent i = new Intent(getApplicationContext(), ComprarActivity.class);
        if(currentRestautante.getRestaurante().equals(getResources().getString(R.string.ame))){
            i.putExtra("Restaurante", 1);
        } else
            i.putExtra("Restaurante", 2);

        i.putExtra("currentRestautante", currentRestautante);
        startActivity(i);
    }


}
