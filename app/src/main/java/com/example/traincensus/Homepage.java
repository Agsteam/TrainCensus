package com.example.traincensus;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.traincensus.Splashscreen.mpref;

public class Homepage extends AppCompatActivity
{
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        image = findViewById(R.id.imageView3);
        TextView tv =  findViewById(R.id.mark);
        tv.setSelected(true);
        tv.setSingleLine();
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("PALAKKAD"))
            image.setImageResource(R.drawable.pgt);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("CHENNAI"))
            image.setImageResource(R.drawable.chennai);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("TRICHY"))
            image.setImageResource(R.drawable.trichy);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("MADURAI"))
            image.setImageResource(R.drawable.madurai);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("SALEM"))
            image.setImageResource(R.drawable.salem);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("TRIVANDRUM"))
            image.setImageResource(R.drawable.tvc);
    }
    @Override
    public void onBackPressed () {
        if(!Splashscreen.mpref.getString("Name", "").toUpperCase().equals("HEAD QUARTERS")) {
            AlertDialog.Builder a = new AlertDialog.Builder(new ContextThemeWrapper(Homepage.this, R.style.MyAlertDialogStyle));
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
        else
        {

            SharedPreferences.Editor editor=mpref.edit();
            editor.remove("Division");
            String a="HEAD";
            editor.putString("Division",a);
            editor.apply();
            Log.e("sun",Splashscreen.mpref.getString("Division", ""));
            Intent intent=new Intent(Homepage.this,ViewDivisions.class);
            startActivity(intent);
            finish();
        }
    }
    public void dvsun(View view) {
        Button button = findViewById(R.id.update);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Intent intent = new Intent(Homepage.this, Update.class);
        startActivity(intent);
        finish();
    }
    public void dvsun1(View view) {
        Button button = findViewById(R.id.Nomi);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Intent intent=new Intent(Homepage.this,Divassign.class);
        startActivity(intent);
        finish();
    }
    public void dvsun2(View view) {
        Button button = findViewById(R.id.Viewre);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Intent intent = new Intent(Homepage.this, ViewOptionActivity.class);
        startActivity(intent);
        finish();
    }
    public void dvsun3(View view) {
        Button button = findViewById(R.id.port);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        Intent intent = new Intent(Homepage.this, Repdiv.class);
        startActivity(intent);
        finish();
    }
    public void dvsun4(View view) {
        Button button = findViewById(R.id.logf);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceinterpolator interpolator = new MyBounceinterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);
        AlertDialog.Builder a = new AlertDialog.Builder(new ContextThemeWrapper(Homepage.this, R.style.MyAlertDialogStyle));
        a.setMessage("Are you sure you want to Logout ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Homepage.this, Mainscreen.class);
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
        alert.setTitle("Alert !!!");
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



