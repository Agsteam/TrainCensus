package com.example.traincensus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Report extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("field");
    EditText et;
    Button bt;
    String st[]={""},dt[]={""};
    int i=0,tav[]={0},j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        et=findViewById(R.id.editText2);
        bt=findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                        {
                            Adddata1 post = postSnapshot.getValue(Adddata1.class);
                            String tq=post.getTrain();
                            tq=tq.substring(0,5);
                            if ((st[i].equals("") || st[i].equals(post.getStation())) && (dt[i].equals("") || (dt[i].equals(post.getDudate())))&&(Integer.parseInt(et.getText().toString())==Integer.parseInt(tq)))
                            {
                                        st[i] = post.getStation();
                                        tav[i] += post.getAv();
                                        dt[i] = post.getDudate();
                                        Log.e("avg", "" + tav[i]);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("The read failed: " , ""+databaseError.getCode());
                    }
                });
            }
        });

    }
}
