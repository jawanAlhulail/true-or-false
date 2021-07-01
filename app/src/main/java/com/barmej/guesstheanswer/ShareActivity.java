package com.barmej.guesstheanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ShareActivity extends AppCompatActivity {
private String mQuestionText;
public EditText mEditText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mQuestionText = getIntent().getStringExtra("question text extra");
        mEditText = findViewById(R.id.edit_share_text);
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        String questionTitle = sharedPreferences.getString("share title", "");
        mEditText.setText(questionTitle);
    }
    public void onShareQuestionClicked(View view){
        String questionTitle = mEditText.getText().toString();
        Intent shareintent = new Intent();
        shareintent.setAction(Intent.ACTION_SEND);
        shareintent.putExtra(Intent.EXTRA_TEXT , questionTitle + "\n" + mQuestionText);
        shareintent.setType("text/plain");
        startActivity(shareintent);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("share title", questionTitle);
        editor.apply();
    }

}