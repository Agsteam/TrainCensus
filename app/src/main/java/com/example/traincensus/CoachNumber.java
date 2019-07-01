package com.example.traincensus;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
public class CoachNumber extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    public ArrayList<EditModel> editModelArrayList;
    static int flag,flag1,flag2,total,atc;
    String station,c,code,coachnumberstring,coachtypestring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_number);
        recyclerView = findViewById(R.id.recyclerView);
        Button count=findViewById(R.id.but_count);
        flag = 0;flag1=0;flag2=0;code="";
        editModelArrayList = populateList();
        customAdapter = new CustomAdapter(this, editModelArrayList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        count.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                total=0;atc=0;
                flag = 0;
                flag1=0;
                flag2=0;
                coachnumberstring="";
                coachtypestring="";
                for (int jk = 0; jk < CustomAdapter.editModelArrayList.size(); jk++)
                {
                    station = CustomAdapter.editModelArrayList.get(jk).getSpinnerValue();
                    Log.e("sun",station);
                    if(!station.equals("Select"))
                    {
                        for (int i = 0; i < station.length(); i++)
                        {
                            int za = 0;
                            c = station.substring(za + i, za + i + 1);
                            if (c.equals("("))
                            {
                                code = station.substring(i + 1, (station.length() - 1));
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(CoachNumber.this,"Please select Coach type"+(jk+1),Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(!code.equals(""))
                    {
                        coachtypestring+=CustomAdapter.editModelArrayList.get(jk).getSpinnerValue()+',';
                        total += Integer.parseInt(code);
                        code = "";
                        flag+=1;
                    }
                    if(!CustomAdapter.editModelArrayList.get(jk).getEditTextValue().substring(0, 1).equals("C"))
                    {
                        coachnumberstring += CustomAdapter.editModelArrayList.get(jk).getEditTextValue()+',';
                        flag1+=1;
                    }
                    else {
                        Toast.makeText(CoachNumber.this, "Please select Coach Number" + (jk + 1), Toast.LENGTH_SHORT).show();
                    }
                    if(CustomAdapter.editModelArrayList.get(jk).getEdit_ActualValue()!=null)
                    {
                        atc+=Integer.parseInt(CustomAdapter.editModelArrayList.get(jk).getEdit_ActualValue());
                        flag2+=1;
                    }
                    else
                    {
                        Toast.makeText(CoachNumber.this,"Please enter coach occupied"+(jk+1),Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if((flag==CustomAdapter.editModelArrayList.size())&&(flag1==CustomAdapter.editModelArrayList.size())&&(flag2==CustomAdapter.editModelArrayList.size()))
                {
                    Intent intent=new Intent(CoachNumber.this,Fucounting.class);
                    Integer temp=getIntent().getIntExtra("edit_trainnumber",0);
                    Log.e("sun",""+temp);
                    intent.putExtra("div",getIntent().getStringExtra("div"));
                    intent.putExtra("dutydate",getIntent().getStringExtra("dutydate"));
                    intent.putExtra("sec",getIntent().getStringExtra("sec"));
                    intent.putExtra("sta",getIntent().getStringExtra("sta"));
                    intent.putExtra("trainnumber",temp.toString());
                    intent.putExtra("tcc",total);
                    intent.putExtra("atc",atc);
                    intent.putExtra("ct",coachtypestring);
                    intent.putExtra("cn",coachnumberstring);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private ArrayList<EditModel> populateList() {
        ArrayList<EditModel> list = new ArrayList<>();
        for (int i = 0; i < getIntent().getIntExtra("edit_noofcoach", -1); i++) {
            EditModel editModel = new EditModel();
            int a=i+1;
            editModel.setEditTextValue("No:" + a);
            list.add(editModel);
        }
        return list;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onBackPressed ()
    {
        Intent intent=new Intent(CoachNumber.this,Trainprofile.class);
        intent.putExtra("div",getIntent().getStringExtra("div"));
        intent.putExtra("sta",getIntent().getStringExtra("sta"));
        intent.putExtra("dutydate",getIntent().getStringExtra("dutydate"));
        intent.putExtra("sec",getIntent().getStringExtra("sec"));
        startActivity(intent);
        finish();
    }
}
