package com.example.traincensus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.traincensus.Splashscreen.mpref;

public class Fumessage extends AppCompatActivity {
    TextView div,sec,sta,date,shin5,shout5;
    Button count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fumessage);
        count=(Button)findViewById(R.id.nextid);
        div=(TextView)findViewById(R.id.divview);
        sec=(TextView)findViewById(R.id.secview);
        sta=(TextView)findViewById(R.id.staview);
        date=(TextView)findViewById(R.id.dateview);
        shin5=(TextView)findViewById(R.id.shiftinview);
        shout5=(TextView)findViewById(R.id.shiftoutview);
        div.setText(Welcomescreen.div);
        sec.setText(Welcomescreen.sec);
        sta.setText(Welcomescreen.sta);
        String pattern = "dd/MM/yyyy";
        DateFormat df1 = new SimpleDateFormat(pattern);
        String todayAsString = df1.format(Welcomescreen.shin);
        date.setText(todayAsString);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
        Date a=Welcomescreen.shin;
        String pattern1="hh:mm a";
        DateFormat df2=new SimpleDateFormat(pattern1);
        String zq=df2.format(a);
        shin5.setText(zq);
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs1 = new SimpleDateFormat("hh:mm a");
        Date a1=Welcomescreen.shout;
        String pattern2="hh:mm a";
        DateFormat df3=new SimpleDateFormat(pattern2);
        String zq1=df3.format(a1);
        shout5.setText(zq1);
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fumessage.this,Fucounting.class);
                //SharedPreferences.Editor editor=mpref.edit();
                //editor.clear();
                //editor.commit();
                startActivity(intent);
            }
        });

    }
}