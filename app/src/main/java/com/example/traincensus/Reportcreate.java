package com.example.traincensus;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

public class Reportcreate extends AppCompatActivity
{
    RecyclerView mRecyclerView;
    Reportclass mAdapter;
    ArrayList<String> st ;
    ArrayList<String> dt;
    ArrayList<Integer> tav ;
    ArrayList<Integer> j ;
    ArrayList<Integer> per;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportcreate);
        st =getIntent().getStringArrayListExtra("st");
        dt = getIntent().getStringArrayListExtra("dt");
        tav =getIntent().getIntegerArrayListExtra("tav");
        j= getIntent().getIntegerArrayListExtra("j");
        per =getIntent().getIntegerArrayListExtra("per");
        mRecyclerView = findViewById(R.id.re);
        mAdapter = new Reportclass(this, st,dt,tav,j,per);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
