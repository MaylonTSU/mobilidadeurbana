package com.example.projetomobili;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormCadastro extends AppCompatActivity {

    private CheckBox checkBoxDriver;
    private LinearLayout driverDetailsLayout;
    private Button btCadastrar;

    // Campos de usuário
    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;

    // Campos para motorista
    private EditText editPlaca;
    private EditText editCNH;

    private DatabaseHelper dbHelper; // Helper do banco de dados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        // Inicializar DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Referenciar componentes do layout
        editNome = findViewById(R.id.edit_nome);
        editEmail = findViewById(R.id.edit_email);
        editSenha = findViewById(R.id.edit_senha);

        // Referenciar componentes de motorista
        checkBoxDriver = findViewById(R.id.checkBoxDriver);
        driverDetailsLayout = findViewById(R.id.driverDetailsLayout);
        editPlaca = findViewById(R.id.edit_placa);
        editCNH = findViewById(R.id.edit_cnh);

        btCadastrar = findViewById(R.id.btCadastrar);

        // Mostrar ou esconder os campos de motorista com base no checkbox
        checkBoxDriver.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                driverDetailsLayout.setVisibility(View.VISIBLE);
            } else {
                driverDetailsLayout.setVisibility(View.GONE);
            }
        });

        // Ação ao clicar no botão de cadastro
        btCadastrar.setOnClickListener(v -> {
            inserirUsuario(); // Método para inserir usuário e motorista
        });
    }

    // Método para inserir usuário no banco de dados
    private void inserirUsuario() {
        // Capturar os valores inseridos
        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        // Verificar se os campos de usuário estão preenchidos
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Inserir o usuário na tabela 'usuario'
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("email", email);
        values.put("senha", senha);

        long usuarioId = db.insert("usuario", null, values);

        if (usuarioId == -1) {
            Toast.makeText(this, "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar se o usuário é motorista
        if (checkBoxDriver.isChecked()) {
            String placa = editPlaca.getText().toString();
            String cnh = editCNH.getText().toString();

            if (placa.isEmpty() || cnh.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos de motorista.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Inserir os detalhes do motorista
            ContentValues motoristaValues = new ContentValues();
            motoristaValues.put("userid", usuarioId);
            motoristaValues.put("nome", nome);
            motoristaValues.put("email", email);
            motoristaValues.put("senha", senha);
            motoristaValues.put("CNH", cnh);
            motoristaValues.put("veiculo", placa);

            long motoristaId = ((SQLiteDatabase) db).insert("motorista", null, motoristaValues);

            if (motoristaId == -1) {
                Toast.makeText(this, "Erro ao cadastrar motorista.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Motorista cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        }

        // Redirecionar para a próxima tela (TelaPrincipal)
        Intent intent = new Intent(FormCadastro.this, FormLogin.class);
        startActivity(intent);
    }
}
