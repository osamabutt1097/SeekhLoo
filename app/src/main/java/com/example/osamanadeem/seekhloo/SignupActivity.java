package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Toolbar toolbar;
    private ViewPager viewPager;

    //// fragments /////
    private signup_frag_birthday birthday;
    private signup_frag_email email;
    private signup_frag_gender gender;
    private signup_frag_password password;
    private signup_frag_join join;
    private signup_frag_name name;



    @Override
    public boolean onSupportNavigateUp() {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //toolbar.setTitle("Signup");
        getSupportActionBar().setTitle("SignUp");

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        init();
        setupViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(join);
        adapter.addFragment(name);
        adapter.addFragment(birthday);
        adapter.addFragment(gender);
        adapter.addFragment(email);
        adapter.addFragment(password);
        viewPager.setAdapter(adapter);
    }

    void init()
    {
        name = new signup_frag_name();
        email = new signup_frag_email();
        birthday = new signup_frag_birthday();
        password = new signup_frag_password();
        join = new signup_frag_join();
        gender = new signup_frag_gender();
    }
}
