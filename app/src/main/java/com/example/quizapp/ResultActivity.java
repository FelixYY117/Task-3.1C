package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView scoreTextView;
    private Button takeNewQuizButton;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 初始化视图
        scoreTextView = findViewById(R.id.scoreTextView);
        takeNewQuizButton = findViewById(R.id.takeNewQuizButton);
        finishButton = findViewById(R.id.finishButton);

        // 获取最终分数
        int finalScore = getIntent().getIntExtra("finalScore", 0);
        scoreTextView.setText("Your final score is: " + finalScore);

        // 设置按钮点击事件监听器
        takeNewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizActivity();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    private void startQuizActivity() {
        // 获取用户名字
        String userName = getIntent().getStringExtra("userName");

        // 启动 QuizActivity，并传递用户名字
        Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }
}