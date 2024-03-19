package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionTextView;
    TextView QuestionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int score=0;
    int totalquestion=QuestionAnswers.question.length;
    int currentQuestionIndex=0;
    String selectedAns="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView = findViewById(R.id.total_question);
        QuestionTextView = findViewById(R.id.Question);
        ansA=findViewById(R.id.Opt_A);
        ansB=findViewById(R.id.Opt_B);
        ansC=findViewById(R.id.Opt_c);
        ansD=findViewById(R.id.Opt_D);
        submitBtn=findViewById(R.id.Submit_button);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionTextView.setText("Total Question: "+totalquestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View v) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if(clickedButton.getId()==R.id.Submit_button){// now increase the score
            if(selectedAns.equals(QuestionAnswers.correctans[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        }
        else{
            // means user has clicked choices
            selectedAns = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }
    }
    void loadNewQuestion(){

        if(currentQuestionIndex == totalquestion){
            finishQuiz();
            return;
        }

        QuestionTextView.setText(QuestionAnswers.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswers.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswers.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswers.choices[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus="";
        if(score>totalquestion*0.6){
            passStatus="Passed";
        }
        else{
            passStatus="Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" Out of "+totalquestion)
                .setPositiveButton("Restart",(dialogInterface,i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
}