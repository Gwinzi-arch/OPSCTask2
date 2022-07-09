package com.sanelegwinyai.sblcollections;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;


    public class Login extends AppCompatActivity {

        EditText Et_Username;
        EditText Et_Password;
        Button Btn_Login;
        Button Btn_Signup;
        private FirebaseAuth mAuth;
        private FirebaseUser currentUser;
        private ProgressDialog progressDialog;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            mAuth = FirebaseAuth.getInstance();
            // check if person is not logged in, send them to login.
            currentUser = mAuth.getCurrentUser();
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Please wait");
            progressDialog.setCanceledOnTouchOutside(false);

            Et_Username = findViewById(R.id.et_username);
            Et_Password = findViewById(R.id.et_password);
            Btn_Login = findViewById(R.id.btn_login);
            Btn_Signup = findViewById(R.id.btn_signup);

            Btn_Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = Et_Username.getText().toString();
                    String password = Et_Password.getText().toString();

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        currentUser = mAuth.getCurrentUser();
                                        Toast.makeText(Login.this, "Login in successful...", Toast.LENGTH_SHORT).show();

                                        Intent next = new Intent(Login.this, Menu.class);
                                        startActivity(next);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                        task.getException();

                                    }
                                    }
                                });
                            }
                });

                    Btn_Signup.setOnClickListener(new View.OnClickListener() { // create new user
                        @Override
                        public void onClick(View view) {

                            String email = Et_Username.getText().toString();
                            String password = Et_Password.getText().toString();

                            mAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Toast.makeText(Login.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                                currentUser = mAuth.getCurrentUser();

                                                // go to next page if successful
                                                Intent next = new Intent(Login.this, Menu.class);
                                                startActivity(next);
                                                finish();

                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                                task.getException();


                                            }
                                        }
                                    });
                        }
                    });
        }
    }
