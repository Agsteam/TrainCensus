 package com.example.traincensus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


 public class Mainscreen extends AppCompatActivity
{

    String loginlable;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
    }
    public void tapToAnimate(View view)
    {
        Button button=findViewById(R.id.hq);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2,20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        loginlable="Head Quarters";
        openLogin();
    }
    public void tapToAnimate1(View view)
    {
        Button button=findViewById(R.id.div);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2,20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        loginlable="Division Admin";
        openLogin();
    }
    public void tapToAnimate2(View view)
    {
        Button button=findViewById(R.id.fo);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2,20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        loginlable="Census Officer";
        openLogin();
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
    public void openLogin()
    {
       Intent intent = new Intent(Mainscreen.this, Login.class);
       intent.putExtra("lable",loginlable);
       startActivity(intent);
       finish();
    }
}