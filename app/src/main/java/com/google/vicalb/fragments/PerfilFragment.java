package com.google.vicalb.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.vicalb.R;
import com.google.vicalb.activities.FirebaseUIActivity;
import com.google.vicalb.models.User;


public class PerfilFragment extends Fragment {

    private FirebaseAuth mAuth;

    TextView tv;
    TextView btnC;
    TextView email;
    TextView name;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_perfil, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        setInit();

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrar();
            }
        });


    }

    private void setInit() {
        tv.setText(R.string.profile);
        email.setText(User.email);
        name.setText(User.name);
    }

    private void init(View view){

        email = view.findViewById(R.id.tvEmailP);
        name = view.findViewById(R.id.tvNameP);
        tv = getActivity().findViewById(R.id.title);
        btnC = view.findViewById(R.id.tvSurt);

    }

    public void cerrar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.cerrarS))
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        signOut();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Intent a = new Intent(getContext(), FirebaseUIActivity.class);
                        startActivity(a);
                        getActivity().finish();
                    }
                });
        // [END auth_fui_signout]
    }
}
