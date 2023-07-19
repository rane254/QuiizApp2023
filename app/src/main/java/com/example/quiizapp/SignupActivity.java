package com.example.quiizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class SignupActivity extends AppCompatActivity
{
    private TextView dSignup;
    private EditText editTextEmailAddress,editTextPassword2,editTextPassword3;
    private Button btnSignup;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(SignupActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        }

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);

        dSignup = findViewById(R.id.dSignup);
        btnSignup = findViewById(R.id.button2);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress2);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        editTextPassword3 = findViewById(R.id.editTextPassword3);

        btnSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                progressBar.setVisibility(View.VISIBLE);
                String email, password2,password3;
                email = editTextEmailAddress.getText().toString();
                password2 = String.valueOf(editTextPassword2.getText().toString());
                password3 = String.valueOf(editTextPassword3.getText().toString());

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(SignupActivity.this,"Enter your email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password2))
                {
                    Toast.makeText(SignupActivity.this,"Enter your password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password3))
                {
                    Toast.makeText(SignupActivity.this,"Confirm your password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password2.equals(password3))
                {
                    Toast.makeText(SignupActivity.this,"Password does not match!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password2.length() < 6)
                {
                    Toast.makeText(SignupActivity.this,"Password too short, Enter minimum 6 characters!",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password2).addOnCompleteListener(SignupActivity.this,(task) -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful())
                    {
                        // Sign in success, update UI with the signed-in user's information
                        // FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignupActivity.this, "Account created. Please Login",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(SignupActivity.this, "Email id is already registered!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        dSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}