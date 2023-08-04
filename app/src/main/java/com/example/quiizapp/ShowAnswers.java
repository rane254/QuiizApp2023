package com.example.quiizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class ShowAnswers extends AppCompatActivity {
    RecyclerView recyclerView;
    String q1 = QuestionActivity.q1;
    String q2 = QuestionActivity.q2;
    String q3 = QuestionActivity.q3;
    String q4 = QuestionActivity.q4;
    String q5 = QuestionActivity.q5;
    String q6 = QuestionActivity.q6;
    String q7 = QuestionActivity.q7;
    String q8 = QuestionActivity.q8;
    String q9 = QuestionActivity.q9;
    String q10 = QuestionActivity.q10;


    String a1 = QuestionActivity.a1;
    String a2 = QuestionActivity.a2;
    String a3 = QuestionActivity.a3;
    String a4 = QuestionActivity.a4;
    String a5 = QuestionActivity.a5;
    String a6 = QuestionActivity.a6;
    String a7 = QuestionActivity.a7;
    String a8 = QuestionActivity.a8;
    String a9 = QuestionActivity.a9;
    String a10 = QuestionActivity.a10;

    String [] arr = {q1+"\n\n\t-"+a1, q2+"\n\n\t-"+a2, q3+"\n\n\t-"+a3, q4+"\n\n\t-"+a4,q5+"\n\n\t-"+a5, q6+"\n\n\t-"+a6,
            q7+"\n\n\t-"+a7, q8+"\n\n\t-"+a8, q9+"\n\n\t-"+a9, q10+"\n\n\t-"+a10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answers);

        recyclerView = findViewById(R.id.recAnswers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter c = new CustomAdapter(arr);
        recyclerView.setAdapter(c);
    }

    public void goToHome(View v){
        Intent home = new Intent(this, DashboardActivity.class);
        startActivity(home);
        finish();
    }
}