package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        Button startQuizButton = findViewById(R.id.startQuizButton);

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString().trim();
                if (!userName.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this,QuizActivity.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                } else {

                    // 显示错误消息或进行其他处理
                }
            }
        });
    }
}