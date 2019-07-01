package com.example.traincensus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Date;
public class Welcomescreen extends AppCompatActivity
{       ProgressBar p;
        TextView ed,op,di;
        String name2,actype2;
        int SPLASH_TIME = 3000;
        Date v;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_welcomescreen);
                actype2=Splashscreen.mpref.getString("Accesstype","");
                ed= findViewById(R.id.name);
                op=findViewById(R.id.textView4);
                di=findViewById(R.id.name1);
                if(actype2.equals("Div"))
                {        di.setVisibility(View.VISIBLE);
                        op.setText(getString(R.string.wopen));
                        di.setText(getString(R.string.wdiv));
                }
                if(actype2.equals("HQ"))
                {        di.setVisibility(View.VISIBLE);
                        op.setText(getString(R.string.wopen));
                        di.setText(getString(R.string.whq));
                }
                name2=Splashscreen.mpref.getString("Name","");
                ed.setText(name2);
                p=findViewById(R.id.progressBar);
                new Handler().postDelayed(new Runnable()
                {
                        @Override
                        public void run()
                        {
                                switch (actype2) {
                                        case "field":
                                                Intent mySuperIntent2 = new Intent(Welcomescreen.this, Fhome.class);
                                                startActivity(mySuperIntent2);
                                                finish();
                                                break;
                                        case "Div":

                                                Intent mySuperIntent = new Intent(Welcomescreen.this, Homepage.class);
                                                startActivity(mySuperIntent);
                                                finish();
                                                break;

                                        case "HQ":

                                                Intent mySuperIntent1 = new Intent(Welcomescreen.this, Head.class);
                                                startActivity(mySuperIntent1);
                                                finish();
                                }
                        }
                }, SPLASH_TIME);
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