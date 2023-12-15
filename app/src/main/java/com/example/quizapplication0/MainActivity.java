package com.example.quizapplication0;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalquestionTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalquestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        submitBtn = findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        totalquestionTextView.setText(" Total  Question : " +totalQuestion);

        loadNewQuestion();




    }

    @Override
    public void onClick(View v) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if(clickedButton.getId() == R.id.submit){
            if (selectedAnswer.equals(QuestionAnswer.correct_answer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        }else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }
    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.answer[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.answer[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.answer[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.answer[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus ="Passed";
        }else {
            passStatus ="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+ " Out of " + totalQuestion)
                .setPositiveButton("Restart",(dialog, which) -> restartQuiz())
                .setCancelable(false)
                .show();


    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();

    }
}