package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    private TextView beerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        beerLabel = findViewById(R.id.beerLabel_TextView);
        Intent intent = getIntent();
        beerLabel.setText(intent.getStringExtra("beer"));
    }
}
