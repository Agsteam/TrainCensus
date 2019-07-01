package com.example.traincensus;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import static com.example.traincensus.Splashscreen.mpref;

public class Fhome extends AppCompatActivity {
    Button vdt,cou,bt2,rep;
    String formattedDate;
    TextView tv;
    String sta,div="",sec,shin,shout,m,validate2;
    Date dutydate ;
    Date cudate,validate1,v2,v1;
    ArrayList<String> tav = new ArrayList<>();
    ArrayList<Integer> j = new ArrayList<>();
    ArrayList<Integer> per = new ArrayList<>();
    FirebaseFirestore database1=FirebaseFirestore.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("field");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fhome);
        vdt=findViewById(R.id.Viewdu);
        tv = findViewById(R.id.mark);
        tv.setSelected(true);
        tv.setSingleLine();
        cou=findViewById(R.id.count);
        bt2=findViewById(R.id.logw);
        rep=findViewById(R.id.rep);
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
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, +180);
        v1= cal1.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = df2.format(v1);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat2= new SimpleDateFormat("dd/MM/yyyy");
        try {  v1=dateFormat2.parse(formattedDate); }
        catch(Exception e) {e.printStackTrace();}
        dutydate=v1;
    }
    @Override
    public void onBackPressed () {
        AlertDialog.Builder a = new AlertDialog.Builder(new ContextThemeWrapper(Fhome.this, R.style.MyAlertDialogStyle));
        a.setMessage("Are you sure you want to exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a.create();
        alert.setTitle("Alert !!!");
        alert.show();
    }
    public void fusun(View view)
    {
        Button button = findViewById(R.id.Viewdu);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Intent intent =new Intent(Fhome.this,Viewcensusofficer.class);
        intent.putExtra("fla",4);
        startActivity(intent);
        finish();
    }
    public void fusun1(View view) {
        Button button = findViewById(R.id.count);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        v2 = cal.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = df1.format(v2);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            v2 = dateFormat1.parse(formattedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        database1.collection("Login").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("sun", "error" + e.getMessage());
                } else {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        String pfvali = doc.getString("pfno").toUpperCase();
                        String d = doc.getString("dob").toUpperCase();
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
                            } else if ((v2.equals(validate1)) && (m.equals("7:00AM"))) {
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
                        Intent mySuperIntent = new Intent(Fhome.this, Fumessage.class);
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
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fhome.this);
                        alertDialogBuilder.setTitle("Information!");
                        alertDialogBuilder.setMessage("You Don't have Duty for Coming Days.").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
            }
        });
    }
    public void fusun2(View view)
    {
        Button button = findViewById(R.id.logw);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        AlertDialog.Builder a = new AlertDialog.Builder(new ContextThemeWrapper(Fhome.this, R.style.MyAlertDialogStyle));
        a.setMessage("Are you sure you want to Logout ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Fhome.this, Mainscreen.class);
                        startActivity(intent);
                        finish();
                        SharedPreferences.Editor editor = mpref.edit();
                        editor.clear();
                        editor.apply();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a.create();
        alert.setTitle("Alert!!!");
        alert.show();
    }
    public void fusun4(View view)
    {
        Button button = findViewById(R.id.rep);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        v2 = cal.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = df1.format(v2);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            v2 = dateFormat1.parse(formattedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        database1.collection("Login").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("sun", "error" + e.getMessage());
                } else {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        String pfvali = doc.getString("pfno").toUpperCase();
                        String d = doc.getString("dob").toUpperCase();
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
                            } else if ((v2.equals(validate1)) && (m.equals("7:00AM"))) {
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
                       sun23();
                    } else if (div.equals("")) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fhome.this);
                        alertDialogBuilder.setTitle("Information!");
                        alertDialogBuilder.setMessage("You Don't have Duty today.").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
            }
        });
    }
    public void sun23()
    {
        String pattern = "dd/MM/yyyy";
        @SuppressLint("SimpleDateFormat") DateFormat df1 = new SimpleDateFormat(pattern);
        final String todayAsString = df1.format(cudate);
        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot1: dataSnapshot.getChildren())
                {
                    Adddata1 post1 = postSnapshot1.getValue(Adddata1.class);
                    if ((sta.equals(post1.getStation()))&&(todayAsString.equals(post1.getDudate())))
                    {
                        tav.add(post1.getTrain());
                        j.add(post1.getCc());
                        per.add(post1.getTc());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Log.e("The read failed: " , ""+databaseError.getCode());
            }
        });
        Intent intent=new Intent(Fhome.this,StationRec.class);
        intent.putStringArrayListExtra("st",tav);
        intent.putIntegerArrayListExtra("j",j);
        intent.putIntegerArrayListExtra("per",per);
        intent.putExtra("tra",sta);
        startActivity(intent);
        finish();
    }

}