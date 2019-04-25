package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainscreen extends AppCompatActivity
{

    Button btnfo,btndiv,btnhq;
    String loginlable;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        btnhq=findViewById(R.id.hq);
        btnhq.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginlable="Head Quarters";
                openLogin();
            }
        });
        btndiv=findViewById(R.id.div);
        btndiv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginlable="Division Admin";
                openLogin();
            }
        });
        btnfo=  findViewById(R.id.fo);
        btnfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginlable="Census Officer";
                openLogin();
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
    public void openLogin()
    {
        Intent intent = new Intent(Mainscreen.this, Login.class);
        intent.putExtra("lable",loginlable);
        startActivity(intent);
    }
}


