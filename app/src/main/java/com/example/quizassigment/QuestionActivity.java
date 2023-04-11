package com.example.quizassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizassigment.databinding.ActivityQuestionBinding;

public class QuestionActivity extends AppCompatActivity {

    private ActivityQuestionBinding binding;

    public final static String EXSTRA_USER = "exstra-user";
    int score = 0;
    int totalQuestion = 5 ;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    User user;
    QuestionList[] daftarQuiz;

    QuestionList quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        daftarQuiz = QuestionList.getQuizSample();

        user = getIntent().getParcelableExtra(EXSTRA_USER);

        binding.ansA.setOnClickListener(this::onClick);
        binding.ansB.setOnClickListener(this::onClick);
        binding.ansC.setOnClickListener(this::onClick);
        binding.ansD.setOnClickListener(this::onClick);

        loadNewQuestion();

    }

    public void onClick(View view){
        Button button = (Button) view;
        selectedAnswer = button.getText().toString();
        int jawabanBenar = quiz.getJawabanBenar();

        if (selectedAnswer.equals(quiz.getPilihanBenar(jawabanBenar))){
            score += quiz.bobot;
            button.setBackgroundColor(Color.parseColor("#4AFF50"));
        }else {
            button.setBackgroundColor(Color.parseColor("#FF4A4A"));
        }


        if (score > user.BestScore){
            user.BestScore = score;
        }

        user.score = score;
        currentQuestionIndex++;

        button.postDelayed(() -> loadNewQuestion(), 1000);

    }

    void loadNewQuestion(){

        if (currentQuestionIndex == totalQuestion){
            Intent  intent = new Intent(this, ResultActivity.class);
            intent.putExtra(QuestionActivity.EXSTRA_USER,user);
            startActivity(intent);
            return;
        }

        binding.ansA.setBackgroundColor(Color.parseColor("#3B6A48"));
        binding.ansB.setBackgroundColor(Color.parseColor("#3B6A48"));
        binding.ansC.setBackgroundColor(Color.parseColor("#3B6A48"));
        binding.ansD.setBackgroundColor(Color.parseColor("#3B6A48"));

        quiz = daftarQuiz[currentQuestionIndex];
        binding.tvQuestion.setText(quiz.soal);
        binding.ansA.setText(quiz.opsi[0]);
        binding.ansB.setText(quiz.opsi[1]);
        binding.ansC.setText(quiz.opsi[2]);
        binding.ansD.setText(quiz.opsi[3]);
        binding.tvTitle.setText("Question " + (currentQuestionIndex + 1) + " of 5");
    }

}