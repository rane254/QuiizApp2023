package com.example.quiizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    //Button btnLogout;
    TextView userDetails;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView myListView = findViewById(R.id.myListView);

        mAuth = FirebaseAuth.getInstance();
        userDetails = findViewById(R.id.userDetails);
        user = mAuth.getCurrentUser();
        if (user == null)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            userDetails.setText(user.getEmail());
        }

        ArrayList<String> QuizList = new ArrayList<>();
        QuizList.add("General Knowledge");
        //QuizList.add("Java");
        //QuizList.add("Python");
        //QuizList.add("Android");
        //QuizList.add("Javascript");
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,QuizList);

        myListView.setAdapter(Adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DashboardActivity.this,QuestionActivity.class);
                startActivity(intent);
                String text = "Opening " + i + " " + ((TextView) view).getText().toString();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item1)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Account Logged out!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}