package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button login,signup;
    EditText email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();



    }




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    void init()
    {
        login = findViewById(R.id.loginbutton);
        signup = findViewById(R.id.signupbutton);
        email = findViewById(R.id.emailLogin);
        pass = findViewById(R.id.passwordLogin);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Login(View view) {

        if(!email.getText().toString().isEmpty() || !pass.getText().toString().isEmpty()) {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "User Existed", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this,AdminPanelActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Failed!", Toast.LENGTH_LONG).show();

                            }

                            // ...
                        }
                    });

        }

        else
            Snackbar.make(view,"Enter valid Email/Password",Snackbar.LENGTH_LONG).show();
        }

    public void SignUp(View view) {


    }
}
