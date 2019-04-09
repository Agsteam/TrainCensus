package com.example.traincensus;


import android.annotation.SuppressLint;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.Calendar;
import java.util.Objects;


public class Welcomescreen extends AppCompatActivity
{       ProgressBar p;
        TextView ed,op,di;
        String formattedDate;
        String name2,actype2,sta,div="",sec,shin,shout,m,validate2;
        Date dutydate ;
        int SPLASH_TIME = 3000;
        Date cudate,validate1,v,v1;
        FirebaseFirestore database1=FirebaseFirestore.getInstance();
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
                cudate = Calendar.getInstance().getTime();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                formattedDate = df.format(cudate);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
                try
                {
                        cudate=dateFormat.parse(formattedDate);
                }
                catch(Exception e)
                {e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);
                v= cal.getTime();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
                formattedDate = df1.format(v);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat1= new SimpleDateFormat("dd/MM/yyyy");
                try {  v=dateFormat1.parse(formattedDate); }
                catch(Exception e) { e.printStackTrace();}
                Calendar cal1 = Calendar.getInstance();
                cal1.add(Calendar.DATE, +180);
                v1= cal1.getTime();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
                formattedDate = df2.format(v1);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat2= new SimpleDateFormat("dd/MM/yyyy");
                try {  v1=dateFormat2.parse(formattedDate); }
                catch(Exception e) {e.printStackTrace();}
                dutydate=v1;
                p=findViewById(R.id.progressBar);
                new Handler().postDelayed(new Runnable()
                {
                        @Override
                        public void run()
                        {
                                switch (actype2) {
                                        case "field":

                                                database1.collection("Login").addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                                                                if (e != null) {
                                                                        Log.d("sun", "error" + e.getMessage());
                                                                } else {
                                                                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                                                                String pfvali = Objects.requireNonNull(doc.getString("pfno")).toUpperCase();
                                                                                String d = Objects.requireNonNull(doc.getString("dob")).toUpperCase();
                                                                                validate2 = doc.getString("date1");
                                                                                @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                                                                try {
                                                                                        validate1 = format.parse(validate2);
                                                                                } catch (ParseException e1) {
                                                                                        e1.printStackTrace();
                                                                                }
                                                                                m = doc.getString("shiftout");
                                                                                if ((pfvali.equals(Splashscreen.mpref.getString("Username", ""))) && (d.equals(Splashscreen.mpref.getString("Password", "")))) {
                                                                                        if (((cudate.before(validate1)) || (cudate.equals(validate1))) && ((dutydate.after(validate1)) || (dutydate.equals(validate1)))) {
                                                                                                sec = doc.getString("section");
                                                                                                sta = doc.getString("station");
                                                                                                shin = doc.getString("shiftin");
                                                                                                shout = doc.getString("shiftout");
                                                                                                div = doc.getString("division");
                                                                                                String te = doc.getString("date1");
                                                                                                @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                                                                                                try {
                                                                                                        dutydate = format1.parse(te);
                                                                                                } catch (ParseException e1) {
                                                                                                        e1.printStackTrace();
                                                                                                }
                                                                                        } else if ((v.equals(validate1)) && (m.equals("7:00AM"))) {
                                                                                                sec = doc.getString("section");
                                                                                                sta = doc.getString("station");
                                                                                                shin = doc.getString("shiftin");
                                                                                                shout = doc.getString("shiftout");
                                                                                                div = doc.getString("division");
                                                                                                String te = doc.getString("date1");
                                                                                                @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                                                                                                try {
                                                                                                        dutydate = format1.parse(te);
                                                                                                } catch (ParseException e1) {
                                                                                                        e1.printStackTrace();
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                        if (!div.equals("")) {
                                                                                Intent mySuperIntent = new Intent(Welcomescreen.this, Fumessage.class);
                                                                                mySuperIntent.putExtra("div", div);
                                                                                mySuperIntent.putExtra("sec", sec);
                                                                                mySuperIntent.putExtra("sta", sta);
                                                                                mySuperIntent.putExtra("shin", shin);
                                                                                mySuperIntent.putExtra("shout", shout);
                                                                                String pattern = "dd/MM/yyyy";
                                                                                @SuppressLint("SimpleDateFormat") DateFormat df1 = new SimpleDateFormat(pattern);
                                                                                String todayAsString = df1.format(cudate);
                                                                                mySuperIntent.putExtra("cudate", todayAsString);
                                                                                String pattern1 = "dd/MM/yyyy";
                                                                                @SuppressLint("SimpleDateFormat") DateFormat df2 = new SimpleDateFormat(pattern1);
                                                                                String todayAsString1 = df2.format(dutydate);
                                                                                mySuperIntent.putExtra("dutydate", todayAsString1);
                                                                                startActivity(mySuperIntent);
                                                                                finish();
                                                                        } else if (div.equals("")) {
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