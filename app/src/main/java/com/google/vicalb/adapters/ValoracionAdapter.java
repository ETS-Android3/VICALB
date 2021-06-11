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
import com.google.vicalb.models.Valoracion;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ValoracionAdapter extends ArrayAdapter<Valoracion> {

    @NonNull
    private final Context context;
    private final int resource;
    @NonNull
    private final List<Valoracion> objects;

    Activity activity;

    public ValoracionAdapter(@NonNull Context context, int resource, @NonNull List<Valoracion> objects) {
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
        TextView tvUser = view.findViewById(R.id.tvUser1);
        TextView tvRestaurante = view.findViewById(R.id.tvRestaurante);
        TextView tvValoracion = view.findViewById(R.id.tvValoracion);

        //getting the hero of the specified position
        final Valoracion valoracion = objects.get(position);

        //adding values to the list item
        tvUser.setText(valoracion.getNombre());
        tvRestaurante.setText(valoracion.getRestaurante());
        tvValoracion.setText(valoracion.getValoracion());

        return view;
    }
}
