package com.example.traincensus;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;

import java.util.Objects;

public class Splashscreen extends AppCompatActivity {

    public static SharedPreferences mpref;
    public static final String Pref_Name="sp_name";
    int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mpref=getSharedPreferences(Pref_Name,MODE_PRIVATE);
        if((Objects.requireNonNull(mpref.getString("Username", "")).equals(""))&&(Objects.requireNonNull(mpref.getString("Password", "")).equals("")))
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    Intent mySuperIntent = new Intent(Splashscreen.this, Mainscreen.class);
                    startActivity(mySuperIntent);
                    finish();
                }
            }, SPLASH_TIME);
        }
        else
        {
            Intent mySuperIntent = new Intent(Splashscreen.this, Welcomescreen.class);
            startActivity(mySuperIntent);
            finish();
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
