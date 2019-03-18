package com.example.traincensus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



import java.util.Calendar;


public class Divassign extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView date, shiftin, shiftout;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Spinner spinsec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divassign);
        date = findViewById(R.id.dateview);
        shiftin = findViewById(R.id.shiftinview);
        shiftout = findViewById(R.id.shiftoutview);
        spinsec=findViewById(R.id.Secname);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                final int month = calendar.get(calendar.MONTH);
                final int day = calendar.get(calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Divassign.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(day + "-" + (month) + "-" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        shiftin.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(calendar.HOUR);
            int minute = calendar.get(calendar.MINUTE);

            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String am_pm;
                        if (i < 12) {
                            am_pm = "AM";
                            shiftin.setText(i + " : " + i1 + " " + am_pm);
                        } else if (i == 12) {
                            am_pm = "PM";
                            {
                                shiftin.setText(i + " :" + i1 + " " + am_pm);
                            }
                        } else {
                            am_pm = "PM";
                            shiftin.setText(i + " :" + i1 + " " + am_pm);
                        }

                    }
                }, hour, minute, true);
                timePickerDialog.show();

            }
        });


        shiftout.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(calendar.HOUR);
            int minute = calendar.get(calendar.MINUTE);

            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String am_pm;
                        if (i < 12) {
                            am_pm = "AM";
                            shiftout.setText(i + " : " + i1 + " " + am_pm);
                        } else if (i == 12) {
                            am_pm = "PM";
                            {
                                shiftout.setText(i + " :" + i1 + " " + am_pm);
                            }
                        } else {
                            am_pm = "PM";
                            shiftout.setText(i + " :" + i1 + " " + am_pm);
                        }

                    }
                }, hour, minute, true);
                timePickerDialog.show();

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Divassign.this, Viewcensusofficer.class);
                startActivity(intent);



            }


        });




    Spinner spinsec = findViewById(R.id.Secname);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.hu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinsec.setAdapter(adapter);
        spinsec.setOnItemSelectedListener(this);
}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    }





