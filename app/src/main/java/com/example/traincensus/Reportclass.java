package com.example.traincensus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class Reportclass extends RecyclerView.Adapter<Reportclass.WordViewHolder>
{
    ArrayList<String> st;
    ArrayList<String> dt;
    ArrayList<Integer> tav;
    ArrayList<Integer> j;
    ArrayList<Integer> per;
    private LayoutInflater mInflater;

    public Reportclass(
            ArrayList<String> st, ArrayList<String> dt, ArrayList<Integer> tav, ArrayList<Integer> j, ArrayList<Integer> per) {
        this.st = st;
        this.dt = dt;
        this.tav = tav;
        this.j = j;
        this.per = per;
    }

    public Reportclass(Context context,
                       ArrayList<String> st, ArrayList<String> dt, ArrayList<Integer> tav, ArrayList<Integer> j, ArrayList<Integer> per) {
        mInflater = LayoutInflater.from(context);
        this.st = st;
        this.dt = dt;
        this.tav = tav;
        this.j = j;
        this.per = per;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View mItemView = mInflater.inflate(R.layout.activity_reportclass, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        String mCurrent = st.get(i);
        wordViewHolder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return st.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        final Reportclass mAdapter;

        public WordViewHolder(@NonNull View itemView, Reportclass adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView9);
            //wordItemView1=itemView.findViewById(R.id.word1);
            this.mAdapter = adapter;
        }
    }
}