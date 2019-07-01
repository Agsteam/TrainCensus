package com.example.traincensus;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class Reportclass extends RecyclerView.Adapter<Reportclass.WordViewHolder>
{
    private ArrayList<String> st;
    ArrayList<String> dt;
    ArrayList<Integer> tav;
    ArrayList<Integer> j;
    ArrayList<Integer> per;
    private LayoutInflater mInflater;
    public Reportclass(Context context,ArrayList<String> st, ArrayList<String> dt, ArrayList<Integer> tav, ArrayList<Integer> j, ArrayList<Integer> per) {
        mInflater = LayoutInflater.from(context);
        this.st = st;
        this.dt = dt;
        this.tav = tav;
        this.j = j;
        this.per = per;
    }
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View mItemView = mInflater.inflate(R.layout.activity_reportclass, parent, false);
        return new WordViewHolder(mItemView, this);
    }
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        int mCurrent =tav.get(i);
        int count=j.get(i);
        int percentage=per.get(i);
        String stat=st.get(i);
        wordViewHolder.wordItemView.setText(""+mCurrent);
        wordViewHolder.wordItemView1.setText(stat);
        wordViewHolder.wordItemView2.setText(""+count);
        wordViewHolder.wordItemView3.setText(""+percentage);
    }
    @Override
    public int getItemCount()
    {
        return tav.size();
    }
    public class WordViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView wordItemView,wordItemView1,wordItemView2,wordItemView3;
        final Reportclass mAdapter;
        public WordViewHolder(@NonNull View itemView, Reportclass adapter)
        {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView9);
            wordItemView1=itemView.findViewById(R.id.textView8);
            wordItemView2=itemView.findViewById(R.id.textView11);
            wordItemView3=itemView.findViewById(R.id.textView12);
            this.mAdapter = adapter;
        }
    }
}