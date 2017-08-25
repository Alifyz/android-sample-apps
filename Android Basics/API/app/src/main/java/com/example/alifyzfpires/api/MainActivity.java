package com.example.alifyzfpires.api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    //Contador - Pontos de Perfil
    int conservador = 0;
    int moderado = 0;
    int agressivo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner ageSpinner = (Spinner) findViewById(R.id.age);
        RadioGroup firstQuestion = (RadioGroup) findViewById(R.id.q1);

    }
}
