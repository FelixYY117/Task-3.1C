package com.example.quizapp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private int finalscore=5;
    private List<Question> questionList; // 问题列表
    private int currentQuestionIndex = 0; // 当前问题索引
    private TextView questionTextView; // 用于显示问题
    private RadioGroup optionsRadioGroup; // 用于显示选项
    private ProgressBar progressBar; // 用于显示进度
    private TextView prgerss;
    int num=1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);




        // 初始化视图
        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);

        progressBar = findViewById(R.id.progressBar);
        Button submitButton = findViewById(R.id.submitButton);
        prgerss = findViewById(R.id.progress);

        // 初始化问题列表
        questionList = new ArrayList<>();
        // 添加问题到列表
        questionList.add(new Question("What is the capital of France?", Arrays.asList("Paris", "London", "Berlin"), "Paris"));
        questionList.add(new Question("What is the largest planet in our solar system?", Arrays.asList("Earth", "Jupiter", "Mars"), "Jupiter"));
        questionList.add(new Question("What is the most spoken language in the world?", Arrays.asList("English", "Chinese", "Spanish"), "Chinese"));
        questionList.add(new Question("What is the highest mountain in the world?", Arrays.asList("K2", "Everest", "Kanchenjunga"), "Everest"));
        questionList.add(new Question("Who wrote the Confessions of the Renaissance?", Arrays.asList("Rousseau", "Chekov", "Gorky"), "Rousseau"));

        // 显示第一个问题

        displayQuestion(currentQuestionIndex);
        //在此设置group监听

        // 设置提交按钮的点击事件监听器
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }


    private void displayQuestion(int questionIndex) {
        // 获取当前问题
        Question currentQuestion = questionList.get(questionIndex);

        // 更新问题文本
        questionTextView.setText(currentQuestion.getQuestionText());

        // 清空之前的选项
        optionsRadioGroup.removeAllViews();

        // 添加新的选项
        for (String option : currentQuestion.getOptions()) {
            RadioButton optionRadioButton = new RadioButton(this);
            optionRadioButton.setText(option);
            optionsRadioGroup.addView(optionRadioButton);
        }

        // 更新进度条
        prgerss.setText(num+"/5");
        int progress = (int) ((double) (questionIndex + 1) / questionList.size() * 100);
        progressBar.setProgress(progress);

      //--------------------------------------------------------------------------------------
        optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedOptionId);

                // 获取当前问题
                Question currentQuestion = questionList.get(currentQuestionIndex);

                // 检查答案是否正确
                if (selectedRadioButton.getText().equals(currentQuestion.getCorrectAnswer())) {
                    // 正确答案
                    // selectedRadioButton.setTextColor(Color.GREEN);
                    selectedRadioButton.setBackground(getResources().getDrawable(R.drawable.change_style_ture,null));


                    Log.d(TAG, "checkAnswer: 变绿色"+selectedRadioButton.getText());


                } else {
                    // 错误答案
                    //selectedRadioButton.setTextColor(Color.RED);

                    selectedRadioButton.setBackground(getResources().getDrawable(R.drawable.change_style_false,null));

                    Log.d(TAG, "checkAnswer: 变红色"+selectedRadioButton.getText());
                    --finalscore;


                }

            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkAnswer() {

        // 如果还有剩余问题，显示下一个问题
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            num++;
            displayQuestion(currentQuestionIndex);
        } else {
            // 所有问题已经回答完毕，显示结果活动
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            // 计算并传递最终分数
            intent.putExtra("finalScore", calculateFinalScore());
            startActivity(intent);
            finish();
        }

    }

    private int calculateFinalScore() {


        return finalscore; // 返回计算后的分数
    }

}