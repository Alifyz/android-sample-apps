package com.example.alifyzfpires.musicstation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView reproduzir = (TextView) findViewById(R.id.reproduzir);
        TextView playlist   = (TextView) findViewById(R.id.playlist);
        TextView premium    = (TextView) findViewById(R.id.premium);
        TextView config     = (TextView) findViewById(R.id.configurações);

        reproduzir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reproduzir = new Intent(MainActivity.this, Reproduzir.class);
                startActivity(reproduzir);
            }
        });

        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlist = new Intent(MainActivity.this, Playlist.class);
                startActivity(playlist);
            }
        });

        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent premium = new Intent(MainActivity.this, Premium.class);
                startActivity(premium);
            }
        });

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent configuracoes = new Intent(MainActivity.this, Configuracoes.class);
                startActivity(configuracoes);
            }
        });


    }
}
