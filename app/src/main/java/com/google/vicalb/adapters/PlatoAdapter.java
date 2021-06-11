package com.google.vicalb.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.vicalb.R;
import com.google.vicalb.activities.ComprarActivity;
import com.google.vicalb.activities.DetailsRestauranteActivity;
import com.google.vicalb.activities.TarjetaActivity;
import com.google.vicalb.models.Plato;
import com.google.vicalb.models.Reserva;
import com.google.vicalb.models.Restaurante;
import com.google.vicalb.models.User;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PlatoAdapter extends ArrayAdapter<Plato> {

    @NonNull
    private final Context context;
    private final int resource;
    @NonNull
    private final List<Plato> objects;

    Activity activity;

    public PlatoAdapter(@NonNull Context context, int resource, @NonNull List<Plato> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView tvNombre = view.findViewById(R.id.tvNombre);
        ImageView ivImagen = view.findViewById(R.id.ivImagen);
        TextView tvDescripcion = view.findViewById(R.id.tvDescripcion);
        TextView tvPrecio = view.findViewById(R.id.tvPrecio);

        Button btnTarj = view.findViewById(R.id.btnTarj);
        Button btnEfec = view.findViewById(R.id.btnEfec);


        //getting the hero of the specified position
        final Plato plato = objects.get(position);

        //adding values to the list item
        tvNombre.setText(plato.getNombre());

        switch (plato.getImage()) {

            case "gyozas":
                ivImagen.setImageResource(R.drawable.gyozas);
                break;
            case "yakisoba":
                ivImagen.setImageResource(R.drawable.yakisoba);
                break;
            case "sushi":
                ivImagen.setImageResource(R.drawable.sushi);
                break;
            case "mochi":
                ivImagen.setImageResource(R.drawable.mochi);
                break;
            case "big":
                ivImagen.setImageResource(R.drawable.big);
                break;
            case "extreme":
                ivImagen.setImageResource(R.drawable.extreme);
                break;
            case "crunchy":
                ivImagen.setImageResource(R.drawable.crunchy);
                break;
            case "monterey":
                ivImagen.setImageResource(R.drawable.monterey);
                break;

            case "porridge":
                ivImagen.setImageResource(R.drawable.porridge);
                break;
            case "menestra":
                ivImagen.setImageResource(R.drawable.menestra);
                break;
            case "calabacin":
                ivImagen.setImageResource(R.drawable.calabacin);
                break;
            case "quinoa":
                ivImagen.setImageResource(R.drawable.quinoa);
                break;

            case "ostras":
                ivImagen.setImageResource(R.drawable.ostras);
                break;
            case "camarones":
                ivImagen.setImageResource(R.drawable.camarones);
                break;
            case "lubina":
                ivImagen.setImageResource(R.drawable.lubina);
                break;
            case "solomillo":
                ivImagen.setImageResource(R.drawable.solomillo);
                break;


        }

        tvDescripcion.setText(plato.getDescripcion());
        tvPrecio.setText(plato.getPrecio());

        //adding a click listener to the button to remove item from the list

        btnTarj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TarjetaActivity.class);
                intent.putExtra("rest", plato.getIdRestaurante());
                activity.startActivity(intent);
            }
        });

        btnEfec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "reserva realizada", Toast.LENGTH_SHORT).show();

                // Create a Uri from an intent string. Use the result to create an Intent.
                /*Uri gmmIntentUri = Uri.parse("google.streetview:cbll="
                        + plato.getRestaurante().getLat()
                        + "," + plato.getRestaurante().getLon());*/

                uploadReserva(plato);


                new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("RESERVA REALIZADA!!")
                        .setConfirmText("Cómo ir")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {


                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                Query query = databaseReference.child("restaurantes")
                                        .orderByChild("id").equalTo(plato.getIdRestaurante());


                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {

                                            Restaurante restaurante = null;

                                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                // TODO: handle the post
                                                restaurante = child.getValue(Restaurante.class);
                                            }


                                            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + restaurante.getLat()
                                                    + "," + restaurante.getLon());

                                            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                            // Make the Intent explicit by setting the Google Maps package
                                            mapIntent.setPackage("com.google.android.apps.maps");

                                            // Attempt to start an activity that can handle the Intent
                                            activity.startActivity(mapIntent);

                                            sDialog.dismissWithAnimation();
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
                        }).show();

            }
        });

        return view;
    }

    public void uploadReserva(Plato plato) {

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;

        Reserva reserva = new Reserva(User.uid,plato);
        mDatabase.child("reservas").push().setValue(reserva);


    }
}
