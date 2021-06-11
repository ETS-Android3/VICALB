package com.google.vicalb.activities;


import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.vicalb.R;
import com.google.vicalb.adapters.PlatoAdapter;
import com.google.vicalb.models.Plato;
import com.google.vicalb.models.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class ComprarActivity extends AppCompatActivity {

    int val;
    List<Plato> platoList;
    Restaurante currentRestautante = null;
    ListView listView;

    public static final String TAG = "ComprarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        Bundle extras = getIntent().getExtras();
        val = extras.getInt("Restaurante");
        currentRestautante = (Restaurante) extras.getSerializable("currentRestautante");

        actionBar();
        getVar();

        platoList = new ArrayList<>();

        loadDatos();
    }

    public void loadDatos() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query queryPlatos = databaseReference.child("platos")
                .orderByChild("idRestaurante").equalTo(currentRestautante.getId());


        queryPlatos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        // TODO: handle the post
                        Plato plato = child.getValue(Plato.class);
                        platoList.add(plato);
                    }


                    PlatoAdapter adapter = new PlatoAdapter(getApplicationContext(), R.layout.item_comprar, platoList);

                    adapter.setActivity(ComprarActivity.this);
                    //attaching adapter to the listview
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPlatos:onCancelled", databaseError.toException());
                // ...
            }
        });


    }

    private void getVar() {
        listView = findViewById(R.id.listview);
    }

    public void actionBar() {
        Toolbar tb = findViewById(R.id.toolbar4);
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
}
