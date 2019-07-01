package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Trainprofile extends AppCompatActivity {
    EditText edit_trainnumber,edit_noofcoach;
    Button btn_gocoach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_profile);
        edit_trainnumber=findViewById(R.id.edit_trainnumber);
        edit_noofcoach=findViewById(R.id.edit_noofcoach);
        btn_gocoach=findViewById(R.id.but_gocoach);
        btn_gocoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!edit_noofcoach.getText().toString().isEmpty())&&(Integer.parseInt(edit_noofcoach.getText().toString())<29))
                {
                    if((!edit_trainnumber.getText().toString().isEmpty())&&(edit_trainnumber.getText().toString().trim().length()<6)&&(edit_trainnumber.getText().toString().trim().length()==5))
                    {
                        Intent intent = new Intent(Trainprofile.this, CoachNumber.class);
                        intent.putExtra("div", getIntent().getStringExtra("div"));
                        intent.putExtra("dutydate", getIntent().getStringExtra("dutydate"));
                        intent.putExtra("sta", getIntent().getStringExtra("sta"));
                        intent.putExtra("sec", getIntent().getStringExtra("sec"));
                        intent.putExtra("edit_noofcoach", Integer.parseInt(edit_noofcoach.getText().toString()));
                        intent.putExtra("edit_trainnumber", Integer.parseInt(edit_trainnumber.getText().toString()));
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(Trainprofile.this,"Please enter Train Number",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Trainprofile.this,"Please enter Number of coach",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onBackPressed ()
    {
        Intent intent=new Intent(Trainprofile.this,Fhome.class);
        startActivity(intent);
        finish();
    }
}
