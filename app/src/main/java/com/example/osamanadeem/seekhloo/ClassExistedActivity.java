package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.widget.TextView;

public class ClassExistedActivity extends AppCompatActivity {

   private TextView textView,name;
    private String n;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_stream:
                    textView.setText("Stream where student and tutor will communicate ");
                    return true;
                case R.id.navigation_tutor_selection:
                    textView.setText("Find Tutor here");
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_existed);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();



        textView = findViewById(R.id.typestudentclass);
        name = findViewById(R.id.classnam);
        if(b!=null)
        {
            n =(String) b.get("subjectname");
            name.setText(n);

        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.class_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_stream);



    }

}
