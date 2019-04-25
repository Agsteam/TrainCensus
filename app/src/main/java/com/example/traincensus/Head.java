package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Head extends AppCompatActivity {
    Button bt,bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        bt=findViewById(R.id.port);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Head.this,Report.class);
                startActivity(intent);
            }
        });
        bt1=findViewById(R.id.Viewre);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Head.this,ViewDivisions.class);
                startActivity(intent);
            }
        });

    }
}
