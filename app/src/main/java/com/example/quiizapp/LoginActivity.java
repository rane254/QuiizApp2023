package com.example.quiizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {
    private TextView dLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button btnLogin;
    private EditText editTextEmailAddress1,editTextPassword1;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_login);

        dLogin = findViewById(R.id.dLogin);
        progressBar = findViewById(R.id.progressBar);
        btnLogin = findViewById(R.id.button1);
        editTextEmailAddress1 = findViewById(R.id.editTextEmailAddress1);
        editTextPassword1 = findViewById(R.id.editTextPassword1);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                progressBar.setVisibility(View.VISIBLE);
                String email, password1;
                email = editTextEmailAddress1.getText().toString();
                password1 = String.valueOf(editTextPassword1.getText().toString());

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginActivity.this,"Enter your email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1))
                {
                    Toast.makeText(LoginActivity.this,"Enter your password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password1).addOnCompleteListener(LoginActivity.this, (task) ->
                    {

                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                // FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Login Successful.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed! Please Try again",Toast.LENGTH_SHORT).show();
                            }
                    });

            }
        });

        dLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}