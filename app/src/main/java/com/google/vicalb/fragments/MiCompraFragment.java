package com.google.vicalb.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.vicalb.R;
import com.google.vicalb.activities.ComprarActivity;
import com.google.vicalb.adapters.ListRestauranteAdapter;
import com.google.vicalb.adapters.PlatoAdapter;
import com.google.vicalb.adapters.ReservaAdapter;
import com.google.vicalb.models.Plato;
import com.google.vicalb.models.Reserva;
import com.google.vicalb.models.Restaurante;
import com.google.vicalb.models.User;

import java.util.ArrayList;
import java.util.List;


public class MiCompraFragment extends Fragment {

    private static final String TAG = "MiCompraFragment";

    public static List<Plato> platoList;

    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        platoList = new ArrayList<>();
        listView = view.findViewById(R.id.listview);

        loadDatos();

        TextView tv = getActivity().findViewById(R.id.title);
        tv.setText(R.string.micompra);

    }


    public void loadDatos(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query queryPlatos = databaseReference.child("reservas")
                .orderByChild("uid").equalTo(User.uid);


        queryPlatos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        // TODO: handle the post
                        Reserva reserva = child.getValue(Reserva.class);
                        platoList.add(reserva.getPlato());
                    }


                    ReservaAdapter adapter = new ReservaAdapter(getActivity().getApplicationContext(), R.layout.item_comprar, platoList);

                    adapter.setActivity(getActivity());
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









}
