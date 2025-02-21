package com.google.vicalb.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.vicalb.R;

public class ShraredPreferencesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    int selected = -1;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Restaurante = "nameKey";
    public static final String Tipo = "phoneKey";
    public static final String Zoom = "zoomKey";
    public static final String Red = "redKey";



    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        actionBar();

        ImageView btnC = findViewById(R.id.btnCheck);
        ListView lv = findViewById(R.id.lv);
        String[] mobileArray = {getResources().getString(R.string.americano),getResources().getString(R.string.japones),getResources().getString(R.string.vegetariano),getResources().getString(R.string.marisqueria)};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.simple_list_view, R.id.textView, mobileArray);

        lv.setAdapter(adapter);
        lv.setSelector(R.color.colorSel);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final RadioButton rbM = findViewById(R.id.radioButton5);
        final RadioButton rbNom = findViewById(R.id.radioButton7);
        final RadioButton rbPoco = findViewById(R.id.radioButton);
        final RadioButton rbInt = findViewById(R.id.radioButton10);




        lv.setOnItemClickListener(this);

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected == -1) {
                    Toast.makeText(ShraredPreferencesActivity.this, "Es necesario seleccionar un restaurante", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    String type = "tarjeta";
                    Float zoom = 19.5f;
                    String internet = "WiFi";

                    if(rbNom.isChecked()) zoom = 18.0f;
                    else if(rbPoco.isChecked()) zoom = 16.5f;

                    if(rbM.isChecked()) type = "efectivo";

                    if(rbInt.isChecked()) internet = "Datos";

                    editor.putString(Red, internet);
                    editor.putFloat(Zoom, zoom);
                    editor.putInt(Restaurante, selected);
                    editor.putString(Tipo, type);
                    editor.commit();

                    Intent result = new Intent();
                    setResult(RESULT_OK, result);
                    finish();
                }
            }
        });
    }


    public void actionBar(){
        Toolbar tb = findViewById(R.id.toolbar7);
        TextView title1 = findViewById(R.id.title2);
        title1.setText(R.string.prefe);
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
    public void onItemClick(AdapterView parent, View view, int index, long arg3) {
        // gets the contact from adapter

        selected = index;
    }
}
