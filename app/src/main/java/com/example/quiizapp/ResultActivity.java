package com.example.quiizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    ImageButton img_home;
    TextView resultTextView;

    private CountDownTimer timer;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        img_home = findViewById(R.id.btnHome);
        resultTextView = findViewById(R.id.resultTextView);

        // Retrieve the total number of questions answered
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        // Retrieve the number of correct answers
        int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);

        // Calculate the score percentage
        int scorePercentage = (correctAnswers * 100) / totalQuestions;


        if (correctAnswers  >= 8)
        {
            resultTextView.setText("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly!\nYou Passed");
        }
        else if (correctAnswers == 7)
        {
            resultTextView.setText("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly!\nYou Just Passed");
        }
        else
        {
            resultTextView.setText("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly!\nYou Failed");
        }


        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity2.class);
                startActivity(intent);
                onDestroy();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null)
        {
            timer.cancel();
        }
    }
}