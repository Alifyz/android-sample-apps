package com.example.alifyzfpires.api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        int Index = firstQuestion.indexOfChild(findViewById(firstQuestion.getCheckedRadioButtonId()));
        Log.e("MainActivity", Index + "");
    }
}
