package com.google.vicalb.adapters;

import android.content.Context;
import android.content.Intent;
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

import com.google.vicalb.R;
import com.google.vicalb.activities.ComprarActivity;
import com.google.vicalb.activities.DetailsRestauranteActivity;
import com.google.vicalb.models.Restaurante;

import java.util.List;

public class ListRestauranteAdapter extends ArrayAdapter<Restaurante> {

    @NonNull
    private final Context context;
    private final int resource;
    @NonNull
    private final List<Restaurante> objects;

    public ListRestauranteAdapter(@NonNull Context context, int resource, @NonNull List<Restaurante> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        boolean enabled = true;
        boolean enabled2 = true;
        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        ImageView imageView = view.findViewById(R.id.ivRestaurante);
        final TextView tvName = view.findViewById(R.id.tvRestaurante);



        Button btnR = view.findViewById(R.id.btnComprar);
        Button btnM = view.findViewById(R.id.btnInfo);


        //getting the hero of the specified position
        final Restaurante restaurante = objects.get(position);

        //adding values to the list item


        switch (restaurante.getImage()){
            case 0:
                imageView.setBackgroundResource(R.drawable.japones);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.americano);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.vegatariano);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.marisqueria);
                break;
        }

        tvName.setText(restaurante.getRestaurante());

        //adding a click listener to the button to remove item from the list
        final boolean finalEnabled = enabled;
        final boolean finalEnabled2 = enabled2;
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalEnabled && finalEnabled2) {

                    Toast.makeText(context,"Menú de platos del restaurante", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(parent.getContext(), ComprarActivity.class);
                    if(restaurante.getRestaurante().equals(parent.getResources().getString(R.string.ame))){
                        i.putExtra("Restaurante", 1);
                    } else
                        i.putExtra("Restaurante", 2);

                    i.putExtra("currentRestautante", restaurante);

                    parent.getContext().startActivity(i);

                } else if(!finalEnabled2) {
                    Toast.makeText(parent.getContext(), "Todo lleno actualmente", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(parent.getContext(), "No disponible actualmente", Toast.LENGTH_SHORT).show();
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalEnabled) {
                    Intent i = new Intent(parent.getContext(), DetailsRestauranteActivity.class);
                    if(restaurante.getRestaurante().equals(parent.getResources().getString(R.string.ame))){
                        i.putExtra("Restaurante", 1);
                    } else
                        i.putExtra("Restaurante", 2);

                    i.putExtra("currentRestautante", restaurante);
                    parent.getContext().startActivity(i);

                } else Toast.makeText(parent.getContext(), "No disponible actualmentee", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
