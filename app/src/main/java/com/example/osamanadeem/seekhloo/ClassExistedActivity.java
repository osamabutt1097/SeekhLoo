package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class ClassExistedActivity extends AppCompatActivity {

    private Switch aSwitch;
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
        aSwitch = findViewById(R.id.darkswitch);
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

    private void restartApp() {
        Intent i = new Intent(getApplicationContext(),ClassExistedActivity.class);
        startActivity(i);
        finish();
    }

}
