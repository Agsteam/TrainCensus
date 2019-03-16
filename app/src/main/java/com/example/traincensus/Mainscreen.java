package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Mainscreen extends AppCompatActivity {

    Button btnfo,btndiv,btnhq;
    String loginlable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mainscreen);
            btnhq=(Button)findViewById(R.id.hq);
            btnhq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Page under construction",Toast.LENGTH_LONG).show();
                }


            });
            btndiv=(Button)findViewById(R.id.div);
            btndiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginlable="Divisional Admin";
                    openLogin();
                }
            });
            btnfo= (Button) findViewById(R.id.fo);
            btnfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginlable="Census Officer";
                    openLogin();
                }
            });
        }
        public void openLogin()
        {
            Intent intent = new Intent(Mainscreen.this, Login.class);
            intent.putExtra("lable",loginlable);
            startActivity(intent);
        }
    }



