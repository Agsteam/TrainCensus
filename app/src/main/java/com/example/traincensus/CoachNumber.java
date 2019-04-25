package com.example.traincensus;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
public class CoachNumber extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    public ArrayList<EditModel> editModelArrayList;
    static int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_number);
        recyclerView =  findViewById(R.id.recyclerViewcoach);
        flag=0;
        editModelArrayList = populateList();
        customAdapter = new CustomAdapter(this,editModelArrayList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }
    private ArrayList<EditModel> populateList()
    {
        ArrayList<EditModel> list = new ArrayList<>();
        for(int i = 0; i < getIntent().getIntExtra("co",-1); i++)
        {
            EditModel editModel = new EditModel();
            editModel.setEditTextValue("");
            list.add(editModel);
        }
        return list;
    }
    @Override
    public void onBackPressed ()
    {
            for (int jk = 0; jk < CustomAdapter.editModelArrayList.size(); jk++) {
                if ((getIntent().getIntExtra("fla", 0)) == 1)
                    Fucounting.gsa1.setText(Fucounting.gsa1.getText() + " " + CustomAdapter.editModelArrayList.get(jk).getEditTextValue() + ",");
                if ((getIntent().getIntExtra("fla", 0)) == 2)
                    Fucounting.gsrda1.setText(Fucounting.gsrda1.getText() + " " + CustomAdapter.editModelArrayList.get(jk).getEditTextValue() + ",");
                if ((getIntent().getIntExtra("fla", 0)) == 3)
                    Fucounting.wgscza1.setText(Fucounting.wgscza1.getText() + " " + CustomAdapter.editModelArrayList.get(jk).getEditTextValue() + ",");
                if ((getIntent().getIntExtra("fla", 0)) == 4)
                    Fucounting.gsra1.setText(Fucounting.gsra1.getText() + " " + CustomAdapter.editModelArrayList.get(jk).getEditTextValue() + ",");
                if ((getIntent().getIntExtra("fla", 0)) == 5)
                    Fucounting.gslra1.setText(Fucounting.gslra1.getText() + " " + CustomAdapter.editModelArrayList.get(jk).getEditTextValue() + ",");
                if ((getIntent().getIntExtra("fla", 0)) == 6)
                    Fucounting.gslrda1.setText(Fucounting.gslrda1.getText() + " " + CustomAdapter.editModelArrayList.get(jk).getEditTextValue() + ",");
            }
        finish();

    }

}
