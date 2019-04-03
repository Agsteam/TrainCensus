package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity
{
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("field");
    EditText et;
    Button bt;
    String tnum;
    RecyclerView mRecyclerView;
    Reportclass mAdapter;
    ArrayList<String> st = new ArrayList<String>();
    ArrayList<String> dt = new ArrayList<String>();
    ArrayList<Integer> tav = new ArrayList<Integer>();
    ArrayList<Integer> j = new ArrayList<Integer>();
    ArrayList<Integer> per = new ArrayList<Integer>();
    int i,t,i1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        et=findViewById(R.id.editText2);
        bt=findViewById(R.id.button3);
        st.add("");
        dt.add("");
        i=0;i1=0;t=0;
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot postSnapshot1: dataSnapshot.getChildren())
                        {
                            tnum=et.getText().toString().trim();
                            Adddata1 post1 = postSnapshot1.getValue(Adddata1.class);
                            String tq1 = post1.getTrain();
                            tq1 = tq1.substring(0, 5);
                            st.add(i,post1.getStation());
                            dt.add(i,post1.getDudate());
                            tav.add(i1,0);
                            j.add(i1,0);
                            if (st.get(i).equals(post1.getStation()) && (dt.get(i).equals(post1.getDudate())) && (Integer.parseInt(tnum) == Integer.parseInt(tq1)))
                            {
                                for(int k=0;k<=i;k++)
                                    if((st.get(k).equals(st.get(t)))&&(t!=1))
                                    {
                                        st.add(i,post1.getStation());
                                        dt.add(i,post1.getDudate());
                                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                                        {
                                            Adddata1 post = postSnapshot.getValue(Adddata1.class);
                                            String tq = post.getTrain();
                                            tq = tq.substring(0, 5);
                                            if ((st.get(i).equals(post.getStation())) &&  (dt.get(i).equals(post.getDudate())) && (Integer.parseInt(tnum) == Integer.parseInt(tq)))
                                            {
                                                int temp=tav.get(i1);
                                                temp+=post.getAv();
                                                tav.add(i1,temp);
                                                j.add(i1,j.get(i1)+1);
                                            }
                                        }
                                        t=1;
                                        i++;i1++;
                                    }
                                    else if(!(st.get(k).equals(post1.getStation())) && (dt.get(i).equals("") || (dt.get(i).equals(post1.getDudate()))) && (Integer.parseInt(tnum) == Integer.parseInt(tq1)))
                                    {
                                        st.add(i,post1.getStation());
                                        dt.add(i,post1.getDudate());
                                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                                        {
                                            Adddata1 post = postSnapshot.getValue(Adddata1.class);
                                            String tq = post.getTrain();
                                            tq = tq.substring(0, 5);
                                            if ((st.get(i).equals(post.getStation())) &&  (dt.get(i).equals(post.getDudate())) && (Integer.parseInt(tnum) == Integer.parseInt(tq)))
                                            {
                                                int temp=tav.get(i1)+post.getAv();
                                                tav.add(i1,temp);
                                                j.add(i1,j.get(i1)+1);
                                            }
                                        }
                                        i++;i1++;
                                    }
                            }
                        }
                        for(int h=0;h<i;h++)
                        {
                            Log.e("train no",et.getText().toString());
                            Log.e("Station",st.get(h));
                            Log.e("Date",dt.get(h));
                            Log.e("Total",""+tav.get(h));
                            if(i!=0&&j.get(h)!=0 )
                                per.add(h,tav.get(h)/j.get(h));
                            Log.e("Percentage",""+per.get(h));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {
                        Log.e("The read failed: " , ""+databaseError.getCode());
                    }
                });
            }
        });
    }
}
