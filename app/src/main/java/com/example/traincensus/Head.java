package com.example.traincensus;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import static com.example.traincensus.Splashscreen.mpref;

public class Head extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        tv =  findViewById(R.id.mark);
        tv.setSelected(true);
        tv.setSingleLine();
    }
    @Override
    public void onBackPressed ()
    {
            AlertDialog.Builder a = new AlertDialog.Builder( new ContextThemeWrapper(Head.this,R.style.MyAlertDialogStyle));
            a.setMessage("Are you sure you want to exit ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a.create();
            alert.setTitle("Alert !!!");
            alert.show();
    }
    public void sun(View view)
    {
        Button button=findViewById(R.id.Viewre);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2,20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Intent intent = new Intent(Head.this, ViewDivisions.class);
        startActivity(intent);
        finish();
    }
    public void sun1(View view)
    {
        Button button=findViewById(R.id.port);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2,20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Intent intent = new Intent(Head.this, Report.class);
        startActivity(intent);
        finish();
    }
    public void sun2(View view)
    {
        Button button=findViewById(R.id.log);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2,20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        AlertDialog.Builder a = new AlertDialog.Builder( new ContextThemeWrapper(Head.this,R.style.MyAlertDialogStyle));
        a.setMessage("Are you sure you want to Logout ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Head.this, Mainscreen.class);
                        startActivity(intent);
                        finish();
                        SharedPreferences.Editor editor = mpref.edit();
                        editor.clear();
                        editor.apply();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a.create();
        alert.setTitle("Alert!!!");
        alert.show();
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