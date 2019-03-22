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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traincensus.Model.TrainDetailsResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    int nugs,nugsrd,nugslrd,nugs1,nugslr,nugsld;
    String c,code="";
    CheckBox g1,g2,g3,g4,g5,g6;
    EditText g1c,g2c,g3c,g4c,g5c,g6c;
    TextView total;
    AutoCompleteTextView listView;
    List<String> list;
    Button trains1;
    ArrayAdapter<String> adapter;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    Request request;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fucounting);
        trains1=(Button)findViewById(R.id.button);
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
        total=(TextView)findViewById(R.id.tc);
        listView = (AutoCompleteTextView) findViewById(R.id.tno);
        super.onStart();
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(g1.isChecked()==true)
                    if(!g1c.getText().toString().equals(""))
                    {
                            nugs = Integer.parseInt(g1c.getText().toString());
                            Log.e("sun", "sun" + Integer.parseInt(g1c.getText().toString()));

                    }
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
                        public void run() {
                            adapter = new ArrayAdapter<String>(Fucounting.this, android.R.layout.simple_list_item_1, list);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
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
}
