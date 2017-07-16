package com.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
     * Initializing Global Variables
     */

    private int pointsTeamA = 0;
    private int pointsTeamB = 0;
    private int faltasA = 0;
    private int faltasB = 0;


    private static final String PONTOS_A = "TIME_A";
    private static final String PONTOS_B = "TIME_B";
    private static final String FALTAS_A = "FALTAS_A";
    private static final String FALTAS_B = "FALTAS_B";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OBRIGADO dhiegoabrantes, por me mostrar o SharePreferences
        // Código Implementado de Acordo com os Padrões no Link abaixo
        // https://developer.android.com/training/basics/activity-lifecycle/recreating.html?hl=pt-br#RestoreState

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            pointsTeamA = savedInstanceState.getInt(PONTOS_A);
            pointsTeamB = savedInstanceState.getInt(PONTOS_B);
            faltasA = savedInstanceState.getInt(FALTAS_A);
            faltasB = savedInstanceState.getInt(FALTAS_B);
            displayA(pointsTeamA);
            displayB(pointsTeamB);
            setFaltasA(faltasA);
            setFaltasB(faltasB);

        } else {
            pointsTeamA = 0;
            pointsTeamB = 0;
            faltasA = 0;
            faltasB = 0;
        }

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(PONTOS_A, pointsTeamA);
        savedInstanceState.putInt(PONTOS_B, pointsTeamB);
        savedInstanceState.putInt(FALTAS_A, faltasA);
        savedInstanceState.putInt(FALTAS_B, faltasB);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
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
