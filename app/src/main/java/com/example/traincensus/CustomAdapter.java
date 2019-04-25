package com.example.traincensus;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    public void onBindViewHolder(final CustomAdapter.MyViewHolder holder, final int position) {
        holder.editText.setText(editModelArrayList.get(position).getEditTextValue());
    }
    @Override
    public int getItemCount() {
        return editModelArrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
    protected EditText editText;
    public MyViewHolder(View itemView)
    {
       super(itemView);
       editText = (EditText) itemView.findViewById(R.id.editid);
       editText.addTextChangedListener(new TextWatcher()
       {
       @Override
       public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
       @Override
       public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          editModelArrayList.get(getAdapterPosition()).setEditTextValue(editText.getText().toString());
       }
       @Override
       public void afterTextChanged(Editable editable) { }
       });
    }

    }
}
