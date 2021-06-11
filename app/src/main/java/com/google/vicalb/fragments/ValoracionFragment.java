package com.google.vicalb.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
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
import com.google.vicalb.adapters.ReservaAdapter;
import com.google.vicalb.adapters.ValoracionAdapter;
import com.google.vicalb.models.Plato;
import com.google.vicalb.models.Reserva;
import com.google.vicalb.models.User;
import com.google.vicalb.models.Valoracion;

import java.util.ArrayList;
import java.util.List;


public class ValoracionFragment extends Fragment {

    private static final String TAG = "ValoracionFragment";

    EditText edOpinion = null;
    EditText edRest = null;
    EditText edOpinionRest = null;

    TextView tv;

    Button btnOpinion = null;

    public static List<Valoracion>  valoracionList = new ArrayList<>();

    ListView listView;
    ValoracionAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_valoracion, container, false);

        edOpinion = root.findViewById(R.id.edOpinion);
        edRest = root.findViewById(R.id.edRest);
        edOpinionRest = root.findViewById(R.id.edOpinionRest);

        btnOpinion = root.findViewById(R.id.btnOpinion);

        btnOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valoracionList.add(new Valoracion(edOpinion.getText().toString(),edRest.getText().toString(),edOpinionRest.getText().toString()));
                adapter.notifyDataSetChanged();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setSelection(listView.getCount() - 1);
                    }
                });


            }
        });


        listView = root.findViewById(R.id.listview);

        if (valoracionList.isEmpty())
            loadDatos();

        adapter = new ValoracionAdapter(getActivity().getApplicationContext(), R.layout.item_valoracion, valoracionList);

        adapter.setActivity(getActivity());
        //attaching adapter to the listview
        listView.setAdapter(adapter);


        return root;
    }

    private void loadDatos() {

        valoracionList.add(new Valoracion(getString(R.string.user1), getString(R.string.jap), getString(R.string.valor1)));
        valoracionList.add(new Valoracion(getString(R.string.user2), getString(R.string.ame), getString(R.string.valor2)));
        valoracionList.add(new Valoracion(getString(R.string.user3), getString(R.string.veget), getString(R.string.valor3)));
        valoracionList.add(new Valoracion(getString(R.string.user4), getString(R.string.maris), getString(R.string.valor4)));


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv = getActivity().findViewById(R.id.title);
        tv.setText(R.string.valoracion);

    }




}
