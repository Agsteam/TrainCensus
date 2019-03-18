package com.example.traincensus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Welcomescreen extends AppCompatActivity
{
        TextView ed, clic;
        public static String name2,actype2,sta,div,sec1="";
        String sec="";
        public static Date shin,shout;
        int SPLASH_TIME = 3000,temp=0;
        Date cudate,validate1;
        FirebaseFirestore database1=FirebaseFirestore.getInstance();
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_welcomescreen);
                ed=(TextView) findViewById(R.id.name);
                name2=Splashscreen.mpref.getString("Name","");
                ed.setText(name2);
                actype2=Splashscreen.mpref.getString("Accesstype","");
                cudate = Calendar.getInstance().getTime();
                validate1=Calendar.getInstance().getTime();
                final boolean b = new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                if (actype2.equals("field")) {
                                        database1.collection("Login").addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                @Override
                                                public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                                                        if (e != null) {
                                                                Log.d("sun", "error" + e.getMessage());
                                                        } else {
                                                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                                                        String pfvali = doc.getString("Pfno");
                                                                        String d = doc.getString("DOB");
                                                                        validate1 = doc.getDate("Shift in");
                                                                        div = doc.getString("Division");
                                                                        if ((pfvali.equals(Splashscreen.mpref.getString("Username", ""))) && (d.equals(Splashscreen.mpref.getString("Password", "")))) {
                                                                                int z;

                                                                                if (validate1.before(cudate) && (temp == 0)) {
                                                                                        sec = doc.getString("Section");
                                                                                        sta = doc.getString("Station");
                                                                                        shin = doc.getDate("Shift in");
                                                                                        shout = doc.getDate("Shift out");
                                                                                        temp = 1;
                                                                                        Intent mySuperIntent = new Intent(Welcomescreen.this, Fumessage.class);
                                                                                        startActivity(mySuperIntent);
                                                                                        finish();
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        });
                                } else if (actype2.equals("Div")) {
                                        Intent mySuperIntent = new Intent(Welcomescreen.this, Divassign.class);
                                        startActivity(mySuperIntent);
                                        finish();
                                }
                        }
                }, SPLASH_TIME);
        }
}