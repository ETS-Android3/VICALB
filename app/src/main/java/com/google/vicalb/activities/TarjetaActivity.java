package com.google.vicalb.activities;



import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.vicalb.R;
import com.google.vicalb.models.Restaurante;

public class TarjetaActivity extends AppCompatActivity {

    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;

    int idRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        idRestaurante= (int) getIntent().getSerializableExtra("rest");

        cardForm = findViewById(R.id.card_form);
        buy = findViewById(R.id.btnBuy);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Se requiere SMS en este número")
                .setup(TarjetaActivity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("restaurantes")
                .orderByChild("id").equalTo(idRestaurante);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    Restaurante restaurante=null;

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        // TODO: handle the post
                        restaurante = child.getValue(Restaurante.class);
                    }
                   showTarjeta(restaurante);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(ComprarActivity.TAG, "loadRestaurante:onCancelled", databaseError.toException());
                // ...
            }
        });

    }

    public void showTarjeta(Restaurante restaurante){
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    alertBuilder = new AlertDialog.Builder(TarjetaActivity.this);
                    alertBuilder.setTitle("Confirmar antes de comprar");
                    alertBuilder.setMessage("Número de tarjeta: " + cardForm.getCardNumber() + "\n" +
                            "Fecha de expiración de la tarjeta: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "CVV: " + cardForm.getCvv() + "\n" +
                            "Código Postal: " + cardForm.getPostalCode() + "\n" +
                            "Número de teléfono: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(TarjetaActivity.this, "Pago realizado con exito", Toast.LENGTH_LONG).show();
                            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + restaurante.getLat()
                                    + "," + restaurante.getLon());

                            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            // Make the Intent explicit by setting the Google Maps package
                            mapIntent.setPackage("com.google.android.apps.maps");

                            // Attempt to start an activity that can handle the Intent
                            startActivity(mapIntent);
                            finish();

                        }
                    });
                    alertBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();

                } else {
                    Toast.makeText(TarjetaActivity.this, "Por favor complete el formulario", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
