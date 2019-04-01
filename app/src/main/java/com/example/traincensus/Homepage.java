package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity
{
    Button btnn,btnu,btnv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        btnn=(Button)findViewById(R.id.Nomi);
        btnn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Homepage.this,Divassign.class);
                startActivity(intent);
            }
        });

        btnu=(Button)findViewById(R.id.update);
        btnu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               Intent intent=new Intent(Homepage.this,Report.class);
               startActivity(intent);
            }
        });

        btnv=(Button)findViewById(R.id.Viewre);
        btnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this,ViewOptionActivity.class);
                startActivity(intent);
            }
        });
    }
}