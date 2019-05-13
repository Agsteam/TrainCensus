package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Fumessage extends AppCompatActivity {
    TextView div,sec,sta,date,shin5,shout5;
    Button count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fumessage);
        count=findViewById(R.id.nextid);
        div=findViewById(R.id.divview);
        sec=findViewById(R.id.secview);
        sta=findViewById(R.id.staview);
        date=findViewById(R.id.dateview);
        shin5=findViewById(R.id.shiftinview);
        shout5=findViewById(R.id.shiftoutview);
        div.setText(getIntent().getStringExtra("div").toUpperCase());
        sec.setText(getIntent().getStringExtra("sec"));
        sta.setText(getIntent().getStringExtra("sta").toUpperCase());
        date.setText(getIntent().getStringExtra("dutydate"));
        shin5.setText(getIntent().getStringExtra("shin"));
        shout5.setText(getIntent().getStringExtra("shout"));
        if(getIntent().getStringExtra("cudate").equals(getIntent().getStringExtra("dutydate")))
            count.setVisibility(View.VISIBLE);
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fumessage.this,Trainprofile.class);
                intent.putExtra("div",getIntent().getStringExtra("div"));
                intent.putExtra("sta",getIntent().getStringExtra("sta"));
                intent.putExtra("dutydate",getIntent().getStringExtra("dutydate"));
                intent.putExtra("sec",sec.getText().toString());
                startActivity(intent);
                finish();
            }
        });
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

    @Override
    public void onBackPressed () {

Intent intent=new Intent(Fumessage.this,Fhome.class);
startActivity(intent);
finish();


    }

}