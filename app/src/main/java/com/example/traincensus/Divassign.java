package com.example.traincensus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Divassign extends AppCompatActivity
{
    List<String> list;
    TextView date, shiftin, shiftout,div;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Spinner spinsec;
    FirebaseFirestore database1=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divassign);
        date = findViewById(R.id.dateview);
        shiftin = findViewById(R.id.shiftinview);
        shiftout = findViewById(R.id.shiftoutview);
        spinsec=findViewById(R.id.Secname);
        div=findViewById(R.id.divnameview);
        div.setText(Splashscreen.mpref.getString("Division",""));
        database1.collection(Splashscreen.mpref.getString("Division","").toLowerCase()).addSnapshotListener(new EventListener<QuerySnapshot>()
        {
                @Override
                public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e)
                {
                    list = new ArrayList<String>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots)
                    {
                        String pfvali = doc.getString("section");
                        list.add(pfvali);
                        Log.e("sun", pfvali);
                    }
                    Divassign.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Divassign.this, android.R.layout.simple_spinner_item, list);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinsec.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                            String provider = spinsec.getSelectedItem().toString();
                            Log.e("sun2",provider);
                        }
                    });
                }
        });
        date.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                final int month = calendar.get(calendar.MONTH);
                final int day = calendar.get(calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Divassign.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        date.setText(day + "-" + (month) + "-" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        shiftin.setOnClickListener(new View.OnClickListener()
        {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(calendar.HOUR);
            int minute = calendar.get(calendar.MINUTE);
            @Override
            public void onClick(View v)
            {
                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1)
                    {
                        String am_pm;
                        if (i < 12)
                        {
                            am_pm = "AM";
                            shiftin.setText(i + " : " + i1 + " " + am_pm);
                        }
                        else if (i == 12)
                        {
                            am_pm = "PM";
                            {
                                shiftin.setText(i + " :" + i1 + " " + am_pm);
                            }
                        }
                        else
                        {
                            am_pm = "PM";
                            shiftin.setText(i + " :" + i1 + " " + am_pm);
                        }
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
        shiftout.setOnClickListener(new View.OnClickListener()
        {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(calendar.HOUR);
            int minute = calendar.get(calendar.MINUTE);
            @Override
            public void onClick(View v)
            {
                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1)
                    {
                        String am_pm;
                        if (i < 12)
                        {
                            am_pm = "AM";
                            shiftout.setText(i + " : " + i1 + " " + am_pm);
                        }
                        else if (i == 12)
                        {
                            am_pm = "PM";
                            {
                                shiftout.setText(i + " :" + i1 + " " + am_pm);
                            }
                        }
                        else
                            {
                            am_pm = "PM";
                            shiftout.setText(i + " :" + i1 + " " + am_pm);
                            }
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Divassign.this, Viewcensusofficer.class);
                startActivity(intent);
            }
        });
        spinsec.setOnItemClickListener(new );
    }
}