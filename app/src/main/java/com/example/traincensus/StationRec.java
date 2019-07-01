package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class StationRec extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Stationrepfield mAdapter;
    ArrayList<String> st ;
    ArrayList<String> dt;
    ArrayList<Integer> tav ;
    ArrayList<Integer> j ;
    ArrayList<Integer> per;
    TextView tr,fr,to,shper;
    float total=0,occ=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_rec);
        st =getIntent().getStringArrayListExtra("st");
        j= getIntent().getIntegerArrayListExtra("j");
        per =getIntent().getIntegerArrayListExtra("per");
        mRecyclerView = findViewById(R.id.staffrec1);
        tr=findViewById(R.id.textView7);
        tr.setText(getIntent().getStringExtra("tra"));
        mAdapter = new Stationrepfield(this, st,j,per);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public  void onBackPressed()
    {
           Intent intent=new Intent(StationRec.this,Fhome.class);
            startActivity(intent);
            finish();
    }

}
