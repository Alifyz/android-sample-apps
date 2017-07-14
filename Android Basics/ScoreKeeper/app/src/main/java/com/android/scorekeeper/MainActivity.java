package com.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
     * Initializing Global Variables
     */

    int pointsTeamA = 0;
    int pointsTeamB = 0;
    int faltasA = 0;
    int faltasB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * Increasing Methods - Aumentando os Pontos e Faltas
     */

    public void increaseTeamA(View view) {
        pointsTeamA++;
        displayA(pointsTeamA);
    }

    public void increaseTeamB(View view) {
        pointsTeamB++;
        displayB(pointsTeamB);
    }

    public void increaseFaltasA(View view) {
        faltasA++;
        setFaltasA(faltasA);
    }

    public void increaseFaltasB(View view) {
        faltasB++;
        setFaltasB(faltasB);
    }

    // Limpando a Pontuação
    public void cleanScore(View view) {

        pointsTeamA = 0;
        pointsTeamB = 0;
        faltasA = 0;
        faltasB = 0;
        displayA(pointsTeamA);
        displayB(pointsTeamB);
        setFaltasA(faltasA);
        setFaltasB(faltasB);
    }


    /*
     * Display Methods - Alterando os Valores das Views
     */

    private void displayA(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.pontos);
        quantityTextView.setText("" + number);
    }

    private void displayB(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.pontos_tb);
        quantityTextView.setText("" + number);
    }

    private void setFaltasA(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.faltas);
        quantityTextView.setText("" + number);
    }

    private void setFaltasB(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.faltas_tb);
        quantityTextView.setText("" + number);
    }




}
