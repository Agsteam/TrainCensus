package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fumessage extends AppCompatActivity {
     Button count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fumessage);
        count=(Button)findViewById(R.id.nextid);
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fumessage.this,Fucounting.class);
                startActivity(intent);
            }
        });

    }
}
