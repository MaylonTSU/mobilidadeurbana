package com.example.projetomobili;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaPrincipal extends AppCompatActivity {

    private TextView textNomeUsuario, textEmailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        // Referenciar TextViews
        textNomeUsuario = findViewById(R.id.textNomeUsuario);
        textEmailUsuario = findViewById(R.id.textEmailUsuario);
        Button sairButton = findViewById(R.id.sair);

        // Obter dados do Intent
        Intent intent = getIntent();
        String nomeUsuario = intent.getStringExtra("nomeUsuario");
        String emailUsuario = intent.getStringExtra("emailUsuario");

        // Definir o nome e email nos TextViews
        textNomeUsuario.setText(nomeUsuario);
        textEmailUsuario.setText(emailUsuario);


        sairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trocar para a próxima Activity ao clicar no botão "Sair"
                Intent intent = new Intent(TelaPrincipal.this, FormLogin.class);
                startActivity(intent);
                finish();  // Fecha a activity atual, se necessário
            }
        });
    }
}
