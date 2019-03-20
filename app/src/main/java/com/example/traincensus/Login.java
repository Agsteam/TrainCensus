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
    EditText pf1, dob1;
    TextView hdloginlable;
    Button singin1;
    String loginpf,loginpass,a,name="",actype;
    FirebaseFirestore database1=FirebaseFirestore.getInstance();
    DocumentReference noteRef=database1.document("");
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hdloginlable = (TextView) findViewById(R.id.textView5);
        hdloginlable.setText(getIntent().getStringExtra("lable"));
        pf1=(EditText)findViewById(R.id.pfno);
        dob1=(EditText)findViewById(R.id.dob);
        singin1 = (Button) findViewById(R.id.signin);
        singin1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                loginpf = pf1.getText().toString().trim();
                loginpass = dob1.getText().toString().trim();
                if (hdloginlable.getText().toString().equals("Divisional Admin")) {
                    a = "Div";
                } else if (hdloginlable.getText().toString().equals("Census Officer")) {
                    a = "field";
                }
                if (loginpf.length() > 0 && loginpass.length() > 0) {
                    database1.collection("Login").addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.d("sun", "error" + e.getMessage());
                            }
                            else
                            {
                                for (DocumentSnapshot doc : queryDocumentSnapshots)
                                {
                                    String pfvali = doc.getString("Pfno");
                                    String d = doc.getString("DOB");
                                    if (pfvali.equals(loginpf) && d.equals(loginpass))
                                    {
                                        name = doc.getString("Name");
                                        actype = doc.getString("Accestype");
                                    }
                                }
                                if(!name.equals("")&& !a.equals(actype))
                                {
                                    Toast.makeText(Login.this,"Your not authorised to login "+hdloginlable.getText().toString(),Toast.LENGTH_SHORT).show();
                                }
                                else if (!name.equals(""))
                                {
                                    Intent intent = new Intent(Login.this, Welcomescreen.class);
                                    SharedPreferences.Editor editor=mpref.edit();
                                    editor.putString("Username",loginpf);
                                    editor.putString("Password",loginpass);
                                    editor.putString("Accesstype",a);
                                    editor.putString("Name",name);
                                    editor.apply();
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(Login.this,"Please Check UserName and Password ",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}