package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements signup_frag_name.FragmentChangeListener,signup_frag_email.FragmentChangePassword,
        signup_frag_join.FragmentChangename,signup_frag_birthday.FragmentChangeGender,
        signup_frag_gender.FragmentChangeEmail {

    Toolbar toolbar;

    //// fragments /////
    private signup_frag_birthday birthday;
    private signup_frag_email email;
    private signup_frag_gender gender;
    private signup_frag_password password;
    private signup_frag_join join;
    private signup_frag_name name;
    private Fragment fragment;
    int i;



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


        init();




    }



    void init()
    {
        name = new signup_frag_name();
        email = new signup_frag_email();
        birthday = new signup_frag_birthday();
        password = new signup_frag_password();
        join = new signup_frag_join();
        gender = new signup_frag_gender();
        i =0;
        fragment = join;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void replacePassword(Fragment fragment) {
        fragment = password;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void replaceFragment(Fragment fragment) {

        fragment = birthday;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void replacename(Fragment fragment) {
        fragment = name;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();

    }

    @Override
    public void replaceGender(Fragment fragment) {
        fragment = gender;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void replaceEmail(Fragment fragment) {
        fragment = email;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }
}
