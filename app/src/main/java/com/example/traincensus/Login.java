package com.example.traincensus;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.traincensus.Splashscreen.mpref;

public class Login extends AppCompatActivity
{
    ProgressBar lprogress;
    EditText  dob1;
    AutoCompleteTextView pf1;
    TextView hdloginlable,ins;
    Button singin1;
    String loginpf,loginpass,a,name="",actype,divname;
    FirebaseFirestore database1=FirebaseFirestore.getInstance();
    List<String> list=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lprogress=findViewById(R.id.progressBar3);
        hdloginlable =  findViewById(R.id.textView5);
        ins=findViewById(R.id.textView6);
        hdloginlable.setText(getIntent().getStringExtra("lable"));
        pf1=findViewById(R.id.pfno);
        dob1=findViewById(R.id.dob);
        singin1 = findViewById(R.id.signin);
        lprogress.setVisibility(View.GONE);
        if (hdloginlable.getText().toString().equals("Division Admin"))
        {
            a = "Div";
            pf1.setHint("USER ID");
            dob1.setHint("PASSWORD");
            ins.setVisibility(View.VISIBLE);
            ins.setText(getString(R.string.password));
            list.add("SRDCMMAS");
            list.add("SRDCMTPJ");
            list.add("SRDCMMDU");
            list.add("SRDCMPGT");
            list.add("SRDCMSA");
            list.add("SRDCMTVC");
            ArrayAdapter<String> spin=new ArrayAdapter<>(Login.this,android.R.layout.simple_spinner_item,list);
            pf1.setAdapter(spin);


        }
        else if (hdloginlable.getText().toString().equals(getString(R.string.censusofficer)))
        {
            a = getString(R.string.field1);
            pf1.setHint(getString(R.string.user12));
            dob1.setHint(getString(R.string.pass1));
        }
        else if (hdloginlable.getText().toString().equals("Head Quarters"))
        {
            a = "HQ";
            pf1.setHint("USER ID");
            dob1.setHint("PASSWORD");
        }
        singin1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                lprogress.setVisibility(View.VISIBLE);
                loginpf = pf1.getText().toString().trim().toUpperCase();
                loginpass = dob1.getText().toString().trim().toUpperCase();
                if (loginpf.length() > 0 && loginpass.length() > 0)
                {
                    database1.collection("Login").addSnapshotListener(new EventListener<QuerySnapshot>()
                    {
                        @Override
                        public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e)
                        {
                            if (e != null)
                            {
                                Log.d("sun", "error" + e.getMessage());
                            }
                            else
                            {
                                for (DocumentSnapshot doc : queryDocumentSnapshots)
                                {
                                    String pfvali = Objects.requireNonNull(doc.getString("pfno")).toUpperCase();
                                    String d = Objects.requireNonNull(doc.getString("dob")).toUpperCase();
                                    if (pfvali.equals(loginpf) && d.equals(loginpass))
                                    {
                                        name = doc.getString("name");
                                        actype = doc.getString("accestype");
                                        divname=doc.getString("division");
                                    }
                                }
                                if(!name.equals("")&& !a.equals(actype))
                                {
                                   Toast toast= Toast.makeText(Login.this,"Your not authorised to login "+hdloginlable.getText().toString(),Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                                    toast.show();
                                    lprogress.setVisibility(View.GONE);
                                }
                                else if (!name.equals(""))
                                {
                                    Intent intent = new Intent(Login.this, Welcomescreen.class);
                                    SharedPreferences.Editor editor=mpref.edit();
                                    editor.putString("Username",loginpf);
                                    editor.putString("Password",loginpass);
                                    editor.putString("Accesstype",a);
                                    editor.putString("Name",name);
                                    editor.putString("Division",divname);
                                    editor.apply();
                                    startActivity(intent);
                                    finish();
                                    lprogress.setVisibility(View.GONE);
                                }
                                else
                                {
                                    Toast toast=Toast.makeText(Login.this,"Please Check UserName and Password ",Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                                    toast.show();
                                    lprogress.setVisibility(View.GONE);
                                }
                            }
                        }
                    });
                }
                else if(loginpf.equals("")&&loginpass.equals(""))
                {
                    Toast toast=Toast.makeText(Login.this,"Please Fill UserName and Password",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                    toast.show();
                    lprogress.setVisibility(View.GONE);
                }


else if(loginpf.equals(""))
            {
               Toast toast= Toast.makeText(Login.this,"Please Fill UserName",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                toast.show();
                lprogress.setVisibility(View.GONE);
            }
            else if (loginpass.equals(""))
            {
                Toast toast=Toast.makeText(Login.this,"Please Fill Password",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.START,90,0);
                toast.show();
                lprogress.setVisibility(View.GONE);
            }
            }
        });
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


    @Override
    public void onBackPressed ()
    {
        Intent intent=new Intent(Login.this,Mainscreen.class);
        startActivity(intent);
        finish();
    }

}
