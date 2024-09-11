package com.example.projetomobili;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_logada);


        // Configurando o botão de Carteiras
        Button carteirasTab = findViewById(R.id.carteirasTab);
        carteirasTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaLogin.this, TelaCarteiras.class);
                startActivity(intent);
            }
        });

        // Configurando o botão de Noticias
        Button noticiasTab = findViewById(R.id.noticiasTab);
        noticiasTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaLogin.this, TelaLogin.class);
                startActivity(intent);
            }
        });

        // Configurando o botão de configurações
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exemplo de ação para o botão de configurações
                Toast.makeText(TelaLogin.this, "Configurações clicado!", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurando o botão de compartilhar
        ImageButton shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exemplo de ação para o botão de compartilhar
                Toast.makeText(TelaLogin.this, "Compartilhar clicado!", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurando o botão de busca
        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exemplo de ação para o botão de busca
                Toast.makeText(TelaLogin.this, "Busca clicado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}