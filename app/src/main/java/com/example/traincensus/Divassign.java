package com.example.traincensus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.String.valueOf;


public class Divassign extends AppCompatActivity {
    DatabaseHelper myDb;
    List<String> list, list1;
    List<String> mango;
    TextView shiftin, shiftout, div,namestaff, pfnostaff;
    TimePickerDialog timePickerDialog;
    Spinner spinsec, spinsta;
    Button divsummit;
    EditText date11;
    String divSection, divStation, a,a1;
    FirebaseFirestore database1 = FirebaseFirestore.getInstance();
    FirebaseFirestore database2 = FirebaseFirestore.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Login");
    public Calendar calendar1 = Calendar.getInstance();
    Date date5;
    int flag, flag1;
    AutoCompleteTextView myAutoComplete;
    ArrayAdapter<String> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divassign);
        date11 = findViewById(R.id.dateview);
        shiftin = findViewById(R.id.shiftinview);
        shiftout = findViewById(R.id.shiftoutview);
        spinsec = findViewById(R.id.Secname);
        spinsta = findViewById(R.id.Stationview);
        div = findViewById(R.id.divnameview);
        divsummit = findViewById(R.id.submit);
        namestaff = findViewById(R.id.nameview);
        pfnostaff = findViewById(R.id.Pfno);
        Toolbar toolbar = findViewById(R.id.toolda);
        TextView dtext =  toolbar.findViewById(R.id.dividshow);
        myAutoComplete=findViewById(R.id.autopf);
        calendar1.add(Calendar.DATE, 0);
        div.setText(Splashscreen.mpref.getString("Division", "").toUpperCase());
        myDb = new DatabaseHelper(this);
        AddData();
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("PALAKKAD"))
            dtext.setText("DIV ADMIN - PGT");
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("CHENNAI"))
            dtext.setText("DIV ADMIN - MAS");
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("TRICHY"))
            dtext.setText("DIV ADMIN - TPJ");
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("MADURAI"))
            dtext.setText("DIV ADMIN - MDU");
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("SALEM"))
            dtext.setText("DIV ADMIN - SA");
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("TRIVANDRUM"))
            dtext.setText("DIV ADMIN - TVC");
        divsummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                flag1 = 0;
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


                try {
                    date5 = format.parse(date11.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (namestaff.getText().toString().equals("")) {
                    flag = 1;
                    namestaff.setError(getString(R.string.a));
                }
                if (pfnostaff.getText().toString().equals("")) {
                    flag = 1;
                    pfnostaff.setError(getString(R.string.a1));
                }
                if (date11.getText().toString().equals("")) {
                    flag = 1;
                    date11.setError(getString(R.string.a2));
                }
                if (shiftin.getText().toString().equals("")) {
                    flag = 1;
                    shiftin.setError(getString(R.string.a3));
                }
                if (shiftout.getText().toString().equals("")) {
                    flag = 1;
                    shiftout.setError(getString(R.string.a4));
                }
                if (flag == 0) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Divassign.this);
                    alertDialogBuilder.setTitle("Confirm Nominated Details");
                    alertDialogBuilder
                            .setMessage(show(a))
                            .setCancelable(false)
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    tamil();
                                }
                            })
                            .setNegativeButton("Recheck", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    Toast toast=Toast.makeText(Divassign.this, getString(R.string.a10), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                    toast.show();
                }
            }
        });
        final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(Divassign.this, date12, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();
            }
        });
        database1.collection(Splashscreen.mpref.getString("Division", "").toLowerCase()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                list = new ArrayList<String>();
                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                    String pfvali = doc.getString("section");
                    list.add(pfvali);
                }
                Divassign.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Divassign.this, android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinsec.setAdapter(dataAdapter);
                        dataAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        spinsec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View
                    selectedItemView, int position, long id) {
                divSection = valueOf(spinsec.getSelectedItem());
                spinsta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        shiftin.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(calendar.HOUR);
            int minute = calendar.get(calendar.MINUTE);

            @Override
            public void onClick(View v) {
                shiftin.setError(null);
                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i2) {
                        String am_pm;
                        if (i < 12) {
                            am_pm = "AM";
                            if (i2 < 10)
                                shiftin.setText(i + ":" + "0" + i2 + am_pm);
                            else
                                shiftin.setText(i + ":" + i2 + am_pm);
                        } else if (i == 12) {
                            am_pm = "PM";
                            if (i2 < 10)
                                shiftin.setText(i + ":" + "0" + i2 + am_pm);
                            else
                                shiftin.setText(i + ":" + i2 + am_pm);
                        } else {
                            am_pm = "PM";
                            if (i2 < 10)
                                shiftin.setText((i = (i % 12)) + ":" + "0" + i2 + am_pm);
                            else
                                shiftin.setText((i = (i % 12)) + ":" + i2 + am_pm);
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
                shiftout.setError(null);
                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override


                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String am_pm;
                        if (i < 12) {
                            am_pm = "AM";
                            if (i1 < 10)
                                shiftout.setText(i + ":" + "0" + i1 + am_pm);
                            else
                                shiftout.setText(i + ":" + i1 + am_pm);
                        } else if (i == 12) {
                            am_pm = "PM";
                            if (i1 < 10)
                                shiftout.setText(i + ":" + "0" + i1 + am_pm);
                            else
                                shiftout.setText(i + ":" + i1 + am_pm);
                        } else {
                            am_pm = "PM";
                            if (i1 < 10)
                                shiftout.setText((i = (i % 12)) + ":" + "0" + i1 + am_pm);
                            else
                                shiftout.setText((i = (i % 12)) + ":" + i1 + am_pm);
                        }
                    }
                }, hour, minute, true);
                timePickerDialog.show();
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
                divStation= valueOf(spinsta.getSelectedItem()).toUpperCase();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
        myAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id)
            {
                List<Updateclass> apple=myDb.getAllvalues();
                mango = new ArrayList<>();
                for(Updateclass d:apple)
                    if(d.getPf().equals(myAutoComplete.getText().toString()))
                    {
                        namestaff.setText(d.getName().toUpperCase());
                        pfnostaff.setText(d.getMobile());
                    }
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
        a = "Name :" + namestaff.getText().toString()+ '\n' + "Pf No :" +myAutoComplete.getText().toString() + '\n'+ "Mobile :" +pfnostaff.getText().toString()+'\n' +"Section :"+divSection+'\n'+"Station :"+divStation+'\n'+'\n'+"Date :"+date11.getText().toString()+'\n'+"Shift In  :"+shiftin.getText().toString()+"Shift Out :"+shiftout.getText().toString();
        return (a);
    }
    public String show1(String a1)
    {
        a1 = "Name :" + namestaff.getText().toString()+ '\n' + "Pf No :" + pfnostaff.getText().toString();
        return (a1);
    }
    public void tamil()
    {
        AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Divassign.this);
        alertDialogBuilder1.setTitle("Do you Want to Nominate Same Officer");
        alertDialogBuilder1
                .setMessage(show1(a1))
                .setCancelable(false)
                .setPositiveButton("Reassign", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Toast toast=Toast.makeText(Divassign.this, "Record Saved Successfully", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                        toast.show();
                        FirestoreDivision note = new FirestoreDivision(Splashscreen.mpref.getString("Division", ""), namestaff.getText().toString().trim(),  myAutoComplete.getText().toString().trim(), divStation, shiftin.getText().toString().trim(), shiftout.getText().toString().trim(),pfnostaff.getText().toString().trim(), "field", divSection, date11.getText().toString());
                        notebookRef.add(note);
                        date11.setText("DD/MM/YYYY");
                        shiftin.setText("HH:MM");
                        shiftout.setText("HH:MM");
                    }
                })
                .setNegativeButton("New Officer", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Toast toast=Toast.makeText(Divassign.this, "Record Saved Successfully", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                        toast.show();
                        FirestoreDivision note = new FirestoreDivision(Splashscreen.mpref.getString("Division", ""), namestaff.getText().toString().trim(),  myAutoComplete.getText().toString().trim(), divStation, shiftin.getText().toString().trim(), shiftout.getText().toString().trim(),pfnostaff.getText().toString().trim(), "field", divSection, date11.getText().toString());
                        notebookRef.add(note);
                        namestaff.setText("");
                        pfnostaff.setText("");
                        myAutoComplete.setText("");
                        date11.setText("DD/MM/YYYY");
                        shiftin.setText("HH:MM");
                        shiftout.setText("HH:MM");
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog1 = alertDialogBuilder1.create();
        alertDialog1.show();
    }
    public void AddData()
    {
        List<Updateclass> apple=myDb.getAllvalues();
        mango = new ArrayList<>();
        for(Updateclass d:apple)
            mango.add(d.getPf());
        Divassign.this.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                myAdapter = new ArrayAdapter<String>(Divassign.this, android.R.layout.simple_list_item_1, mango);
                myAutoComplete.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
        });
    }
    public void onBackPressed()
    {
        Intent intent=new Intent(Divassign.this,Homepage.class);
        startActivity(intent);
        finish();
    }
}