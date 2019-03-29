package com.example.traincensus;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.String.valueOf;


public class Divassign extends AppCompatActivity
{
    List<String> list,list1;
    TextView  shiftin, shiftout,div;
    TimePickerDialog timePickerDialog;
    Spinner spinsec,spinsta;
    EditText date11,namestaff,pfnostaff;
    String divSection,divStation;
    FirebaseFirestore database1=FirebaseFirestore.getInstance();
    FirebaseFirestore database2=FirebaseFirestore.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Login");
    public Calendar calendar1=Calendar.getInstance();
    Button divsummit;
    Date date5;
    int flag=0;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divassign);
        date11 = findViewById(R.id.dateview);
        shiftin = findViewById(R.id.shiftinview);
        shiftout = findViewById(R.id.shiftoutview);
        spinsec= findViewById(R.id.Secname);
        spinsta=findViewById(R.id.Stationview);
        div=findViewById(R.id.divnameview);
        divsummit=findViewById(R.id.submit);
        namestaff=findViewById(R.id.nameview);
        pfnostaff=findViewById(R.id.Pfno);
        calendar1.add(Calendar.DATE, 0);
        div.setText(Splashscreen.mpref.getString("Division",""));
        divsummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                flag=0;
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {date5 = format.parse(date11.getText().toString());
                } catch (ParseException e) {e.printStackTrace();}
                if(namestaff.getText().toString().equals(""))
                {flag=1;
                    namestaff.setError(getString(R.string.a));}
                if(pfnostaff.getText().toString().equals(""))
                {flag=1;
                    pfnostaff.setError(getString(R.string.a1));}
                if(date11.getText().toString().equals(""))
                {flag=1;
                    date11.setError(getString(R.string.a2));}
                if(shiftin.getText().toString().equals(""))
                {flag=1;
                    shiftin.setError(getString(R.string.a3));}
                if(shiftout.getText().toString().equals(""))
                {flag=1;
                    shiftout.setError(getString(R.string.a4));}
                if(flag==0) {


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Divassign.this);
                    alertDialogBuilder.setTitle(R.string.a6);
                    alertDialogBuilder
                            .setMessage(show(a))
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int id)
                                {
                                    FirestoreDivision note = new FirestoreDivision(Splashscreen.mpref.getString("Division", ""), namestaff.getText().toString().trim(), pfnostaff.getText().toString().trim(), divStation, shiftin.getText().toString().trim(), shiftout.getText().toString().trim(), pfnostaff.getText().toString().trim(), "field", divSection, date5);
                                    notebookRef.add(note);
                                    Toast.makeText(Divassign.this,"Record Saved Successfully",Toast.LENGTH_LONG).show();
                                    namestaff.setText("");
                                    pfnostaff.setText("");
                                    shiftin.setText(getString(R.string.a7));
                                    shiftout.setText(getString(R.string.a8));
                                    date11.setText(getString(R.string.a9));
                                    Intent intent=new Intent(Divassign.this,Divassign.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int id)
                                {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else
                    Toast.makeText(Divassign.this,getString(R.string.a10),Toast.LENGTH_SHORT).show();
            }
        });
        final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth)
            {
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, monthOfYear);
                calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };
        date11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                date11.setError(null);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Divassign.this, date12,Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();


            }
        });
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
                }
                Divassign.this.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Divassign.this, android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinsec.setAdapter(dataAdapter);
                        dataAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        spinsec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override public void onItemSelected (AdapterView < ? > parentView, View
                    selectedItemView,int position, long id)
            {
                divSection = valueOf(spinsec.getSelectedItem());
                spinsta();
            }
            @Override public void onNothingSelected (AdapterView < ? > adapterView)
            {
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
                shiftin.setError(null);
                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i2)
                    {
                        String am_pm;
                        if (i < 12)
                        {
                            am_pm = "AM";
                            if(i2<10)
                                shiftin.setText(i + ":" +"0"+ i2 + am_pm);
                            else
                                shiftin.setText(i + ":" + i2 + am_pm);
                        }
                        else if (i == 12)
                        {
                            am_pm = "PM";
                            if(i2<10)
                                shiftin.setText(i + ":"+"0" + i2 + am_pm);
                            else
                                shiftin.setText(i + ":" + i2 + am_pm);
                        }
                        else
                        {
                            am_pm = "PM";
                            if(i2<10)
                                shiftin.setText((i=(i%12)) + ":"+"0" + i2 + am_pm);
                            else
                                shiftin.setText((i=(i%12)) + ":" + i2 + am_pm);
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
                shiftout.setError(null);
                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override


                    public void onTimeSet(TimePicker timePicker, int i, int i1)
                    {
                        String am_pm;
                        if (i < 12)
                        {
                            am_pm = "AM";
                            if(i1<10)
                                shiftout.setText(i + ":"+"0" + i1 + am_pm);
                            else
                                shiftout.setText(i + ":" + i1 + am_pm);
                        }
                        else if (i == 12)
                        {
                            am_pm = "PM";
                            if(i1<10)
                                shiftout.setText(i + ":" +"0"+ i1 + am_pm);
                            else
                                shiftout.setText(i + ":" + i1 + am_pm);
                        }
                        else
                        {
                            am_pm = "PM";
                            if(i1<10)
                                shiftout.setText((i=(i%12)) + ":"+"0" + i1 + am_pm);
                            else
                                shiftout.setText((i=(i%12)) + ":" + i1 + am_pm);
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
                finish();

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }
    public void spinsta()
    {
        database2.collection(Splashscreen.mpref.getString("Division","").toLowerCase()).addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e)
            {
                list1 = new ArrayList<String>();
                for (DocumentSnapshot doc1 : queryDocumentSnapshots)
                {
                    String pfvali = doc1.getString("section");
                    if(pfvali.equals(divSection))
                    {
                        list1 = (List<String>) doc1.get("station");
                    }
                }
                Divassign.this.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(Divassign.this, android.R.layout.simple_spinner_item, list1);
                        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinsta.setAdapter(dataAdapter1);
                        dataAdapter1.notifyDataSetChanged();
                    }
                });
            }
        });
        spinsta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                divStation= valueOf(spinsta.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
    }
    private void updateLabel2()
    {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date11.setText(sdf.format(calendar1.getTime()));
    }
    public String show(String a)
    {
        a = "Name :" + namestaff.getText().toString()+ " " + "  Pf No :" + pfnostaff.getText().toString() + " " +"  Date :"+date11.getText().toString()+"  Shift In  :"+shiftin.getText().toString()+"  Shift Out :"+shiftout.getText().toString()+"  Station :"+divStation+"  Section :"+divSection;
        return (a);
    }
}