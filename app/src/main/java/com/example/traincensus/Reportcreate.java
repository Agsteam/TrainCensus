package com.example.traincensus;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
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
    TextView tr,fr,to,shper;
    float total=0,occ=0;
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
        tr=findViewById(R.id.nameview);
        fr=findViewById(R.id.view);
        to=findViewById(R.id.textView18);
        shper=findViewById(R.id.perview);
        fr.setText(dt.get(0));
        if(dt.size()>1)
            to.setText(dt.get(dt.size()-1));
        else
            to.setText(dt.get(0));
        tr.setText(getIntent().getStringExtra("tra"));
        mAdapter = new Reportclass(this, st,dt,tav,j,per);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int q=0;q<per.size();q++) {
            total = total + tav.get(q);
            occ = occ + j.get(q);
        }
        total=Math.round((total/occ)*100);
        shper.setText(""+total);
    }
    public  void onBackPressed()
    {
        if(Splashscreen.mpref.getString("Name", "").equals("HEAD QUARTERS")&&Splashscreen.mpref.getString("Division", "").equals("HEAD"))
        {
            Intent intent=new Intent(Reportcreate.this,Report.class);
            startActivity(intent);
            finish();
        }
        else if(Splashscreen.mpref.getString("Name", "").equals("HEAD QUARTERS")&&(!Splashscreen.mpref.getString("Division", "").equals("HEAD")))
        {
            Intent intent=new Intent(Reportcreate.this,Repdiv.class);
            startActivity(intent);
            finish();
        }
        else if(Splashscreen.mpref.getString("Name", "").equals("HEAD QUARTERS"))
        {
            Intent intent=new Intent(Reportcreate.this,Report.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(Reportcreate.this, Repdiv.class);
            startActivity(intent);
            finish();
        }
    }
}