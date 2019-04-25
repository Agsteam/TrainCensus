package com.example.traincensus;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Homepage extends AppCompatActivity
{
    Button btnn,btnu,btnv,btnre;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        image=findViewById(R.id.imageView3);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("PALAKKAD"))
           image.setImageResource(R.drawable.pgt);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("CHENNAI"))
            image.setImageResource(R.drawable.chennai);;
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("TRICHY"))
            image.setImageResource(R.drawable.trichy);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("MADURAI"))
            image.setImageResource(R.drawable.madurai);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("SALEM"))
            image.setImageResource(R.drawable.salem);
        if (Splashscreen.mpref.getString("Division", "").toUpperCase().equals("TRIVANDRUM"))
            image.setImageResource(R.drawable.tvc);
        btnn=findViewById(R.id.Nomi);
        btnn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Homepage.this,Divassign.class);
                startActivity(intent);
            }
        });

        btnu=(Button)findViewById(R.id.update);
        btnu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Homepage.this,Update.class);
                startActivity(intent);
            }
        });

        btnv=(Button)findViewById(R.id.Viewre);
        btnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this,ViewOptionActivity.class);
                startActivity(intent);
            }
        });
        btnre=(Button)findViewById(R.id.port);
        btnre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this,Repdiv.class);
                startActivity(intent);
            }
        });
    }
}