package com.example.traincensus;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.traincensus.Splashscreen.mpref;
import static java.lang.String.valueOf;

public class Viewcensusofficer extends AppCompatActivity
{
    List<String> list, list1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Login");
    FirebaseFirestore database1 = FirebaseFirestore.getInstance();
    FirebaseFirestore database2 = FirebaseFirestore.getInstance();
    private Adaptercard1 adapter=null;
    Spinner vsec,vsta;
    String divSection,divStation;
    EditText e;
    public Calendar calendar1 = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcensusofficer);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolvc);
        vsec=findViewById(R.id.spinner);
        vsta=findViewById(R.id.spinner2);
        e=findViewById(R.id.editText);
        setSupportActionBar(mToolbar);
        database1.collection(Splashscreen.mpref.getString("Division", "").toLowerCase()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                list = new ArrayList<String>();
                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                    String pfvali = doc.getString("section");
                    list.add(pfvali);
                }
                Viewcensusofficer.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Viewcensusofficer.this, android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vsec.setAdapter(dataAdapter);
                        dataAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        vsec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View
                    selectedItemView, int position, long id) {
                divSection = valueOf(vsec.getSelectedItem());
                spinsta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
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
        e.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                e.setError(null);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Viewcensusofficer.this, date12, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.logout1:

                startActivity(new Intent(Viewcensusofficer.this,Mainscreen.class));
                Toast.makeText(Viewcensusofficer.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                finish();
                SharedPreferences.Editor editor=mpref.edit();
                editor.clear();
                editor.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setUpRecyclerView()
    {
        Query query = notebookRef.whereEqualTo("division", mpref.getString("Division","")).whereEqualTo("accestype","field").orderBy("name", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<FirestoreDivision> options = new FirestoreRecyclerOptions.Builder<FirestoreDivision>()
                .setQuery(query, FirestoreDivision.class)
                .build();
        adapter = new Adaptercard1(options);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
       adapter.stopListening();
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
                Viewcensusofficer.this.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(Viewcensusofficer.this, android.R.layout.simple_spinner_item, list1);
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
                divStation= valueOf(vsta.getSelectedItem()).toUpperCase();
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
        e.setText(sdf.format(calendar1.getTime()));
        setUpRecyclerView();
    }
}