package com.example.traincensus;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.traincensus.Splashscreen.mpref;

public class Login extends AppCompatActivity
{
    ProgressBar lprogress;
    EditText pf1, dob1;
    TextView hdloginlable,ins;
    Button singin1;
    String loginpf,loginpass,a,name="",actype,divname;
    FirebaseFirestore database1=FirebaseFirestore.getInstance();
    DocumentReference noteRef=database1.document("");
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lprogress=(ProgressBar)findViewById(R.id.progressBar3);
        hdloginlable = (TextView) findViewById(R.id.textView5);
        ins=(TextView)findViewById(R.id.textView6);
        hdloginlable.setText(getIntent().getStringExtra("lable"));
        pf1=(EditText)findViewById(R.id.pfno);
        dob1=(EditText)findViewById(R.id.dob);
        singin1 = (Button) findViewById(R.id.signin);
        lprogress.setVisibility(View.GONE);
        if (hdloginlable.getText().toString().equals("Division Admin"))
        {
            a = "Div";
            pf1.setHint("USER ID");
            dob1.setHint("PASSWORD");
            ins.setVisibility(View.VISIBLE);
            ins.setText("(Password will be issued by HQ Admin)");

        }
        else if (hdloginlable.getText().toString().equals("Census Officer"))
        {
            a = "field";
            pf1.setHint("USER ID IS YOUR PF NUMBER");
            dob1.setHint("PASSWORD IS YOUR MOBILE NUMBER");
        }
        singin1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                lprogress.setVisibility(View.VISIBLE);
                loginpf = pf1.getText().toString().trim();
                loginpass = dob1.getText().toString().trim();
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
                                    String pfvali = doc.getString("pfno");
                                    String d = doc.getString("dob");
                                    if (pfvali.equals(loginpf) && d.equals(loginpass))
                                    {
                                        name = doc.getString("name");
                                        actype = doc.getString("accestype");
                                        divname=doc.getString("division");
                                    }
                                }
                                if(!name.equals("")&& !a.equals(actype))
                                {
                                    Toast.makeText(Login.this,"Your not authorised to login "+hdloginlable.getText().toString(),Toast.LENGTH_SHORT).show();
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
                                    lprogress.setVisibility(View.GONE);
                                }
                                else
                                {
                                    Toast.makeText(Login.this,"Please Check UserName and Password ",Toast.LENGTH_SHORT).show();
                                    lprogress.setVisibility(View.GONE);
                                }
                            }
                        }
                    });
                }
                else if(loginpf.equals("")&&loginpass.equals(""))
                {
                    Toast.makeText(Login.this,"Please Fill UserName and Password",Toast.LENGTH_SHORT).show();
                    lprogress.setVisibility(View.GONE);
                }
                else if(loginpf.equals(""))
                {
                    Toast.makeText(Login.this,"Please Fill UserName",Toast.LENGTH_SHORT).show();
                    lprogress.setVisibility(View.GONE);
                }
                else if (loginpass.equals(""))
                {
                    Toast.makeText(Login.this,"Please Fill Password",Toast.LENGTH_SHORT).show();
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
}
