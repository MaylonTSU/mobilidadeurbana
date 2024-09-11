package com.example.projetomobili;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormLogin extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btEntrar, buttonCriar;
    private ProgressBar progressBar;

    private DatabaseHelper dbHelper; // Helper do banco de dados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        // Inicializar DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Referenciar componentes do layout
        editEmail = findViewById(R.id.edit_email);
        editSenha = findViewById(R.id.edit_senha);
        btEntrar = findViewById(R.id.bt_entrar);
        progressBar = findViewById(R.id.progressbar);
        buttonCriar = findViewById(R.id.buttonCriar);

        // Ação ao clicar no botão de login
        btEntrar.setOnClickListener(v -> {
            verificarLogin(); // Método para verificar login
        });
        buttonCriar.setOnClickListener(v -> {
            // Intenção para abrir a próxima Activity
            Intent intent = new Intent(this, FormCadastro.class);
            startActivity(intent);

        });

    }

    // Método para verificar login no banco de dados
    private void verificarLogin() {
        // Capturar os valores inseridos
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        // Verificar se os campos estão preenchidos
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mostrar o ProgressBar enquanto verifica o login
        progressBar.setVisibility(View.VISIBLE);

        // Consultar o banco de dados
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, senha});

        if (cursor.moveToFirst()) {
            // Login bem-sucedido
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

            // Obter os dados do usuário
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
            String emailUsuario = cursor.getString(cursor.getColumnIndexOrThrow("email"));

            // Redirecionar para a próxima tela (exemplo: TelaPrincipal)
            Intent intent = new Intent(this, TelaPrincipal.class);
            intent.putExtra("nomeUsuario", nome);
            intent.putExtra("emailUsuario", emailUsuario);
            startActivity(intent);
            finish(); // Finalizar a activity de login para não voltar a ela
        } else {
            // Login falhou
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Email ou senha incorretos.", Toast.LENGTH_SHORT).show();
        }

        cursor.close(); // Fechar o cursor

    }
}
