package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.reflect.Array.getInt;

public class secondActivity extends AppCompatActivity {

    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        Intent gi = getIntent();
        double x1 = Double.valueOf(gi.getDoubleExtra("n",1));

        tv1.setText(""+x1);

    }



}

