package com.example.traincensus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Adaptercard1 extends FirestoreRecyclerAdapter<FirestoreDivision, Adaptercard1.NoteHolder> {

    public Adaptercard1(@NonNull FirestoreRecyclerOptions<FirestoreDivision> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull FirestoreDivision model) {
        holder.textViewName.setText(model.getName());
        holder.textViewStation.setText(model.getStation());
        String a = model.getDate1();
      //  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       // String formattedDate = df.format(a);
        holder.textViewDate.setText(a);
        holder.textViewShifin.setText(model.getShiftin());
        holder.textViewShiftout.setText(model.getShiftout());
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adaptercard1,
                parent, false);
        return new NoteHolder(v);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewStation;
        TextView textViewDate;
        TextView textViewShifin;
        TextView textViewShiftout;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.nameview);
            textViewStation = itemView.findViewById(R.id.stationview);
            textViewDate = itemView.findViewById(R.id.dateview);
            textViewShifin=itemView.findViewById(R.id.shiftinview);
            textViewShiftout=  itemView.findViewById(R.id.shiftoutview);
        }
    }
}