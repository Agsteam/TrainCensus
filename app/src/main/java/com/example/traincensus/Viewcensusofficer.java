package com.example.traincensus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.Calendar;
import java.util.Date;

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
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolvc);
        setSupportActionBar(mToolbar);
        if(getIntent().getIntExtra("fla",0)==2)
        setUpRecyclerView();
        if(getIntent().getIntExtra("fla",0)==1)
            setUpRecyclerView1();
        if(getIntent().getIntExtra("fla",0)==3)
            setUpRecyclerView2();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout1:

                startActivity(new Intent(Viewcensusofficer.this,Mainscreen.class));
                Toast.makeText(Viewcensusofficer.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                finish();
                SharedPreferences.Editor editor=mpref.edit();
                editor.clear();
                editor.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);}


    private void setUpRecyclerView() {
        Query query = notebookRef.whereEqualTo("division", mpref.getString("Division","")).whereEqualTo("accestype","field").whereEqualTo("date1",getIntent().getStringExtra("date")).orderBy("name", Query.Direction.DESCENDING);
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
        Query query = notebookRef.whereEqualTo("division", mpref.getString("Division","")).whereEqualTo("accestype","field").whereEqualTo("station",getIntent().getStringExtra("station")).whereEqualTo("date1",getIntent().getStringExtra("date")).orderBy("name", Query.Direction.DESCENDING);
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
        Query query = notebookRef.whereEqualTo("division", mpref.getString("Division","")).whereEqualTo("accestype","field").whereEqualTo("section",getIntent().getStringExtra("sect")).whereEqualTo("date1",getIntent().getStringExtra("date")).orderBy("name", Query.Direction.DESCENDING);
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
}