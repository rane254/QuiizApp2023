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
    private TextView txt_exit,questionTextView,timerTextView;
    private RadioGroup answerRadioGroup;
    private TextView submitButton;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0; // To keep track of correct answers
    private CountDownTimer timer;
    private static final long COUNTDOWN_TIME = 16000; // 11 seconds (adjust as needed)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        img_back = findViewById(R.id.img_back);
        txt_exit = findViewById(R.id.txt_exit);
        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        txt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity2.class);
                startActivity(intent);
                if (timer != null)
                {
                    timer.cancel();
                }
            }
        });
        // Initialize questions and options
        questions = new ArrayList<>();
        initializeQuestions();

        displayQuestion();
        startTimer(COUNTDOWN_TIME); // Start the timer when a new question is displayed

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checkAnswer();
                // Reset the timer
                startTimer(COUNTDOWN_TIME);
            }
        });

    }

    private void startTimer(long timeInMillis)
    {
        if (timer != null)
        {
            timer.cancel();
        }

        timer = new CountDownTimer(timeInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time remaining: " + millisUntilFinished / 1000 + " seconds");
                // Update the timer display (if you have a TextView to display the timer)
                // For example: timerTextView.setText("Time remaining: " + millisUntilFinished / 1000 + " seconds");
            }

            public void onFinish() {
                // Timer finished, show "Time's up" toast and proceed to the next question
                Toast.makeText(DashboardActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()-1) {
                    displayQuestion();
                    startTimer(COUNTDOWN_TIME); // Start the timer again for the next question
                } else {
                    // No more questions, proceed to the ResultActivity
                    if (timer != null)
                    {
                        timer.cancel();
                    }
                    showResult();
                }
            }
        }.start();
    }



    private void initializeQuestions() {
        // Add your questions and options here
        // For example:
        questions.add(new Question("The C++ code which causes abnormal termination/behaviour of a program should be written under _________ block.",
                new String[]{"catch", "throw", "try","finally"}, 2));

        questions.add(new Question("By default, all the files in C++ are opened in _________ mode.",
                new String[]{"VTC", "Binary", "ISCII","Text"}, 3));

        questions.add(new Question("What is C++?",
                new String[]{"C++ is an object oriented programming language", "C++ is a procedural programming language", "C++ supports both procedural and object oriented programming language","C++ is a functional programming language"}, 2));

        questions.add(new Question("Which of the following is the correct syntax of including a user defined header files in C++?",
                new String[]{"#include [userdefined]", "#include “userdefined”", "#include <userdefined.h>","#include <userdefined>"}, 1));

        questions.add(new Question("Which of the following is a correct identifier in C++?",
                new String[]{"7var_name", "$var_name", "7VARNAME","VAR_1234"}, 3));

        questions.add(new Question("Which of the following is not a type of Constructor in C++?",
                new String[]{"Default constructor", "Parameterized constructor", "Copy constructor","Friend constructor"}, 3));

        questions.add(new Question("Which of the following approach is used by C++?",
                new String[]{"Left-right", "Right-left", "Bottom-up","Top-down"}, 2));

        questions.add(new Question("What happens if the following C++ statement is compiled and executed?\n" +
                "\n" +
                "int *ptr = NULL;\n" +
                "delete ptr;",
                new String[]{"The program is not semantically correct", "The program is compiled and executed successfully", "The program gives a compile-time error","The program compiled successfully but throws an error during run-time"}, 1));

        questions.add(new Question("What will be the output of the following C++ code?\n" +
                "\n" +
                "    #include <iostream>\n" +
                "    using namespace std;\n" +
                "    int main ()\n" +
                "    {\n" +
                "        int a, b, c;\n" +
                "        a = 2;\n" +
                "        b = 7;\n" +
                "        c = (a > b) ? a : b;\n" +
                "        cout << c;\n" +
                "        return 0;\n" +
                "    }.",
                new String[]{"12", "14", "6","7"}, 3));

        questions.add(new Question("What will be the output of the following C++ code?\n" +
                "\n" +
                "#include<iostream>\n" +
                "using namespace std;\n" +
                "int main ()\n" +
                "{\n" +
                "   int cin;\n" +
                "   cin >> cin;\n" +
                "   cout << \"cin: \" << cin;\n" +
                "   return 0;\n" +
                "}",
                new String[]{"Segmentation fault", "Nothing is printed", "Error","cin: garbage value"}, 3));

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
        if (selectedRadioButtonId != -1)
        {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int selectedOptionIndex = selectedRadioButton.getId();

            Question currentQuestion = questions.get(currentQuestionIndex);
            int correctOptionIndex = currentQuestion.getCorrectOptionIndex();

            if (selectedOptionIndex == correctOptionIndex) {
                correctAnswers++;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size())
            {
                displayQuestion();
                startTimer(COUNTDOWN_TIME); // Start the timer again for the next question
            }
            else
            {
                showResult();
            }
        }
        else
        {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResult()
    {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("totalQuestions", questions.size());
        intent.putExtra("correctAnswers", correctAnswers);
        startActivity(intent);
        finish(); // Optional: If you want to close the quiz activity after displaying the result.
    }
}