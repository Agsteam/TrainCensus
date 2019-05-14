package com.example.traincensus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class Stationrepfield extends RecyclerView.Adapter<Stationrepfield.WordViewHolder>
{

        private ArrayList<String> st;
        ArrayList<Integer> j;
        ArrayList<Integer> per;
        private LayoutInflater mInflater;

        public Stationrepfield(Context context, ArrayList<String> st, ArrayList<Integer> j, ArrayList<Integer> per)
        {
            mInflater = LayoutInflater.from(context);
            this.st = st;
            this.j = j;
            this.per = per;
        }
        @NonNull
        @Override
        public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View mItemView = mInflater.inflate(R.layout.activity_stationrepfield, parent, false);
            return new WordViewHolder(mItemView, this);
        }
        @Override
        public void onBindViewHolder(@NonNull Stationrepfield.WordViewHolder wordViewHolder1, int i) {
            String mCurrent =st.get(i);
            int count=j.get(i);
            int percentage=per.get(i);
            wordViewHolder1.wordItemView.setText(mCurrent);
            wordViewHolder1.wordItemView1.setText(""+count);
            wordViewHolder1.wordItemView2.setText(""+percentage);
        }
        @Override
        public int getItemCount()
        {
            return st.size();
        }
        public class WordViewHolder extends RecyclerView.ViewHolder
        {
            public final TextView wordItemView,wordItemView1,wordItemView2;
            final com.example.traincensus.Stationrepfield mAdapter;
            public WordViewHolder(@NonNull View itemView, com.example.traincensus.Stationrepfield adapter)
            {
                super(itemView);
                wordItemView = itemView.findViewById(R.id.ttno);
                wordItemView1=itemView.findViewById(R.id.acc);
                wordItemView2=itemView.findViewById(R.id.occ1);
                this.mAdapter = adapter;
            }
        }
    }