package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import java.util.Objects;

import static com.example.traincensus.Splashscreen.mpref;

public class Viewcensusofficer extends AppCompatActivity
{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Login");
    private Adaptercard1 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcensusofficer);
        Toolbar toolbar=findViewById(R.id.toolvc);
        TextView tit=toolbar.findViewById(R.id.ideal);
        if(getIntent().getIntExtra("fla",0)==2)
            setUpRecyclerView();
        if(getIntent().getIntExtra("fla",0)==1)
            setUpRecyclerView1();
        if(getIntent().getIntExtra("fla",0)==3)
            setUpRecyclerView2();
        if(getIntent().getIntExtra("fla",0)==4)
        {
            setUpRecyclerView3();
            tit.setText("Duty Roster");
        }
    }


    private void setUpRecyclerView() {
        Query query = notebookRef.whereEqualTo("division", (Objects.requireNonNull(mpref.getString("Division", "")).toUpperCase())).whereEqualTo("accestype","field").whereEqualTo("date1",getIntent().getStringExtra("date")).orderBy("name", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<FirestoreDivision> options = new FirestoreRecyclerOptions.Builder<FirestoreDivision>()
                .setQuery(query, FirestoreDivision.class)
                .build();
        adapter = new Adaptercard1(options);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    private void setUpRecyclerView1() {
        Query query = notebookRef.whereEqualTo("division", (Objects.requireNonNull(mpref.getString("Division", "")).toUpperCase())).whereEqualTo("accestype","field").whereEqualTo("station",(getIntent().getStringExtra("station").toUpperCase())).whereEqualTo("date1",getIntent().getStringExtra("date")).orderBy("name", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<FirestoreDivision> options = new FirestoreRecyclerOptions.Builder<FirestoreDivision>()
                .setQuery(query, FirestoreDivision.class)
                .build();
        adapter = new Adaptercard1(options);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    private void setUpRecyclerView2() {
        Query query = notebookRef.whereEqualTo("division", (Objects.requireNonNull(mpref.getString("Division", "")).toUpperCase())).whereEqualTo("accestype","field").whereEqualTo("section",getIntent().getStringExtra("sect")).whereEqualTo("date1",getIntent().getStringExtra("date")).orderBy("name", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<FirestoreDivision> options = new FirestoreRecyclerOptions.Builder<FirestoreDivision>()
                .setQuery(query, FirestoreDivision.class)
                .build();

        adapter = new Adaptercard1(options);

        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    private void setUpRecyclerView3() {
        Query query = notebookRef.whereEqualTo("division", (Objects.requireNonNull(mpref.getString("Division", "")).toUpperCase())).whereEqualTo("accestype","field").whereEqualTo("pfno", ((Objects.requireNonNull(mpref.getString("Username", "")).toLowerCase()))).orderBy("date1", Query.Direction.ASCENDING);
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
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public void onBackPressed()
    {
        if(!Objects.requireNonNull(mpref.getString("Accesstype", "")).equals("field")) {
            Intent intent = new Intent(Viewcensusofficer.this, ViewOptionActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(Viewcensusofficer.this, Fhome.class);
            startActivity(intent);
            finish();

        }
    }
}