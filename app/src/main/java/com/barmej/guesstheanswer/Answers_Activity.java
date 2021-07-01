package com.barmej.guesstheanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Answers_Activity extends AppCompatActivity {
private TextView mCurrentTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
mCurrentTextView = findViewById(R.id.answer_detail);
String answer = getIntent().getStringExtra("question_answer");
if(answer != null){
    mCurrentTextView.setText(answer);
}
    }
    public void onReturn(View view){
        finish();
    }

}