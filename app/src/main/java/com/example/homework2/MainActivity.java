package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private Button start_button;
    private ImageView imageView;
    private URLEncoder URLEncorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Picasso.get().load("~/assets/beer.jpeg");
//        for(int i = 0; i < 5; i++) {
//            System.out.println("hi");
//        }

//        File file = new File("/Users/tammieoh/AndroidStudioProjects/Homework2/app/src/main/assets/images/beer.jpeg");
        start_button = findViewById(R.id.start_button);
        imageView = findViewById(R.id.home_imageView);
//        Picasso.get().load("file://beer.jpeg").into(imageView);
        String file = "file:///android_asset/images/beer.jpeg";
        Picasso.get().load(file).into(imageView);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextActivity(v);
            }
        });
    }

    public void launchNextActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}