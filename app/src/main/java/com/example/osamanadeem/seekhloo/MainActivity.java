package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String b = "Hello";
        Toast.makeText(this, b, Toast.LENGTH_SHORT).show();
    }
}
