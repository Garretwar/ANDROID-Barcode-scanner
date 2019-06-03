package com.example.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button btnReadQR;
    private Button btnReadBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        btnReadQR = findViewById(R.id.OpenQR);
        btnReadBD = findViewById(R.id.OpenBD);
        btnReadQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.SecondActivity");
                startActivity(intent);
            }
        });

        btnReadBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.ThirdActivity");
                startActivity(intent);

            }
        });


        }
    }

