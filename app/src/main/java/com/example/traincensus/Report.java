package com.example.traincensus;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;

public class Report extends AppCompatActivity
{
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("field");
    DatabaseReference ref1 = database.getReference("field");
    DatabaseReference ref2 = database.getReference("field");
    EditText et;
    Button bt;
    String tnum,tnum1="";
    ArrayList<String> st = new ArrayList<>();
    ArrayList<String> st1 = new ArrayList<>();
    ArrayList<String> dt = new ArrayList<>();
    ArrayList<String> dt1 = new ArrayList<>();
    ArrayList<Integer> tav = new ArrayList<>();
    ArrayList<Integer> j = new ArrayList<>();
    ArrayList<Integer> per = new ArrayList<>();
    int total=0,count=0,i=0,j1;
    float y1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        et=findViewById(R.id.editText2);
        bt=findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tnum=et.getText().toString().trim();
                st.clear();
                st1.clear();
                dt.clear();
                dt1.clear();
                tav.clear();
                j.clear();
                per.clear();
                if(!et.getText().toString().isEmpty())
                first();
                else{
                    Toast toast=Toast.makeText(Report.this, "Please enter the Train No", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                    toast.show();
                }
            }
        });
    }
    public static <String> ArrayList<String> removeDuplicates(ArrayList<String> list)
    {
        ArrayList<String> newList = new ArrayList<String>();
        for (String element : list) {
            if (!newList.contains(element))
            {
                newList.add(element);
            }
        }
        return newList;
    }
    public void first()
    {
        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot1: dataSnapshot.getChildren())
                {
                    Adddata1 post1 = postSnapshot1.getValue(Adddata1.class);
                    String tq1 = post1.getTrain();
                    tq1 = tq1.substring(0, 5);
                    if (Integer.parseInt(tnum) == Integer.parseInt(tq1))
                    {
                        tnum1=post1.getTrain();
                        dt.add(post1.getDudate());
                    }
                }
                Collections.sort(dt);
                dt1 = removeDuplicates(dt);
                if(!tnum1.equals(""))
                {
                    second();}
                else {
                    Toast toast=Toast.makeText(Report.this, "Train No you entered is not valid ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                    toast.show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Log.e("The read failed: " , ""+databaseError.getCode());
            }
        });
    }
    public void second()
    {
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int o=0;o<dt1.size();o++)
                for (DataSnapshot postSnapshot2 : dataSnapshot.getChildren())
                {
                    Adddata1 post2 = postSnapshot2.getValue(Adddata1.class);
                    String tq2 = post2.getTrain();
                    tq2 = tq2.substring(0, 5);
                    if ((tnum.equals(tq2)) && (dt1.get(o).equals(post2.getDudate()))) {
                        st.add(post2.getStation());
                    }
                }
                Collections.sort(st);
                st1 = removeDuplicates(st);
                third();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", "" + databaseError.getCode());
            }
        });
    }
    public void third()
    {
        for(int y=0;y<st1.size();y++)
        {
            tav.add(y,0);
            j.add(y,0);
            per.add(y,0);
        }
        ref2.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot3: dataSnapshot.getChildren())
                {
                    for(i=0;i<dt1.size();i++)
                    {
                        for(j1=0;j1<st1.size();j1++)
                        {
                            Adddata1 post3 = postSnapshot3.getValue(Adddata1.class);
                            String tq2 = post3.getTrain();
                            tq2 = tq2.substring(0, 5);
                            if (((tnum.equals(tq2)) && (dt1.get(i).equals(post3.getDudate()))) && (st1.get(j1).equals(post3.getStation())))
                            {
                                total=post3.getTc();
                                count=post3.getCc();
                                total = total + tav.get(j1);
                                count = count + j.get(j1);
                                tav.add(j1,total);
                                tav.remove(j1+1);
                                j.add(j1,count);
                                j.remove(j1+1);
                                y1=Math.round(((float)total/(float)count)*100);
                                per.add(j1,(int)y1);
                                per.remove(j1+1);
                                total=0;
                                count=0;
                            }
                        }
                    }
                }
                i=0;
                j1=0;
                pass();
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Log.e("The read failed: " , ""+databaseError.getCode());
            }
        });
    }
    public void pass()
    {
        Intent intent=new Intent(Report.this,Reportcreate.class);
        intent.putStringArrayListExtra("st",st1);
        intent.putStringArrayListExtra("dt",dt1);
        intent.putIntegerArrayListExtra("tav",tav);
        intent.putIntegerArrayListExtra("j",j);
        intent.putIntegerArrayListExtra("per",per);
        intent.putExtra("tra",tnum1);
        startActivity(intent);
        finish();
    }
    public void onBackPressed()
    {
            Intent intent = new Intent(Report.this, Head.class);
            startActivity(intent);
            finish();

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
}