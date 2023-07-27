package com.example.quiizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class DashboardActivity extends AppCompatActivity {

    private ImageView img_back;
    private TextView txt_exit,questionTextView;
    private RadioGroup answerRadioGroup;
    private LinearLayout submitButton;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        img_back = findViewById(R.id.img_back);
        txt_exit = findViewById(R.id.txt_exit);
        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        txt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        // Initialize questions and options
        questions = new ArrayList<>();
        initializeQuestions();

        displayQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void initializeQuestions() {
        // Add your questions and options here
        // For example:
        questions.add(new Question("What is the capital of France?",
                new String[]{"London", "Paris", "Berlin"}, 1));

        questions.add(new Question("Which planet is closest to the Sun?",
                new String[]{"Venus", "Mercury", "Mars"}, 1));

        // Add more questions as needed
    }

    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestionText());

        // Dynamically add radio buttons for options
        answerRadioGroup.removeAllViews();
        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText(currentQuestion.getOptions()[i]);
            answerRadioGroup.addView(radioButton);
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = answerRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int selectedOptionIndex = selectedRadioButton.getId();

            Question currentQuestion = questions.get(currentQuestionIndex);
            int correctOptionIndex = currentQuestion.getCorrectOptionIndex();

            if (selectedOptionIndex == correctOptionIndex) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                displayQuestion();
            } else {
                finish();
            }
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }
}