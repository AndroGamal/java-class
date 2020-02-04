package com.example.andro.church;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ImageView imageView, imageView1;
    MyAdapter_Recycler adapter_recycler;
    ArrayList<Contact> list;
    RecyclerView recyclerView;
    OpenHelperSQLite sqLite;
    Contact n;
    EditText name, addr, phane, Father_of_confession, Date_of_visit, childreen;
    AlertDialog al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sqLite = new OpenHelperSQLite(this, null, 1);
        recyclerView = findViewById(R.id.re);
        try {
            list = sqLite.all();
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        al = new AlertDialog.Builder(MainActivity.this).setNegativeButton("cansel", null).setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    name = al.findViewById(R.id.editText_name);
                    addr = al.findViewById(R.id.editText_addr);
                    phane = al.findViewById(R.id.editText_phane);
                    Father_of_confession = al.findViewById(R.id.editText_Father_of_confession);
                    Date_of_visit = al.findViewById(R.id.editText_Date_of_visit);
                    childreen = al.findViewById(R.id.editText_childreen);
                    n = new Contact(0, name.getText().toString(), addr.getText().toString(), phane.getText().toString(), Father_of_confession.getText().toString(), Date_of_visit.getText().toString(), Integer.parseInt(childreen.getText().toString()));
                    sqLite.insert(n);
                    list = sqLite.all();
                    adapter_recycler.updateData(list);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }).setView(getLayoutInflater().inflate(R.layout.add, null)).create();
        adapter_recycler = new MyAdapter_Recycler(this, R.layout.layout, list);
        recyclerView.setAdapter(adapter_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageView = findViewById(R.id.imageView3);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.add);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        al.show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imageView.startAnimation(animation);

            }
        });
        imageView1 = findViewById(R.id.imageView4);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView1.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.add));
            }
        });
    }
}

