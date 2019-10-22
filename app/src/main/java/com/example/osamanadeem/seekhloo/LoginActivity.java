package com.example.osamanadeem.seekhloo;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser currentFirebaseUser;
    private Button login,signup;
    private EditText email, pass;
    private LottieAnimationView lottieAnimationView;
    private UserInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }



    void init()
    {
        login = findViewById(R.id.loginbutton);
        signup = findViewById(R.id.signupbutton);
        email = findViewById(R.id.emailLogin);
        pass = findViewById(R.id.passwordLogin);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        lottieAnimationView = findViewById(R.id.load_login);

    }

    public void Login(final View view) {

        if(!email.getText().toString().isEmpty() || !pass.getText().toString().isEmpty()) {
            final InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            lottieAnimationView.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();

                                ////////////////////////Fetch User Type ////////////////////////////

                                currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                mDatabase.child("Tutor").child(currentFirebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.exists()) {
                                            info = dataSnapshot.getValue(UserInfo.class);

                                            if (info.getType().equals("student")) {
                                                startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                                                finish();
                                            } else if (info.getType().equals("tutor")) {
                                                startActivity(new Intent(LoginActivity.this, TutorActivity.class));
                                                // Toast.makeText(LoginActivity.this, "Tutor Type", Toast.LENGTH_SHORT).show();
                                            } else if (info.getType().equals("admin")) {
                                                startActivity(new Intent(LoginActivity.this, AdminPanelActivity.class));
                                                finish();
                                            } else {
                                                lottieAnimationView.setVisibility(View.GONE);
                                                Toast.makeText(LoginActivity.this, "Login Again", Toast.LENGTH_SHORT).show();
                                            }
                                            return;
                                        }
                                        else
                                        {
                                            return;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                mDatabase.child("Student").child(currentFirebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.exists()) {
                                            info = dataSnapshot.getValue(UserInfo.class);

                                            if (info.getType().equals("student")) {
                                                startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                                                finish();
                                            } else if (info.getType().equals("tutor")) {
                                                startActivity(new Intent(LoginActivity.this, TutorActivity.class));
                                                // Toast.makeText(LoginActivity.this, "Tutor Type", Toast.LENGTH_SHORT).show();
                                            } else if (info.getType().equals("admin")) {
                                                startActivity(new Intent(LoginActivity.this, AdminPanelActivity.class));
                                                finish();
                                            } else {
                                                lottieAnimationView.setVisibility(View.GONE);
                                                Toast.makeText(LoginActivity.this, "Login Again", Toast.LENGTH_SHORT).show();
                                            }
                                            return;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            } else {
                                lottieAnimationView.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user.
                                Snackbar.make(view, "Email/Password incorrect!", Snackbar.LENGTH_LONG).show();
                                pass.setText(null);
                            }


                        }
                    });

        }

        else
            Snackbar.make(view,"Enter valid Email/Password",Snackbar.LENGTH_LONG).show();

        }

    public void SignUp(View view) {
        startActivity(new Intent(this,SignupActivity.class));
        finish();
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        lottieAnimationView.setVisibility(View.VISIBLE);
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
        {

            mDatabase.child("Tutor").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        info = dataSnapshot.getValue(UserInfo.class);

                        if (info.getType().equals("student")) {
                            startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                            finish();
                        } else if (info.getType().equals("tutor")) {
                            startActivity(new Intent(LoginActivity.this, TutorActivity.class));
                            // Toast.makeText(LoginActivity.this, "Tutor Type", Toast.LENGTH_SHORT).show();
                        } else if (info.getType().equals("admin")) {
                            startActivity(new Intent(LoginActivity.this, AdminPanelActivity.class));
                            finish();
                        } else {
                            lottieAnimationView.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Login Again", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                    else
                    {
                        return;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mDatabase.child("Student").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        info = dataSnapshot.getValue(UserInfo.class);

                        if (info.getType().equals("student")) {
                            startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                            finish();
                        } else if (info.getType().equals("tutor")) {
                            startActivity(new Intent(LoginActivity.this, TutorActivity.class));
                            // Toast.makeText(LoginActivity.this, "Tutor Type", Toast.LENGTH_SHORT).show();
                        } else if (info.getType().equals("admin")) {
                            startActivity(new Intent(LoginActivity.this, AdminPanelActivity.class));
                            finish();
                        } else {
                            lottieAnimationView.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Login Again", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else
        {
            lottieAnimationView.setVisibility(View.GONE);
        }
    }
}



