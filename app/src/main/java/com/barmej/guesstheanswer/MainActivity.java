package com.barmej.guesstheanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mQuestionTextView ;
    private  String[] QUESTIONS;

    private static final boolean[] ANSWERS = {
            false,
            true,
            true,
            false,
            true,
            false,
            false,
            false,
            false,
            true,
            true,
            false,
            true
    };

    private static final String[] ANSWERS_DETAILS = {
            "العملة الرسمية لدولة الكويت هي الدينار الكويتي",
            "توبقال هي أعلى قمة جبلية في العالم العربي و تقع في المغرب",
            "الجزائر هي أكبر دولة عربية و إفريقية من حيث المساحة",
            "الرباط هي عاصمة المغرب",
            "كابول هى عاصمة افغانستان",
            "اضخم الحيوانات اللافقرية هو الحبار",
            "الدولة العربية التي يمر بها خط الاستواء هى الصومال",
            "الكبد هو أكبر عضو في جسم الإنسان",
            "أول مسجد في الإسلام هو مسجد قباء",
            "الخال الوحيد لأولاد عمتك هو والدك",
            "اولى دول العالم انتاجا للموز هى الاكوادور",
            "الأرجنتين عاصمتها بونس إيرس",
            "عملة فيتنام هى دونج"
    };

private String mCurrentQuestion , mCurrentAnswerDetail;
private Boolean mCurrentAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mQuestionTextView = findViewById(R.id.textView3);
        QUESTIONS = getResources().getStringArray(R.array.questions);
        onShowNewQuestions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuChangeLang){
showLanguageDialog();
return true;
        }else{
            return super.onOptionsItemSelected(item);
        }

    }
private void showLanguageDialog(){
    AlertDialog alertDialog = new AlertDialog.Builder(this)
            .setTitle(R.string.change_lang_text)
            .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String language = "ar";
                    switch (which) {
                        case 0:
                            language = "ar";
                            break;
                        case 1:
                            language = "en";
                            break;
                        case 2:
                            language = "fr";
                            break;
                    }

                    LocaleHelper.setLocale(MainActivity.this, language);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }).create();
    alertDialog.show();
}
    private void onShowNewQuestions (){
        Random random = new Random();
        int randomQuestionIndex = random.nextInt(QUESTIONS.length);
        mCurrentQuestion = QUESTIONS[randomQuestionIndex];
        mCurrentAnswerDetail = ANSWERS_DETAILS[randomQuestionIndex];
        mCurrentAnswer = ANSWERS[randomQuestionIndex];
        mQuestionTextView.setText(mCurrentQuestion);
    }
    public void onClickChangeQuestion(View view){
        onShowNewQuestions();
    }
    public void onTrueClicked(View view){
        if(mCurrentAnswer == true){
            Toast.makeText(this , "اجابة صحيحة",Toast.LENGTH_SHORT).show();
            onShowNewQuestions();
        }else{
            Toast.makeText(this , "اجابة خاطئة",Toast.LENGTH_SHORT).show();
           onIntent();

        }
    }
public void onFalseClicked(View view){
        if(mCurrentAnswer == false){
            Toast.makeText(this , "اجابة صحيحة",Toast.LENGTH_SHORT).show();
            onShowNewQuestions();
        }else{
            Toast.makeText(this , "اجابة خاطئة",Toast.LENGTH_SHORT).show();
            onIntent();
        }
}
public void onIntent(){
    Intent intent = new Intent(MainActivity.this, Answers_Activity.class);
    intent.putExtra("question_answer", mCurrentAnswerDetail);
    startActivity(intent);
}
public void onShareQuestionClicked(View view){
        Intent intent = new Intent(MainActivity.this, ShareActivity.class);
        intent.putExtra("question text extra", mCurrentQuestion);
        startActivity(intent);
}

}