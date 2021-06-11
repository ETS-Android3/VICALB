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
import com.google.vicalb.adapters.ListRestauranteAdapter;
import com.google.vicalb.models.Plato;
import com.google.vicalb.models.Restaurante;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Restaurante = "nameKey";

    SharedPreferences sharedpreferences;

    List<com.google.vicalb.models.Restaurante> restauranteList;
    List<Restaurante> restauranteList2;

    public static List<Plato> platoList;


    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_home, container, false);

        TextView tv = getActivity().findViewById(R.id.title);
        if(tv!=null)
            tv.setText(R.string.app_name);

        /*TextView tv = root.findViewById(R.id.title);
        tv.setText(getActivity().getResources().getString(R.string.app_name));*/

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        restauranteList2 = new ArrayList<>();
        restauranteList = new ArrayList<>();
        platoList = new ArrayList<>();
        listView = view.findViewById(R.id.listview);

        //getRestaurante();
        //crearListaPlatos();

        //uploadDatos();

        loadDatos();


    }


    public void uploadDatos(){

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;

        mDatabase.child("restaurantes").setValue(restauranteList2);
        mDatabase.child("platos").setValue(platoList);
    }

    public void loadDatos(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query queryRestaurantes = databaseReference.child("restaurantes");

        queryRestaurantes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    Restaurante restaurante = child.getValue(Restaurante.class);
                    restauranteList2.add(restaurante);
                }
                createList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadRestaurantes:onCancelled", databaseError.toException());
                // ...
            }
        });

        //mDatabase.child("restaurantes").setValue(restauranteList2);
        //mDatabase.child("platos").setValue(platoList);



    }


    public void createList(){
        int type = -1;
        //adding some values to our list
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(sharedpreferences != null) {
            type = sharedpreferences.getInt(Restaurante, 0);
            if(type == 0) restauranteList.add(restauranteList2.get(0));
            else restauranteList.add(restauranteList2.get(1));
        }

        adding(type);
        //creating the adapter
        ListRestauranteAdapter adapter = new ListRestauranteAdapter(getContext(), R.layout.view_restaurante, restauranteList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }

    public void adding(int add){
        for(int i = 0; i<restauranteList2.size(); i++){
            if(i!=add) restauranteList.add(restauranteList2.get(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //restauranteList = new ArrayList<>();
        //listView.setAdapter(null);
        //getRestaurante();
        //loadDatos();

    }

     /*private void getRestaurante(){
        //db.collection("Restaurante");

        restauranteList2 = new ArrayList<>();
        restauranteList = new ArrayList<>();
        listView.setAdapter(null);

        restauranteList2.add(new Restaurante(R.drawable.japones,"Restaurante Japones","100/150","200/250",5,20));
        restauranteList2.add(new Restaurante(R.drawable.americano,"Restaurante Americano","100/150","200/250",10,10));
        restauranteList2.add(new Restaurante(R.drawable.vegatariano,"Restaurante Vegetariano","100/150","200/250",7,50));
        restauranteList2.add(new Restaurante(R.drawable.marisqueria,"Restaurante Marisqueria","100/150","200/250",8,20));

        restauranteList2.get(0).setDescripcion("La gastronomía no deja de ser algo muy subjetivo y por esa razón Bokoto Sushi Club ofrece una experiencia más allá de la cocina japonesa.\n" +
                "\n" + "Intentamos siempre que puedas sentirte a gusto en un espacio cálido y cómodo, acompañado de un servicio atento y que estará encantado de responder tus dudas y sugerir lo mejor para ti.\n" +
                "\n" + "En Bokoto encontrarás la esencia de la comida japonesa, la emoción y el placer de sabores exquisitos en un espacio con alma.  ");
        restauranteList2.get(0).setHorario("El horario es de Martes a Domingo de 13h a 16h y de 18.30h a 21h");
        restauranteList2.get(0).setLocalización("C/ Magí Morera, 57, Lleida ");
        restauranteList2.get(0).setLat(41.618257);
        restauranteList2.get(0).setLon(0.621278);

        restauranteList2.get(1).setDescripcion("Conoce en VICALB el sabor de la comida americana: hamburguesas, costillas... Descubre nuestra carta y encuentra tu restaurante más cercano.");
        restauranteList2.get(1).setHorario("El horario es de Lunes a Sabado de 12h a 17h y de 20:00h a 23:50h ");
        restauranteList2.get(1).setLocalización("C/ Rambla Ferran, 40, Lleida ");
        restauranteList2.get(1).setLat(41.629541);
        restauranteList2.get(1).setLon(0.629368);


        restauranteList2.get(2).setDescripcion("Colorido, divertido y especioso, este restaurante de Cappont cercano a la Universidad utiliza ingredientes orgánicos para apostar por platos de origen hindú y nepalí. Prueba su hummus y su mussaka y también sus 'smoothies', sus zumos y su batido de plátano y chocolate. ");
        restauranteList2.get(2).setHorario("El horario es de Lunes a Sabado de 12h a 18h");
        restauranteList2.get(2).setLocalización("C/ 11 de Septiembre, 2, Lleida");
        restauranteList2.get(2).setLat(41.619997);
        restauranteList2.get(2).setLon(0.637286);

        restauranteList2.get(3).setDescripcion("Una marisquería única en Lleida  con unas vistas unicas y justo enfrente de la Seu vella, VICALB ofrece en su restaurante, desde 1944, los mejores mariscos de Galicia en Lleida en un ambiente de auténtica marisquería en pleno barrio de la Barceloneta. ");
        restauranteList2.get(3).setHorario("El horario es de Martes a Domingo de 12h a 17h");
        restauranteList2.get(3).setLocalización("C/ Sant Marti, 7, Lleida ");
        restauranteList2.get(3).setLat(41.608376);
        restauranteList2.get(3).setLon(0.625571);



        createList();

    }*/

    /*public void crearListaPlatos(){

        for (Restaurante restaurante : restauranteList2
                ) {

            platoList.add(new Plato(
                    getString(R.string.gyozas),
                    "imagen",
                    getString(R.string.infGyozas),
                    getString(R.string.precioGyo),
                    restaurante.getId()
            ));

            platoList.add(new Plato(
                    getString(R.string.yakisoba),
                    "imagen",
                    getString(R.string.infYakisoba),
                    getString(R.string.precioYaki),
                    restaurante.getId()
            ));

            platoList.add(new Plato(
                    getString(R.string.sushi),
                    "imagen",
                    getString(R.string.infSushi),
                    getString(R.string.precioSushi),
                    restaurante.getId()
            ));

            platoList.add(new Plato(
                    getString(R.string.mochi),
                    "imagen",
                    getString(R.string.infMochi),
                    getString(R.string.precioMochi),
                    restaurante.getId()
            ));

        }


    }*/


}
