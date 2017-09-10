package com.example.alifyzfpires.api;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    int rightAnswers;
    int wrongAnswers;

    EditText name;

    // Each view Representing one Question
    RadioGroup question1;
    RadioGroup question2;
    RadioGroup question3;
    RadioGroup question4;

    // First Statement of the last question and so on...
    CheckBox firstAnswer;
    CheckBox seconAnswer;
    CheckBox thirdAnswer;
    CheckBox fourtAnswer;
    CheckBox fifthAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);

        question1 = (RadioGroup) findViewById(R.id.q1);
        question2 = (RadioGroup) findViewById(R.id.q2);
        question3 = (RadioGroup) findViewById(R.id.q3);
        question4 = (RadioGroup) findViewById(R.id.q4);

        firstAnswer = (CheckBox) findViewById(R.id.a);
        seconAnswer = (CheckBox) findViewById(R.id.b);
        thirdAnswer = (CheckBox) findViewById(R.id.c);
        fourtAnswer = (CheckBox) findViewById(R.id.d);
        fifthAnswer = (CheckBox) findViewById(R.id.e);

    }

    public void getScore(View v) {

        int firstIndex = question1.indexOfChild(findViewById
                (question1.getCheckedRadioButtonId()));
        int secondIndex = question2.indexOfChild(findViewById
                (question2.getCheckedRadioButtonId()));
        int thirdIndex = question3.indexOfChild(findViewById
                (question3.getCheckedRadioButtonId()));
        int fourthIndex = question4.indexOfChild(findViewById
                (question4.getCheckedRadioButtonId()));

        switch (firstIndex) {
            case 0:
                rightAnswers++;
                break;
            default:
                wrongAnswers++;
        }

        switch (secondIndex) {
            case 0:
                rightAnswers++;
                break;
            default:
                wrongAnswers++;
        }

        switch (thirdIndex) {
            case 3:
                rightAnswers++;
                break;
            default:
                wrongAnswers++;
        }

        switch (fourthIndex) {
            case 3:
                rightAnswers++;
                break;
            default:
                wrongAnswers++;
        }

        if (firstAnswer.isChecked())
            rightAnswers++;
        if (seconAnswer.isChecked())
            wrongAnswers++;
        if (thirdAnswer.isChecked())
            wrongAnswers++;
        if (fourtAnswer.isChecked())
            rightAnswers++;
        if (fifthAnswer.isChecked())
            rightAnswers++;

        Toast.makeText(this, "You got: " + rightAnswers +
                        " right, and " + wrongAnswers + " wrong" + ", " + name.getText(),
                Toast.LENGTH_SHORT).show();

        //Restarting Values
        rightAnswers = 0;
        wrongAnswers = 0;

    }


}
