package com.example.traincensus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Fucounting extends AppCompatActivity
{
    int flagauto=0;
    String c,code="";
    CheckBox g1,g2,g3,g4,g5,g6;
    EditText g1c,g2c,g3c,g4c,g5c,g6c,total;
    TextView ccy1;
    AutoCompleteTextView listView;
    ProgressBar tload;
    List<String> list;
    Button trains1,saverecord;
    ArrayAdapter<String> adapter;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    Request request;
    DatabaseReference savetask;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fucounting);
        FirebaseApp.initializeApp(this);
        savetask = FirebaseDatabase.getInstance().getReference("field");
        trains1=(Button)findViewById(R.id.button);
        saverecord=(Button)findViewById(R.id.submit);
        g1=(CheckBox)findViewById(R.id.gsr);
        g2=(CheckBox)findViewById(R.id.gsrd);
        g3=(CheckBox)findViewById(R.id.gslrd);
        g4=(CheckBox)findViewById(R.id.gs);
        g5=(CheckBox)findViewById(R.id.gslr);
        g6=(CheckBox)findViewById(R.id.wgscz);
        g1c=(EditText)findViewById(R.id.gsrv);
        g2c=(EditText)findViewById(R.id.gsrdv);
        g3c=(EditText)findViewById(R.id.gslrdv);
        g4c=(EditText)findViewById(R.id.gsv);
        g5c=(EditText)findViewById(R.id.gslrv);
        g6c=(EditText)findViewById(R.id.wgsczv);
        total=(EditText) findViewById(R.id.tc);
        ccy1=(TextView)findViewById(R.id.ccy);
        listView = (AutoCompleteTextView) findViewById(R.id.tno);
        tload=(ProgressBar)findViewById(R.id.progressBar2);
        super.onStart();
        saverecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pattern = "dd/MM/yyyy";
                int average;
                int occ1=0,cc1=0;
                DateFormat df1 = new SimpleDateFormat(pattern);
                String todayAsString = df1.format(Welcomescreen.dutydate);
                if (total.getText().toString().equals(""))
                {
                    Toast.makeText(Fucounting.this, "please fill Total Count", Toast.LENGTH_LONG).show();
                    total.setError("please fill Total Count");
                }
                else
                    {
                          String t = total.getText().toString();
                          occ1 = Integer.parseInt(t);
                    }
                if ((ccy1.getText().toString().equals(""))||(ccy1.getText().toString().trim().length()<=1))
                {
                    if ((!g1.isChecked())&& (!g2.isChecked()) && (!g3.isChecked()) && (!g4.isChecked()) && (!g5.isChecked()) &&(!g1.isChecked()))
                    {
                        g1.setError("please fill Number of Coach and Coach Type");
                        g2.setError("please fill Number of Coach and Coach Type");
                        g3.setError("please fill Number of Coach and Coach Type");
                        g4.setError("please fill Number of Coach and Coach Type");
                        g5.setError("please fill Number of Coach and Coach Type");
                        g6.setError("please fill Number of Coach and Coach Type");
                    }
                    if ((g1c.getText().toString().trim().length() == 0) && (g2c.getText().toString().trim().length() == 0)&& (g3c.getText().toString().trim().length() == 0) && (g4c.getText().toString().trim().length() == 0) && (g5c.getText().toString().trim().length() == 0) && (g6c.getText().toString().trim().length() == 0))
                    {
                        g3c.setError("please fill Number of Coach and Coach Type");
                        g1c.setError("please fill Number of Coach and Coach Type");
                        g4c.setError("please fill Number of Coach and Coach Type");
                        g2c.setError("please fill Number of Coach and Coach Type");
                        g5c.setError("please fill Number of Coach and Coach Type");
                        g6c.setError("please fill Number of Coach and Coach Type");
                    }
                }
                else
                {
                    String t = ccy1.getText().toString();
                    cc1 = Integer.parseInt(t);
                }
                if((occ1!=0)&&(cc1!=0)) {
                    float z = (float) occ1 / (float) cc1;
                    average = (int) (z * 100);
                    if((listView.getText().toString().equals(""))&&(cc1!=0)&&(occ1!=0)) {
                        String id = savetask.push().getKey();
                        Adddata1 artist = new Adddata1(Welcomescreen.div, Welcomescreen.sta, todayAsString, listView.getText().toString(), cc1, occ1, average);
                        savetask.child(id).setValue(artist);
                    }
                    else
                        Toast.makeText(Fucounting.this,"Please enter proper values",Toast.LENGTH_SHORT).show();
                }
            }
        });
        g3c.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    g4c.setError(null);
                    g2c.setError(null);
                    g1c.setError(null);
                    g5c.setError(null);
                    g6c.setError(null);
                    sundar1();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        g1c.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    g4c.setError(null);
                    g2c.setError(null);
                    g3c.setError(null);
                    g5c.setError(null);
                    g6c.setError(null);
                    sundar1();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        g2c.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    sundar1();
                    g4c.setError(null);
                    g3c.setError(null);
                    g1c.setError(null);
                    g5c.setError(null);
                    g6c.setError(null);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        g4c.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    sundar1();
                    g3c.setError(null);
                    g2c.setError(null);
                    g1c.setError(null);
                    g5c.setError(null);
                    g6c.setError(null);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        g5c.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    g4c.setError(null);
                    g2c.setError(null);
                    g1c.setError(null);
                    g3c.setError(null);
                    g6c.setError(null);
                    sundar1();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        g6c.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    g4c.setError(null);
                    g2c.setError(null);
                    g1c.setError(null);
                    g5c.setError(null);
                    g3c.setError(null);
                    sundar1();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
       g1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
       {
           @Override
           public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
           {
               sundar1();
           }
       });
        g2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                sundar1();
            }
        });
        g3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                sundar1();
            }
        });
        g4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                sundar1();
            }
        });
        g5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                sundar1();
            }
        });
        g6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                sundar1();
            }
        });

        for(int i=0;i<Welcomescreen.sta.length();i++)
        {
            int za=0;
            c=Welcomescreen.sta.substring(za+i,za+i+1);
            if(c.equals("("))
            {
                    code = Welcomescreen.sta.substring(i+1, (Welcomescreen.sta.length()-1));
            }
        }
        code=code.toLowerCase();
        request = new Request.Builder()
                .url("https://api.railwayapi.com/v2/arrivals/station/"+code+"/hours/4/apikey/k8792aaq6q/")
                .build();
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
                    for(int i=0;i<trainDetailsResponse.getTotal();i++)
                    {
                        String trainname = trainDetailsResponse.getTrains().get(i).getNumber()+"\t"+trainDetailsResponse.getTrains().get(i).getName();
                        list.add(trainname);
                    }
                    Fucounting.this.runOnUiThread(new Runnable() {
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
    public void sundar1()
    {
        int nugs12=0,nugsrd12=0,nugslrd12=0,nugs112=0,nugslr12=0,nugsld12=0;
        if(g4.isChecked()) {
            if (!g4c.getText().toString().equals(""))
                nugs12 = Integer.parseInt(g4c.getText().toString()) * 90;
            g4.setError(null);
            g3.setError(null);
            g2.setError(null);
            g1.setError(null);
            g5.setError(null);
            g6.setError(null);
        }
        if(g2.isChecked()) {
            if (!g2c.getText().toString().equals("")) {
                nugsrd12 = Integer.parseInt(g2c.getText().toString()) * 60;
            }
            g4.setError(null);
            g3.setError(null);
            g2.setError(null);
            g1.setError(null);
            g5.setError(null);
            g6.setError(null);
        }
        if(g3.isChecked()) {
            if (!g3c.getText().toString().equals("")) {
                nugslrd12 = Integer.parseInt(g3c.getText().toString()) * 20;
            }
            g4.setError(null);
            g3.setError(null);
            g2.setError(null);
            g1.setError(null);
            g5.setError(null);
            g6.setError(null);
        }
        if(g5.isChecked()) {
            if (!g5c.getText().toString().equals("")) {
                nugslr12 = Integer.parseInt(g5c.getText().toString()) * 42;
            }
            g4.setError(null);
            g3.setError(null);
            g2.setError(null);
            g1.setError(null);
            g5.setError(null);
            g6.setError(null);
        }
        if(g1.isChecked()) {
            if (!g1c.getText().toString().equals("")) {
                nugs112 = Integer.parseInt(g1c.getText().toString()) * 60;
            }
            g4.setError(null);
            g3.setError(null);
            g2.setError(null);
            g1.setError(null);
            g5.setError(null);
            g6.setError(null);
        }
        if(g6.isChecked()) {
            if (!g6c.getText().toString().equals("")) {
                nugsld12 = Integer.parseInt(g6c.getText().toString()) * 108;
            }
            g4.setError(null);
            g3.setError(null);
            g2.setError(null);
            g1.setError(null);
            g5.setError(null);
            g6.setError(null);
        }
        ccy1.setText(""+(nugs12+nugsrd12+nugslrd12+nugs112+nugslr12+nugsld12));
    }
}
