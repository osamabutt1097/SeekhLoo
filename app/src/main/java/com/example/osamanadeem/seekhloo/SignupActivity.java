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
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.HashMap;

public class SignupActivity extends AppCompatActivity implements signup_frag_name.FragmentChangeBirthday,signup_frag_email.FragmentChangePassword,
        signup_frag_join.FragmentChangetype,signup_frag_birthday.FragmentChangeGender,
        signup_frag_gender.FragmentChangeAcademicInfo, signup_frag_password.UploadData, signup_frag_type.FragmentChangeName, signup_frag_academic.FragmentChangeEmail{

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
    private signup_frag_academic academic;
    private AcademicInfo academicInfo;
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
        academic = new signup_frag_academic();
        i =0;
        mAuth = FirebaseAuth.getInstance();
        fragment = join;
        academicInfo = new AcademicInfo();
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
    public void tofirebase() {
        getSupportActionBar().setTitle("Creating Your profile");

        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);




        mAuth.createUserWithEmailAndPassword(prefs.getString("email",null), prefs.getString("password",null))
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();




                            //////////////////inserting data to database///////////

                            UserInfo info = new UserInfo(FirebaseAuth.getInstance().getCurrentUser().getUid(),prefs.getString("type",null),prefs.getString("email",null),prefs.getString("dob",null),prefs.getString("fname",null),prefs.getString("lname",null),prefs.getString("gender",null));
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            final DatabaseReference myRef = database.getReference();
                            final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                            if (prefs.getString("type",null).equals("student"))
                            {
                                myRef.child("Student" +
                                        "").child(currentFirebaseUser.getUid()).setValue(info, new DatabaseReference.CompletionListener() {
                                    public void onComplete(DatabaseError error, DatabaseReference ref) {

                                        setAcademicInfo();
                                        myRef.child("Student").child(currentFirebaseUser.getUid()).child("Academia").setValue(academicInfo, new DatabaseReference.CompletionListener() {
                                            public void onComplete(DatabaseError error, DatabaseReference ref) {


                                                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                                finish();

                                            }
                                        });


                                        Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                        finish();

                                    }
                                });
                            }
                            else if (prefs.getString("type",null).equals("tutor"))
                            {
                                String token = FirebaseInstanceId.getInstance().getToken();
                                String picUrL ="https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/Tutor%2Fuser-icon-businessman-profile-man-avatar-vector-10552531.jpg?alt=media&token=9988fbad-b062-429c-a612-dc847581f8f1";
                                TutorInfo t_info = new TutorInfo(prefs.getString("type",null),prefs.getString("email",null),prefs.getString("dob",null),prefs.getString("fname",null),prefs.getString("lname",null),prefs.getString("gender",null),picUrL,FirebaseAuth.getInstance().getCurrentUser().getUid().toString());

                                final HashMap<String,Object>map = new HashMap<>();
                                map.put("token",token);
                                myRef.child("Tutor" +
                                        "").child(currentFirebaseUser.getUid()).setValue(t_info, new DatabaseReference.CompletionListener() {
                                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                                        myRef.updateChildren(map);
                                        setAcademicInfo();
                                        myRef.child("Tutor").child(currentFirebaseUser.getUid()).child("Academia").setValue(academicInfo, new DatabaseReference.CompletionListener() {
                                            public void onComplete(DatabaseError error, DatabaseReference ref) {


                                                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                                finish();

                                            }
                                        });


                                        Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                        finish();

                                    }
                                });
                            }



                            ////////////////////////////////////////////////////////




                            Snackbar.make(findViewById(android.R.id.content),"User Created",Snackbar.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this,"Error Signing Up",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                            finish();
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

    @Override
    public void replaceAcademicInfo(Fragment fragment) {
        getSupportActionBar().setTitle("Academic Info");
        fragment = academic;
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

    private void setAcademicInfo()
    {
        final SharedPreferences prefs = getSharedPreferences("AcademicInfo", MODE_PRIVATE);
        academicInfo.setSchoolcity(prefs.getString("schoolcity",null));
        academicInfo.setSchoolname(prefs.getString("schoolname",null));
        academicInfo.setSchoolRoll(prefs.getString("schoolRoll",null));
        academicInfo.setSchooltotal(prefs.getString("schooltotal",null));
        academicInfo.setScoolobtn(prefs.getString("scoolobtn",null));

        academicInfo.setCollegename(prefs.getString("collegename",null));
        academicInfo.setCollegecity(prefs.getString("collegecity",null));
        academicInfo.setCollegeobtn(prefs.getString("collegeobtn",null));
        academicInfo.setCollegetotal(prefs.getString("collegetotal",null));
        academicInfo.setCollegeRoll(prefs.getString("CollegeRoll",null));

        academicInfo.setUniversityname(prefs.getString("universityname",null));
        academicInfo.setUniversitydegree(prefs.getString("universitydegree",null));
        academicInfo.setUniversityGpa(prefs.getString("universityGpa",null));
        academicInfo.setUniversityMajor(prefs.getString("universityMajor",null));


        academicInfo.setMasteruniversityname(prefs.getString("Masteruniversityname",null));
        academicInfo.setMasteruniversitydegree(prefs.getString("Masteruniversitydegree",null));
        academicInfo.setMasteruniversityGpa(prefs.getString("MasteruniversityGpa",null));
        academicInfo.setMasteruniversityMajor(prefs.getString("MasteruniversityMajor",null));

        academicInfo.setPHDuniversityname(prefs.getString("PHDuniversityname",null));
        academicInfo.setPHDuniversitydegree(prefs.getString("PHDuniversitydegree",null));
        academicInfo.setPHDuniversityGpa(prefs.getString("PHDuniversityGpa",null));
        academicInfo.setPHDuniversityMajor(prefs.getString("PHDuniversityMajor",null));



    }
}
