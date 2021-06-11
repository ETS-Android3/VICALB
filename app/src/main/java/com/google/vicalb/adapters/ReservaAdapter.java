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
import com.google.vicalb.activities.TarjetaActivity;
import com.google.vicalb.models.Plato;
import com.google.vicalb.models.Reserva;
import com.google.vicalb.models.Restaurante;
import com.google.vicalb.models.User;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ReservaAdapter extends ArrayAdapter<Plato> {

    @NonNull
    private final Context context;
    private final int resource;
    @NonNull
    private final List<Plato> objects;

    Activity activity;

    public ReservaAdapter(@NonNull Context context, int resource, @NonNull List<Plato> objects) {
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

        switch (plato.getIdRestaurante()) {

            case 0:
                ivImagen.setImageResource(R.drawable.japones);
                break;
            case 1:
                ivImagen.setImageResource(R.drawable.americano);
                break;
            case 2:
                ivImagen.setImageResource(R.drawable.vegatariano);
                break;
            case 3:
                ivImagen.setImageResource(R.drawable.marisqueria);
                break;
        }

        tvDescripcion.setText(plato.getDescripcion());
        tvPrecio.setText(plato.getPrecio());

        tvDescripcion.setVisibility(View.GONE);
        tvPrecio.setVisibility(View.GONE);
        btnTarj.setVisibility(View.GONE);
        btnEfec.setVisibility(View.GONE);

        return view;
    }
}
