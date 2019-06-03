package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproject.Adapters.MyAdapter;
import com.example.myproject.Databases.DbHelper;
import com.example.myproject.Model.ListItem;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ListItem> arrayList;
    MyAdapter myAdapter;
    DbHelper helper;
    private Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helper = new DbHelper(this);

        arrayList = helper.getAllInformation();

        if (arrayList.size() > 0) {
            myAdapter = new MyAdapter(arrayList, this);
            recyclerView.setAdapter(myAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }
        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                ListItem listItem = arrayList.get(position);


                helper.deleteRow(listItem.getId());

                arrayList.remove(position);
                myAdapter.notifyItemRemoved(position);
                myAdapter.notifyItemRangeChanged(position, arrayList.size());


            }
        }).attachToRecyclerView(recyclerView);

        final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setCameraId(0);


        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getApplicationContext(), "No result", Toast.LENGTH_SHORT).show();

            } else {
                boolean isInserted = helper.insertData(result.getFormatName(), result.getContents());

                if (isInserted) {
                    arrayList.clear();
                    arrayList = helper.getAllInformation();
                    myAdapter = new MyAdapter(arrayList, this);
                    recyclerView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();


                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}