package com.example.traincensus;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;



import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.Calendar;
import java.util.Locale;


public class Welcomescreen extends AppCompatActivity
{       ProgressBar p;
        TextView ed;
        String formattedDate;
        String name2,actype2,sta,div="",sec,shin,shout,m;
        Date dutydate ;
        int SPLASH_TIME = 3000;
        Date cudate,validate1,v,v1;
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
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                formattedDate = df.format(cudate);
                SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
                try
                {
                        cudate=dateFormat.parse(formattedDate);
                }
                catch(Exception e)
                {
                }
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);
                v= cal.getTime();
                SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
                formattedDate = df1.format(v);
                SimpleDateFormat dateFormat1= new SimpleDateFormat("dd/MM/yyyy");
                try {  v=dateFormat.parse(formattedDate); }
                catch(Exception e) {}
                Calendar cal1 = Calendar.getInstance();
                cal1.add(Calendar.DATE, +1);
                v1= cal1.getTime();
                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
                formattedDate = df2.format(v1);
                SimpleDateFormat dateFormat2= new SimpleDateFormat("dd/MM/yyyy");
                try {  v1=dateFormat2.parse(formattedDate); }
                catch(Exception e) {}
                dutydate=v1;
                p=(ProgressBar)findViewById(R.id.progressBar);
                final boolean b = new Handler().postDelayed(new Runnable()
                {
                        @Override
                        public void run()
                        {
                                if (actype2.equals("field"))
                                {
                                        database1.collection("Login").addSnapshotListener(new EventListener<QuerySnapshot>()
                                        {
                                                @Override
                                                public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e)
                                                {
                                                        if (e != null)
                                                        {
                                                                Log.d("sun", "error" + e.getMessage());
                                                        }


else
                                                        {
                                                                for (DocumentSnapshot doc : queryDocumentSnapshots)
                                                                {
                                                                        String pfvali = doc.getString("pfno");
                                                                        String d = doc.getString("dob");
                                                                        validate1 = doc.getDate("date1");
                                                                        m=doc.getString("shiftout");
                                                                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                                                        String formattedDate1 = df.format(validate1);
                                                                        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
                                                                        try {
                                                                                validate1=dateFormat.parse(formattedDate1);
                                                                        }
                                                                        catch(Exception e1) {
                                                                        }
                                                                        if ((pfvali.equals(Splashscreen.mpref.getString("Username", ""))) && (d.equals(Splashscreen.mpref.getString("Password", ""))))
                                                                        {
                                                                                if (((cudate.before(validate1))||(cudate.equals(validate1)))&&((dutydate.after(validate1))||(dutydate.equals(validate1))))
                                                                                {
                                                                                        sec = doc.getString("section");
                                                                                        sta = doc.getString("station");
                                                                                        shin = doc.getString("shiftin");
                                                                                        shout = doc.getString("shiftout");
                                                                                        div = doc.getString("division");
                                                                                        dutydate=doc.getDate("date1");
                                                                                }
                                                                                else if((v.equals(validate1))&&(m.equals("7:00AM"))) {
                                                                                        sec = doc.getString("section");
                                                                                        sta = doc.getString("station");
                                                                                        shin = doc.getString("shiftin");
                                                                                        shout = doc.getString("shiftout");
                                                                                        div = doc.getString("division");
                                                                                        dutydate = doc.getDate("date1");
                                                                                }
                                                                        }
                                                                }
                                                                if(!div.equals(""))


                                                                {
                                                                        Intent mySuperIntent = new Intent(Welcomescreen.this, Fumessage.class);
                                                                        mySuperIntent.putExtra("div", div);
                                                                        mySuperIntent.putExtra("sec", sec);
                                                                        mySuperIntent.putExtra("sta", sta);
                                                                        mySuperIntent.putExtra("shin", shin);
                                                                        mySuperIntent.putExtra("shout", shout);
                                                                        String pattern = "dd/MM/yyyy";
                                                                        DateFormat df1 = new SimpleDateFormat(pattern);
                                                                        String todayAsString = df1.format(cudate);
                                                                        mySuperIntent.putExtra("cudate", todayAsString);
                                                                        String pattern1 = "dd/MM/yyyy";
                                                                        DateFormat df2 = new SimpleDateFormat(pattern1);
                                                                        String todayAsString1 = df2.format(dutydate);
                                                                        mySuperIntent.putExtra("dutydate", todayAsString1);
                                                                        startActivity(mySuperIntent);
                                                                        finish();
                                                                }
                                                                else if(div.equals("")) {
                                                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Welcomescreen.this);
                                                                alertDialogBuilder.setTitle("Information!");
                                                                alertDialogBuilder.setMessage("You Don't have Duty for Coming Days.").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int id) {
                                                                                finish();
                                                                        }
                                                                });
                                                                AlertDialog alertDialog = alertDialogBuilder.create();
                                                                alertDialog.show();
                                                        }
                                                                p.setVisibility(View.GONE);
                                                        }
                                                }


                                        });
                                }
                                else if (actype2.equals("Div"))
                                {
                                        Intent mySuperIntent = new Intent(Welcomescreen.this, Divassign.class);
                                        startActivity(mySuperIntent);
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