package com.example.myproject;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myproject.Databases.zapros_ThirdAct;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ThirdActivity extends AppCompatActivity {

    zapros_ThirdAct zapros;

    private EditText id;
    private String ids;
    private EditText Links;
    private Button button;
    private ImageView imageView;
    private Button QR_GEN;
    private String Link;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        id = findViewById(R.id.ID);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        Links = findViewById(R.id.Links);
        QR_GEN = findViewById(R.id.QR_GEN);
   button.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           ids = id.getText().toString();
           zapros = new zapros_ThirdAct();
           zapros.start(ids);

           try {
               zapros.join();
           } catch (InterruptedException ie) {
               Log.e("pass 0 ", ie.getMessage());
           }
           Link = zapros.resLinks();
           Links.setText(Link);
       }
   });
        QR_GEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text =  Links.getText().toString().trim();
                try {
                    if (text != null) {
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                     }


                     }catch (WriterException e){
                       e.printStackTrace();
                       finish();
                     }
            }
        });
    }
}