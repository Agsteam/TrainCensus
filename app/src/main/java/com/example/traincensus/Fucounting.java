package com.example.traincensus;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.traincensus.Model.TrainDetailsResponse;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
public class Fucounting extends AppCompatActivity
{
    static EditText gsa1,gsrda1,gslrda1,gsra1,wgscza1,gslra1;
    EditText g1c,g2c,g3c,g4c,g5c,g6c,total;
    final Context context = this;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    int occ1 = 0, cc1 = 0;
    int nugs12,nugsrd12,nugslrd12,nugs112,nugslr12,nugsld12;
    int nugs121,nugsrd121,nugslrd121,nugs1121,nugslr121,nugsld121;
    int average,flag;
    String c,code="",a,station;
    String todayAsString,aq,coachshow="";
    CheckBox g1,g2,g3,g4,g5,g6;
    TextView ccy1;
    AutoCompleteTextView listView;
    ProgressBar tload;
    List<String> list;
    Button trains1,saverecord;
    ArrayAdapter<String> adapter;
    Request request;
    DatabaseReference savetask;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fucounting);
        Toolbar mToolbar =  findViewById(R.id.toolfc);
        setSupportActionBar(mToolbar);
        FirebaseApp.initializeApp(this);
        savetask = FirebaseDatabase.getInstance().getReference("field");
        trains1 =  findViewById(R.id.button);
        saverecord = findViewById(R.id.submit);
        g4 =  findViewById(R.id.gsr);
        g2 =  findViewById(R.id.gsrd);
        g6 =  findViewById(R.id.gslrd);
        g1 =  findViewById(R.id.gs);
        g5 =  findViewById(R.id.gslr);
        g3 =  findViewById(R.id.wgscz);
        g4c = findViewById(R.id.gsrv);
        g2c = findViewById(R.id.gsrdv);
        g6c = findViewById(R.id.gslrdv);
        g1c = findViewById(R.id.gsv);
        g5c = findViewById(R.id.gslrv);
        g3c = findViewById(R.id.wgsczv);
        total =  findViewById(R.id.tc);
        ccy1 =  findViewById(R.id.ccy);
        listView =  findViewById(R.id.tno);
        tload =  findViewById(R.id.progressBar2);
        gsa1=findViewById(R.id.gsa);
        gsrda1=findViewById(R.id.gsrda);
        gslrda1=findViewById(R.id.gslrda);
        gsra1=findViewById(R.id.gsra);
        wgscza1=findViewById(R.id.wgscza);
        gslra1=findViewById(R.id.gslra);
        super.onStart();
        station=getIntent().getStringExtra("sta");
        saverecord.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                todayAsString = getIntent().getStringExtra("dutydate");
                if (total.getText().toString().equals(""))
                { total.setError("please fill Total Count"); }
                else
                {
                    String t = total.getText().toString();
                    occ1 = Integer.parseInt(t);
                }
                if ((ccy1.getText().toString().equals("")) || (ccy1.getText().toString().trim().length() <= 1))
                {
                    if ((!g1.isChecked()) && (!g2.isChecked()) && (!g3.isChecked()) && (!g4.isChecked()) && (!g5.isChecked()) && (!g1.isChecked()))
                    {
                        g1.setError("please fill Number of Coach and Coach Type");
                        g2.setError("please fill Number of Coach and Coach Type");
                        g3.setError("please fill Number of Coach and Coach Type");
                        g4.setError("please fill Number of Coach and Coach Type");
                        g5.setError("please fill Number of Coach and Coach Type");
                        g6.setError("please fill Number of Coach and Coach Type");
                    }
                    if ((g1c.getText().toString().trim().length() == 0) && (g2c.getText().toString().trim().length() == 0) && (g3c.getText().toString().trim().length() == 0) && (g4c.getText().toString().trim().length() == 0) && (g5c.getText().toString().trim().length() == 0) && (g6c.getText().toString().trim().length() == 0))
                    {
                        g3c.setError("please fill Number of Coach and Coach Type");
                        g1c.setError("please fill Number of Coach and Coach Type");
                        g4c.setError("please fill Number of Coach and Coach Type");
                        g2c.setError("please fill Number of Coach and Coach Type");
                        g5c.setError("please fill Number of Coach and Coach Type");
                        g6c.setError("please fill Number of Coach and Coach Type");
                    }
                }
                aq=gsa1.getText().toString()+wgscza1.getText().toString().trim()+gsrda1.getText().toString().trim()+gsra1.getText().toString().trim()+gslrda1.getText().toString().trim()+gslra1.getText().toString().trim();
                if(aq.trim().equals(""))
                {
                    if((gsa1.getText().toString().equals(""))||(wgscza1.getText().toString().trim().equals(""))||(gsrda1.getText().toString().trim().equals(""))||(gsra1.getText().toString().trim().equals(""))||(gslrda1.getText().toString().trim().equals(""))||(gslra1.getText().toString().trim().equals("")))
                    {
                        gsa1.setError("Please Enter Coach Number");
                        wgscza1.setError("Please Enter Coach Number");
                        gsrda1.setError("Please Enter Coach Number");
                        gsra1.setError("Please Enter Coach Number");
                        gslrda1.setError("Please Enter Coach Number");
                        gslra1.setError("Please Enter Coach Number");
                    }
                }
                else
                {
                    String t = ccy1.getText().toString();
                    cc1 = Integer.parseInt(t);
                }
                if (listView.getText().toString().trim().length() < 6)
                    listView.setError("please select train no from the list ");
                else if ((occ1 != 0) && (cc1 != 0))
                {
                    float z = (float) occ1 / (float) cc1;
                    average = (int) (z * 100);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Confirm");
                    alertDialogBuilder
                            .setMessage(show(a))
                            .setCancelable(false)
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int id)
                                {
                                    String id1 = savetask.push().getKey();
                                    Adddata1 artist = new Adddata1(getIntent().getStringExtra("div"), getIntent().getStringExtra("sta"), todayAsString, listView.getText().toString(), cc1, occ1, average,aq,coachshow);
                                    savetask.child((id1)).setValue(artist);
                                    g1.setChecked(false);
                                    g2.setChecked(false);
                                    g3.setChecked(false);
                                    g4.setChecked(false);
                                    g5.setChecked(false);
                                    g6.setChecked(false);
                                    g1c.setText("");
                                    g2c.setText("");
                                    g3c.setText("");
                                    g4c.setText("");
                                    g5c.setText("");
                                    g6c.setText("");
                                    total.setText("");
                                    listView.setText("");
                                    gsa1.setText("");
                                    gsrda1.setText("");
                                    gslrda1.setText("");
                                    gsra1.setText("");
                                    wgscza1.setText("");
                                    gslra1.setText("");
                                    Toast.makeText(Fucounting.this,"Record Saved Successfully",Toast.LENGTH_LONG).show();
                                    listView.requestFocus();
                                    coachshow="";
                                }
                            })
                            .setNegativeButton("Recount", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int id)
                                {
                                    coachshow="";
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else
                    Toast.makeText(Fucounting.this, "Please enter proper values", Toast.LENGTH_SHORT).show();
            }
        });
        gsa1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sun2();
                    sundar1();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        wgscza1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sun2();
                    sundar1();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        gsrda1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sundar1();
                    sun2();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        gsra1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sundar1();
                    sun2();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        gslrda1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sun2();
                    sundar1();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        gslra1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {   sun2();
                    sundar1();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        g1c.addTextChangedListener(new TextWatcher()
        {
           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count)
           {
             try
               {
                    sun2();
                    sundar1();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        g1c.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    Intent intent=new Intent(Fucounting.this,CoachNumber.class);
                    if(!g1c.getText().toString().isEmpty())
                    {
                        if(Integer.parseInt(g1c.getText().toString())<29) {
                            flag = 1;
                            intent.putExtra("co", Integer.parseInt(g1c.getText().toString()));
                            intent.putExtra("sta1", station);
                            intent.putExtra("fla", flag);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(Fucounting.this,"please check number of coach",Toast.LENGTH_SHORT).show();
                    }
                    else
                        g1c.setError("Please enter number of Coach in GS");
                }
            }
        });
        g2c.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sundar1();
                    sun2();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        g2c.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    flag=2;
                    Intent intent=new Intent(Fucounting.this,CoachNumber.class);
                    if(!g2c.getText().toString().isEmpty())
                    {
                        if(Integer.parseInt(g2c.getText().toString())<29)
                        {
                            intent.putExtra("co", Integer.parseInt(g2c.getText().toString()));
                            intent.putExtra("sta1", station);
                            intent.putExtra("fla", flag);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(Fucounting.this,"please check number of coach",Toast.LENGTH_SHORT).show();
                    }
                    else
                        g2c.setError("Please enter number of Coach in GS");
                }
            }
        });
        g3c.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sun2();
                    sundar1();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        g3c.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    flag=3;
                    Intent intent=new Intent(Fucounting.this,CoachNumber.class);
                    if(!g3c.getText().toString().isEmpty())
                    {
                        if(Integer.parseInt(g3c.getText().toString())<29)
                        {
                            intent.putExtra("co", Integer.parseInt(g3c.getText().toString()));
                            intent.putExtra("sta1", station);
                            intent.putExtra("fla", flag);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(Fucounting.this,"please check number of coach",Toast.LENGTH_SHORT).show();
                    }
                    else
                        g3c.setError("Please enter number of Coach in GS");
                }
            }
        });
        g4c.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sundar1();
                    sun2();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        g4c.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    flag=4;
                    Intent intent=new Intent(Fucounting.this,CoachNumber.class);
                    if(!g4c.getText().toString().isEmpty()) {
                        if(Integer.parseInt(g4c.getText().toString())<29) {
                            intent.putExtra("co", Integer.parseInt(g4c.getText().toString()));
                            intent.putExtra("sta1", station);
                            intent.putExtra("fla", flag);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(Fucounting.this,"please check number of coach",Toast.LENGTH_SHORT).show();
                    }
                    else
                        g4c.setError("Please enter number of Coach in GS");
                }
            }
        });
        g5c.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sun2();
                    sundar1();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        g5c.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    flag=5;
                    Intent intent=new Intent(Fucounting.this,CoachNumber.class);
                    if(!g5c.getText().toString().isEmpty()) {
                        if(Integer.parseInt(g5c.getText().toString())<29) {
                            intent.putExtra("co", Integer.parseInt(g5c.getText().toString()));
                            intent.putExtra("sta1", station);
                            intent.putExtra("fla", flag);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(Fucounting.this,"please check number of coach",Toast.LENGTH_SHORT).show();
                    }
                    else
                        g5c.setError("Please enter number of Coach in GS");
                }
            }
        });
        g6c.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                try
                {
                    sun2();
                    sundar1();
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        g6c.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    flag=6;
                    Intent intent=new Intent(Fucounting.this,CoachNumber.class);
                    if(!g6c.getText().toString().isEmpty())
                    {
                        if(Integer.parseInt(g6c.getText().toString())<29)
                        {
                            intent.putExtra("co", Integer.parseInt(g6c.getText().toString()));
                            intent.putExtra("sta1", station);
                            intent.putExtra("fla", flag);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(Fucounting.this,"please check number of coach",Toast.LENGTH_SHORT).show();
                    }
                    else
                        g6c.setError("Please enter number of Coach in GS");
                }
            }
        });
        g1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                sundar1();
            }
        });
        g2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                sundar1();
            }
        });
        g3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                sundar1();
            }
        });
        g4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                sundar1();
            }
        });
        g5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                sundar1();
            }
        });
        g6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                sundar1();
            }
        });
        for (int i = 0; i < station.length(); i++)
        {
            int za = 0;
            c = station.substring(za + i, za + i + 1);
            if (c.equals("("))
            {
                code = station.substring(i + 1, (station.length() - 1));
            }
        }
        code = code.toLowerCase();
        request = new Request.Builder().url("https://api.railwayapi.com/v2/arrivals/station/" + code + "/hours/4/apikey/k8792aaq6q/").build();
        trains1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
        tload.setVisibility(View.VISIBLE);
        sun1();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        station=getIntent().getStringExtra("sta");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        station=getIntent().getStringExtra("sta");
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
    public void sun1()
    {
        client.newCall(request).enqueue(new Callback()
        {
            @Override public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }
            @Override public void onResponse(Call call, Response response) throws IOException
            {
                try (ResponseBody responseBody = response.body())
                {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    String responseString = responseBody.string();
                    TrainDetailsResponse trainDetailsResponse = gson.fromJson(responseString, TrainDetailsResponse.class);
                    list = new ArrayList<>();
                    if(trainDetailsResponse.getTotal()==0)Toast.makeText(Fucounting.this,"Next Four Hours No trains",Toast.LENGTH_SHORT).show();
                    for(int i=0;i<trainDetailsResponse.getTotal();i++)
                    {
                        String trainname = trainDetailsResponse.getTrains().get(i).getNumber()+'\t'+trainDetailsResponse.getTrains().get(i).getName();
                        list.add(trainname);
                    }
                    Fucounting.this.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            adapter = new ArrayAdapter<String>(Fucounting.this, android.R.layout.simple_list_item_1, list);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            tload.setVisibility(View.GONE);
                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void sundar1()
    {
        nugs12=0;
        nugsrd12=0;
        nugslrd12=0;
        nugs112=0;
        nugslr12=0;
        nugsld12=0;
        if(g1.isChecked())
        {
            if ((g1c.getText().toString().equals(""))||(gsa1.getText().toString().trim().equals("")))
            {
                sun2();
                g1c.setError("Please Enter No of Coach");
                gsa1.setError("Please Enter the Coach Number");
            }
            else
            {
                nugs12 = Integer.parseInt(g1c.getText().toString()) * 90;
                sun2();
            }
        }
        if(g2.isChecked())
        {


            if ((g2c.getText().toString().equals(""))||(gsrda1.getText().toString().trim().equals("")))
            {
                sun2();
                g2c.setError("Please Enter No of Coach");
                gsrda1      .setError("Please Enter the Coach Number");
            }
            else
            {
                nugsrd12 = Integer.parseInt(g2c.getText().toString()) * 60;
                sun2();
            }
        }
        if(g3.isChecked())
        {
            if ((g3c.getText().toString().equals(""))||(wgscza1.getText().toString().trim().equals("")))
            {
                sun2();
                g3c.setError("Please Enter No of Coach");
                wgscza1.setError("Please Enter the Coach Number");
            }
            else{
                nugslrd12 = Integer.parseInt(g3c.getText().toString()) * 20;
                sun2();
            }
        }
        if(g5.isChecked())
        {
            if ((g5c.getText().toString().equals(""))||(gslra1.getText().toString().trim().equals("")))
            {
                sun2();
                g5c.setError("Please Enter No of Coach");
                gslra1.setError("Please Enter the Coach Number");
            }
            else
            {  nugslr12 = Integer.parseInt(g5c.getText().toString()) * 42;
                sun2();
            }
        }
        if(g4.isChecked())
        {
            if ((g4c.getText().toString().equals(""))||(gsra1.getText().toString().trim().equals("")))
            {
                sun2();
                g4c.setError("Please Enter No of Coach");
                gsra1.setError("Please Enter the Coach Number");
            }
            else{
                nugs112 = Integer.parseInt(g1c.getText().toString()) * 60;
                sun2();
            }
        }
        if(g6.isChecked())
        {
            if ((g6c.getText().toString().equals(""))||(gslrda1.getText().toString().trim().equals("")))
            {
                sun2();
                g6c.setError("Please Enter No of Coach");
                gslrda1.setError("Please Enter the Coach Number");
            }
            else
            {
                nugsld12 = Integer.parseInt(g6c.getText().toString()) * 108;
                sun2();
            }
        }
        ccy1.setText(getString(R.string.empty)+(nugs12+nugsrd12+nugslrd12+nugs112+nugslr12+nugsld12));
    }
    public String show(String t)
    {
        if(nugs12!=0)
        {
            nugs121=nugs12/90;
            coachshow+="GS  "+nugs121;}
        if(nugsrd12!=0)
        {
            nugsrd121=nugsrd12/60;
            coachshow+="  GSRD  "+nugsrd121;}
        if(nugslrd12!=0)
        {
            nugslrd121=nugslrd12/20;
            coachshow+="  GSLRD  "+nugslrd121;}
        if(nugs112!=0)
        {
            nugs1121=nugs112/60;
            coachshow+="  GSR  "+nugs1121;}
        if(nugsld12!=0)
        {   nugsld121=nugsld12/108;
            coachshow+="  GSLR  "+nugsld121;}
        if(nugslr12!=0)
        {   nugslr121=nugslr12/42;
            coachshow+="  WGSCZ  "+nugslr121;}
        t = "Train No :" + listView.getText().toString()+ '\n'+"Coach Type :"+coachshow +'\n'+ "  Carrying Capacity :" + ccy1.getText().toString() + '\n' +"  Actual Occupied :"+total.getText().toString()+'\n'+"  Percentage  :"+average;
        return (t);
    }
    public  void sun2()
    {
        gsa1.setError(null);
        wgscza1.setError(null);
        gsrda1.setError(null);
        gslrda1.setError(null);
        gslra1.setError(null);
        gsra1.setError(null);
        g1c.setError(null);
        g2c.setError(null);
        g3c.setError(null);
        g4c.setError(null);
        g5c.setError(null);
        g6c.setError(null);
        g1.setError(null);
        g2.setError(null);
        g3.setError(null);
        g4.setError(null);
        g5.setError(null);
        g6.setError(null);
    }
    @Override
    public void onBackPressed ()
    {
        Intent intent=new Intent(Fucounting.this,Fhome.class);
        startActivity(intent);
        finish();
    }
}