package com.google.vicalb.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.vicalb.R;
import com.google.vicalb.models.User;

public class LoginActivity extends AppCompatActivity {

    TextView tvEmail;
    TextView tvPassword;
    TextView tvRegis;

    Button bntEntrar;

    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";


    CheckBox remb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);


        init();



        bntEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvPassword.getText().toString().equals("") || tvEmail.getText().toString().equals("")){
                    Toast.makeText(getApplication(), "Es necesario rellenar todos los campos!", Toast.LENGTH_SHORT).show();
                } else {
                    if(tvPassword.getText().toString().equals(User.pass) && tvEmail.getText().toString().equals(User.user)){
                        go();
                    }
                }
            }
        });

    }

    private void go(){
        Intent i = new Intent(getApplication(), MainActivity.class);
        startActivity(i);
        finish();
    }

    private void init(){
        //mAuth = FirebaseAuth.getInstance();

        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);
        tvRegis = findViewById(R.id.tvRegis);
        bntEntrar = findViewById(R.id.btnEntrar);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        remb = findViewById(R.id.remember);

        check();
    }

    private void check(){
        if(sharedpreferences != null) {
            String text = sharedpreferences.getString(Email, "");
            if(!text.equals("")) {
                remb.setChecked(true);
                tvEmail.setText(text);
            }
        }

    }

    public void regis(View v){
        /*Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);*/
    }
}
