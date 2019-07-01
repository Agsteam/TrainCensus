package com.example.traincensus;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.traincensus.Splashscreen.mpref;
import static java.lang.String.valueOf;

public class ViewOptionActivity extends AppCompatActivity
{
    List<String> list, list1;
    FirebaseFirestore database1 = FirebaseFirestore.getInstance();
    FirebaseFirestore database2 = FirebaseFirestore.getInstance();
    Spinner vsec, vsta;
    String divSection, divStation;
    EditText e;
    Button su;
    RadioGroup group;
    RadioButton a12,se,st;
    public Calendar calendar1 = Calendar.getInstance();
    int flag=0;
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_option);
        flag=0;
        vsec = findViewById(R.id.spinner);
        vsta = findViewById(R.id.spinner2);
        e = findViewById(R.id.editText);
        su=findViewById(R.id.button2);
        group=findViewById(R.id.radioGroup);
        a12=findViewById(R.id.radioButton);
        se=findViewById(R.id.radioButton1);
        st=findViewById(R.id.radioButton2);

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
        e.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                e.setError(null);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ViewOptionActivity.this, date12, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton2:
                        flag = 1;
                        vsec.setVisibility(View.VISIBLE);
                        vsta.setVisibility(View.VISIBLE);
                        sec();
                        break;
                    case R.id.radioButton:
                        vsta.setVisibility(View.INVISIBLE);
                        vsec.setVisibility(View.INVISIBLE);
                        flag = 2;
                        break;
                    case R.id.radioButton1:
                        flag = 3;
                        vsta.setVisibility(View.INVISIBLE);
                        vsec.setVisibility(View.VISIBLE);
                        sec();
                        break;
                }

            }
        });
        su.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(flag==0)
                {
                    Toast toast=Toast.makeText(ViewOptionActivity.this, "Please Select Date", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                    toast.show();
                }
                if(e.getText().toString().trim().isEmpty())
                {
                    Toast toast=Toast.makeText(ViewOptionActivity.this, "Please Select Option Button", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                    toast.show();
                }
                else
                {
                    Intent intent=new Intent(ViewOptionActivity.this,Viewcensusofficer.class);
                    intent.putExtra("fla",flag);
                    if(flag==1)
                    {
                        intent.putExtra("station", divStation);
                        intent.putExtra("date",e.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                    else if(flag==2)
                    {
                        intent.putExtra("date",e.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                    else if(flag==3)
                    {
                        intent.putExtra("sect", divSection);
                        intent.putExtra("date",e.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                      Toast toast=  Toast.makeText(ViewOptionActivity.this,"Select any one Option in above",Toast.LENGTH_SHORT);
                      toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                      toast.show();
                    }
                }
            }
        });
    }


    public void spinsta ()
    {
        database2.collection(mpref.getString("Division", "").toLowerCase()).addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e)
            {
                list1 = new ArrayList<>();
                for (DocumentSnapshot doc1 : queryDocumentSnapshots)
                {
                    String pfvali = doc1.getString("section");
                    if (pfvali.equals(divSection))
                      list1 = (List<String> )doc1.get("station");

                }
                ViewOptionActivity.this.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(ViewOptionActivity.this, android.R.layout.simple_spinner_item, list1);
                        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vsta.setAdapter(dataAdapter1);
                        dataAdapter1.notifyDataSetChanged();
                    }
                });
            }
        });
        vsta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                divStation = valueOf(vsta.getSelectedItem()).toUpperCase();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
    }
    private void updateLabel2 ()
    {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        e.setText(sdf.format(calendar1.getTime()));
    }
    public void sec()
    {
        database1.collection(Objects.requireNonNull(mpref.getString("Division", "")).toLowerCase()).addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e)
            {
                list = new ArrayList<>();
                for (DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    String pfvali = doc.getString("section");
                    list.add(pfvali);
                }
                ViewOptionActivity.this.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(ViewOptionActivity.this, android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vsec.setAdapter(dataAdapter);
                        dataAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        vsec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View
                    selectedItemView, int position, long id)
            {
                divSection = valueOf(vsec.getSelectedItem());
                if(flag==1)
                    spinsta();
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
    }
    public void onBackPressed()
    {
        Intent intent=new Intent(ViewOptionActivity.this,Homepage.class);
        startActivity(intent);
        finish();
    }

}