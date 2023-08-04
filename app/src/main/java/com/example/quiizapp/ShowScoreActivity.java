package com.example.quiizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowScoreActivity extends AppCompatActivity {
    TextView TxtScore;
    TextView TxtStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        TxtScore = findViewById(R.id.txtscore);
        TxtStatus = findViewById(R.id.txtStatus);
        Intent intent = getIntent();
        String scores = String.valueOf(intent.getIntExtra("score", 0));

        TxtScore.setText(scores);
        TxtStatus.setText(setStatus(scores));
    }

    private String setStatus(String scores){
        int score = Integer.parseInt(scores);

        if(score >= 8){

            return "Awesome !!";
        }

        if (score >= 5){

            return "Moderate";
        }


        return "You can do better !";

    }


    public void goToHome(View v){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        finish();
    }
    public void showAnswers(View v){
        Intent answers = new Intent(this, ShowAnswers.class);
        startActivity(answers);
        finish();
    }
}
