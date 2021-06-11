package com.google.vicalb.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.vicalb.R;


public class SplashActivity extends AppCompatActivity {

    AsynTaskRunner runner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        runner = new AsynTaskRunner();
        runner.execute();
    }


    class AsynTaskRunner extends AsyncTask<String, String, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            //Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            Intent i = new Intent(SplashActivity.this, FirebaseUIActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        runner.cancel(true);
    }



}
