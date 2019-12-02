package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;

public class ClassExistedActivity extends AppCompatActivity {

    private Switch aSwitch;
    private  FragmentManager manager;
    private FragmentTransaction transaction;
    private classroom_stream_frag streamFrag;
    private frag_classroom_tutor tutor;
    private classroom_Tutor_frag tutorFrag;
    private classroom_Resources_frag resources_frag;
   private TextView textView,name;
   private Toolbar mToolbar;
    private String n;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_stream:
                    getSupportActionBar().setTitle("Stream");
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.classframe, streamFrag);
                    transaction.commit();

                    return true;
                case R.id.navigation_tutor:
                    getSupportActionBar().setTitle("Tutor");
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.classframe, tutor);
                    transaction.commit();

                    //startActivity(new Intent(ClassExistedActivity.this,SearchTutorActivity.class));
                    return true;
                case R.id.navigation_resources:
                    getSupportActionBar().setTitle("Resources");
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.classframe, resources_frag);
                    transaction.commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Dark panga
        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);

        String themeMode = prefs.getString("theme_preference","0");
        if (themeMode.equals("2"))
        {
            setTheme(R.style.DarkTheme);
        }
        else
            setTheme(R.style.AppTheme);
//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_existed);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.classroomtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Stream");
        /*
        // aSwitch = findViewById(R.id.darkswitch);
        //Again dark panga
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES   )
        {
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = getSharedPreferences("User", MODE_PRIVATE).edit();
                    editor.putString("theme_preference", "2");
                    editor.apply();
                    restartApp();
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = getSharedPreferences("User", MODE_PRIVATE).edit();
                    editor.putString("theme_preference", "1");
                    editor.apply();
                    restartApp();
                }
            }
        });
        //textView = findViewById(R.id.typestudentclass);
      //  name = findViewById(R.id.classnam);
        if(b!=null)
        {
            n =(String) b.get("subjectname");
            name.setText(n);

        }

         */


       init();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.class_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_stream);



    }


    private void restartApp() {
        Intent i = new Intent(getApplicationContext(),ClassExistedActivity.class);
        startActivity(i);
        finish();
    }

    public void init(){
        tutor = new frag_classroom_tutor();
        streamFrag = new classroom_stream_frag();
        resources_frag = new classroom_Resources_frag();
        tutorFrag = new classroom_Tutor_frag();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.classframe, streamFrag);
        transaction.commit();
    }

}
