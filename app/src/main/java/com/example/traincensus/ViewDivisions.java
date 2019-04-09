package com.example.traincensus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static com.example.traincensus.Splashscreen.mpref;

public class ViewDivisions extends AppCompatActivity {
    Spinner spinner1;
    Button btn;
    List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_divisions);
        spinner1=findViewById(R.id.spinner123);
        btn=findViewById(R.id.button);
        list.add("CHENNAI");
        list.add("TRICHY");
        list.add("MADURAI");
        list.add("PALAKKAD");
        list.add("SALEM");
        list.add("TRIVANDRUM");
        ArrayAdapter<String> spin=new ArrayAdapter<>(ViewDivisions.this,android.R.layout.simple_spinner_item,list);
        spinner1.setAdapter(spin);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String div=parent.getItemAtPosition(position).toString();
                SharedPreferences.Editor editor=mpref.edit();
                editor.remove("Division");
                editor.putString("Division",div);
                editor.apply();
                btn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewDivisions.this,Homepage.class);
                startActivity(intent);
            }
        });

    }
}
