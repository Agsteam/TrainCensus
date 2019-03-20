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
import android.widget.ProgressBar;
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
        listView = (AutoCompleteTextView) findViewById(R.id.tno);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("test");
        strings.add("test1");
        final String[] COUNTRIES = new String[] {
                "Belgium", "France", "Italy", "Germany", "Spain"
        };
        adapter = new ArrayAdapter<String>(this, R.layout.auto_complete_item, COUNTRIES);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        super.onStart();
        request = new Request.Builder()
                .url("https://api.railwayapi.com/v2/arrivals/station/mas/hours/4/apikey/k8792aaq6q/")
                .build();
        trains1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        Log.e("sun1",list.get(i));
                    }
                    Fucounting.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new ArrayAdapter<String>(Fucounting.this, R.layout.auto_complete_item, list);
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
