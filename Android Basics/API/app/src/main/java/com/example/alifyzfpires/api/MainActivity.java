package com.example.alifyzfpires.api;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    //Contador - Pontos de Perfil
    int conservador = 0;
    int moderado = 0;
    int agressivo = 0;

    Spinner ageSpinner;
    RadioGroup firstQuestion;
    RadioGroup secondQuestion;
    RadioGroup thirdQuestion;
    RadioGroup fourthQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageSpinner = (Spinner) findViewById(R.id.age);
        firstQuestion = (RadioGroup) findViewById(R.id.q1);
        secondQuestion = (RadioGroup) findViewById(R.id.q2);
        thirdQuestion = (RadioGroup) findViewById(R.id.q3);
        fourthQuestion = (RadioGroup) findViewById(R.id.q4);

    }

    public void evaluateProfile(View v) {

        int firstQuestionIndex = ageSpinner.getSelectedItemPosition();
        int secondQuestionIndex = firstQuestion.indexOfChild(findViewById
                (firstQuestion.getCheckedRadioButtonId()));
        int thirdQuestionIndex = secondQuestion.indexOfChild(findViewById
                (secondQuestion.getCheckedRadioButtonId()));
        int fourthQuestionIndex = thirdQuestion.indexOfChild(findViewById
                (thirdQuestion.getCheckedRadioButtonId()));
        int fithQuestionIndex = fourthQuestion.indexOfChild(findViewById
                (fourthQuestion.getCheckedRadioButtonId()));

        switch (firstQuestionIndex) {
            case 0:
                conservador += 1;
                break;
            case 1:
                moderado += 1;
                break;
            case 2:
                moderado += 1;
                break;
            case 3:
                agressivo += 1;
            default:
                break;
        }

        switch (secondQuestionIndex) {
            case 0:
                conservador += 1;
                break;
            case 1:
                moderado += 1;
                agressivo += 1;
                break;
            default:
                break;
        }

        switch (thirdQuestionIndex) {
            case 0:
                conservador += 1;
                break;
            case 1:
                moderado += 1;
                break;
            case 2:
                moderado += 1;
                break;
            case 3:
                agressivo += 1;
            default:
                break;
        }

        switch (fourthQuestionIndex) {
            case 0:
                conservador += 1;
                break;
            case 1:
                moderado += 1;
                break;
            case 2:
                moderado += 1;
                break;
            case 3:
                agressivo += 1;
            default:
                break;
        }

        switch (fithQuestionIndex) {
            case 0:
                conservador += 1;
                break;
            case 1:
                moderado += 1;
                break;
            case 2:
                moderado += 1;
                break;
            case 3:
                agressivo += 1;
            default:
                break;
        }
    }

    public void getScore() {

    }


}
