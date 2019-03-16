package com.example.traincensus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

public class Welcomescreen extends AppCompatActivity
{
        TextView ed;
        public  String name2,div2,sta2,actype2,sec2,sinin2,sinout2,lable2;
        Date sinin1,sinout1;
        int SPLASH_TIME = 1000; //This is 4 seconds
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);
        ed=(TextView) findViewById(R.id.name);
        name2= getIntent().getStringExtra("name1");
        div2=getIntent().getStringExtra("div1");
        sta2=getIntent().getStringExtra("Sta1");
        actype2=getIntent().getStringExtra("acsty");
        sec2=getIntent().getStringExtra("sec1");
        sinin2=getIntent().getStringExtra("sinin");
        sinout2=getIntent().getStringExtra("sinout");
        lable2=getIntent().getStringExtra("lable");
        Log.e("sun:",""+sinin2+sinout2+div2+sta2+actype2+sec2);
        ed.setText(name2);
        new Handler().postDelayed(new Runnable()
        {
                @Override
                public void run()
                {
                        if(actype2.equals("field")) {
                                Intent mySuperIntent = new Intent(Welcomescreen.this, Fumessage.class);
                                mySuperIntent.putExtra("name1",name2);
                                mySuperIntent.putExtra("div1",div2);
                                mySuperIntent.putExtra("Sta1",sta2);
                                mySuperIntent.putExtra("acsty",actype2);
                                mySuperIntent.putExtra("sec1",sec2);
                                mySuperIntent.putExtra("sinin",sinin2);
                                mySuperIntent.putExtra("sinout",sinout2);
                                startActivity(mySuperIntent);
                                finish();
                        }
                        else if(actype2.equals("Div"))
                         {
                                Intent mySuperIntent = new Intent(Welcomescreen.this, Divassign.class);
                                mySuperIntent.putExtra("div1",div2);
                                startActivity(mySuperIntent);
                                finish();
                        }

                }
        }, SPLASH_TIME);
        }
}
