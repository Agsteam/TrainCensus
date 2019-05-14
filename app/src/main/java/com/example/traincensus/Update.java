package com.example.traincensus;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;


public class Update extends AppCompatActivity {
    DatabaseHelper myDb;
    Cursor cur;
    EditText editName,editpf,editmobile ;
    Button btnAddData;
    ListView listView;
    ArrayList<Updateclass> list;
    ArrayList<String>arrayList;
    Updateclass updateclass;
    ArrayAdapter adapter;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        myDb = new DatabaseHelper(this);
        editName = findViewById(R.id.nameview);
        editpf = findViewById(R.id.Pfno);
        editmobile = findViewById(R.id.mobileview);
        btnAddData = findViewById(R.id.submit);
        listView=findViewById(R.id.staffrec);
        myDb = new DatabaseHelper(this);
        AddData();
       editName.setFilters(new InputFilter[]
       {
         new InputFilter()
         {
           public CharSequence filter(CharSequence src, int start,int end, Spanned dst, int dstart, int dend)
           {
              if (src.equals(""))
              { // for backspace
                   return src;
              }
              if (src.toString().matches("[a-zA-Z. ]+"))
              {
                 return src;
              }
              return "";
           }
         }
       });
       InputFilter[] editFilters = editName.getFilters();
       InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
       System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
       newFilters[editFilters.length] = new InputFilter.AllCaps();
       editName.setFilters(newFilters);
       InputFilter[] leditFilters =editName.getFilters();
       InputFilter[] lnewFilters = new InputFilter[leditFilters.length + 1];
       System.arraycopy(leditFilters, 0, lnewFilters, 0, leditFilters.length);
       lnewFilters[leditFilters.length] = new InputFilter.LengthFilter(35);
       editName.setFilters(lnewFilters);
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if((editpf.getText().toString().trim().length()>0)&&(editName.getText().toString().trim().length()>0)&&(editmobile.getText().toString().trim().length()>0&&editmobile.getText().toString().trim().length()<=10))
                        {
                            boolean isInserted = myDb.insertData(editName.getText().toString(),editpf.getText().toString(),editmobile.getText().toString());
                            if (isInserted)
                            {
                                Toast toast=Toast.makeText(Update.this, "Data Inserted", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                                toast.show();
                                editName.setText("");
                                editmobile.setText("");
                                editpf.setText("");
                                editpf.requestFocus();
                                sun();
                            }
                        }
                        else {
                            Toast toast=Toast.makeText(Update.this, "Data not Inserted", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                            toast.show();
                        }
                    }
                }


        );
    }
    public void onBackPressed()
    {
        Intent intent=new Intent(Update.this,Homepage.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public  void sun()
    {
        cur=myDb.getAllData();
        list=new ArrayList<>();
        arrayList=new ArrayList<>();
        if(cur!=null&&cur.getCount()>0)
        {
            while (cur.moveToNext())
            {
                int id=cur.getInt(0);
                String n1=cur.getString(1);
                String pf1=cur.getString(2);
                String mo1=cur.getString(3);
                updateclass=new Updateclass(id,n1,pf1,mo1);
                list.add(updateclass);
                arrayList.add(n1);
            }
            adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);
        }
    }

}