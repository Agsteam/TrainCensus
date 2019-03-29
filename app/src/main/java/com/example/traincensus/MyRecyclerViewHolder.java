package com.example.traincensus;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder
{
    public TextView wa,we,wr;
    public Button de;
    public MyRecyclerViewHolder(View itemView)
    {
        super(itemView);
        wa=itemView.findViewById(R.id.nameview);
        we=itemView.findViewById(R.id.stationview);
        wr=itemView.findViewById(R.id.divnameview);
    }
}
