package com.example.osamanadeem.seekhloo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements signup_frag_name.FragmentChangeBirthday,signup_frag_email.FragmentChangePassword,
        signup_frag_join.FragmentChangetype,signup_frag_birthday.FragmentChangeGender,
        signup_frag_gender.FragmentChangeEmail, signup_frag_password.UploadData, signup_frag_type.FragmentChangeName {

    Toolbar toolbar;

    //// fragments /////
    private FirebaseAuth mAuth;
    private signup_frag_birthday birthday;
    private signup_frag_type type;
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
        type = new signup_frag_type();
        i =0;
        mAuth = FirebaseAuth.getInstance();
        fragment = join;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void replacePassword(Fragment fragment) {
        getSupportActionBar().setTitle("Password");
        fragment = password;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void replaceBirthday(Fragment fragment) {

        getSupportActionBar().setTitle("Birthday");
        fragment = birthday;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void replacetype(Fragment fragment) {
        getSupportActionBar().setTitle("Type");
        fragment = type;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();

    }

    @Override
    public void replaceGender(Fragment fragment) {
        getSupportActionBar().setTitle("Gender");
        fragment = gender;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void replaceEmail(Fragment fragment) {
        getSupportActionBar().setTitle("Email");
        fragment = email;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }

    @Override
    public void tofirebase() {


        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);




        mAuth.createUserWithEmailAndPassword(prefs.getString("email",null), prefs.getString("password",null))
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();




                            //////////////////inserting data to database///////////

                            UserInfo info = new UserInfo(prefs.getString("type",null),prefs.getString("email",null),prefs.getString("birthday",null),prefs.getString("fname",null),prefs.getString("lname",null));
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference();
                            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                            myRef.child("User" +
                                    "").child(currentFirebaseUser.getUid()).setValue(info, new DatabaseReference.CompletionListener() {
                                public void onComplete(DatabaseError error, DatabaseReference ref) {
                                    Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                    finish();

                                }
                            });

                            ////////////////////////////////////////////////////////




                            Snackbar.make(findViewById(android.R.id.content),"User Created",Snackbar.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Snackbar.make(findViewById(android.R.id.content),"Error Signing Up",Snackbar.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });

        //startActivity(new Intent(this,LoginActivity.class));
        //finish();
    }

    @Override
    public void replaceName(Fragment fragment) {
        getSupportActionBar().setTitle("Name");
        fragment = name;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.signup_frag, fragment);
        transaction.commit();
    }
}
