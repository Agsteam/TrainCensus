package com.example.traincensus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {


    int SPLASH_TIME = 3000; //This is 4 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {

                //Do any action here. Now we are moving to next page

                Intent mySuperIntent = new Intent(Splashscreen.this, Mainscreen.class);

                startActivity(mySuperIntent);

                /* This 'finish()' is for exiting the app when back button pressed

                 *  from Home page which is ActivityHome

                 */

                finish();

            }

        }, SPLASH_TIME);

    }

}



