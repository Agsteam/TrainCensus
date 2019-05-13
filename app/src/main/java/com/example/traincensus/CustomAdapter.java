package com.example.traincensus;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    private LayoutInflater inflater;
    public static ArrayList<EditModel> editModelArrayList;
    public CustomAdapter(Context ctx, ArrayList<EditModel> editModelArrayList)
    {
        inflater = LayoutInflater.from(ctx);
        this.editModelArrayList = editModelArrayList;
    }
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.rv_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final CustomAdapter.MyViewHolder holder, final int position)
    {
        holder.editText.setHint(editModelArrayList.get(position).getEditTextValue());
    }
    @Override
    public int getItemCount()
    {
        return editModelArrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        protected EditText editText,atc;
        protected Spinner spinner;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            spinner=itemView.findViewById(R.id.spi_coachtype);
            editText = itemView.findViewById(R.id.edit_no);
            atc=itemView.findViewById(R.id.edit_atc);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String div=parent.getItemAtPosition(position).toString();
                    editModelArrayList.get(getAdapterPosition()).setSpinnerValue(div);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            editText.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    editModelArrayList.get(getAdapterPosition()).setEditTextValue(editText.getText().toString());
                }
                @Override
                public void afterTextChanged(Editable editable) {}
            });
            atc.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    editModelArrayList.get(getAdapterPosition()).setEdit_ActualValue(atc.getText().toString());
                }
                @Override
                public void afterTextChanged(Editable editable) {}
            });
        }

    }
}
