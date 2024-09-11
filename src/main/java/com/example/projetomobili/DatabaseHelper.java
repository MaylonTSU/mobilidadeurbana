package com.example.projetomobili;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nome e versão do banco de dados
    private static final String DATABASE_NAME = "meu_banco_de_dados.db";
    private static final int DATABASE_VERSION = 1;

    // Comandos de criação das tabelas
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE usuario (" +
            "userid INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome TEXT NOT NULL, " +
            "email TEXT NOT NULL UNIQUE, " +
            "senha TEXT NOT NULL" +
            ");";

    private static final String CREATE_TABLE_MOTORISTA = "CREATE TABLE motorista (" +
            "userid INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "email TEXT NOT NULL UNIQUE, " +
            "senha TEXT NOT NULL, " +
            "CNH TEXT NOT NULL, " +
            "veiculo TEXT, " +
            "FOREIGN KEY (userid) REFERENCES usuario(userid)" +
            ");";

    private static final String CREATE_TABLE_GERENTE = "CREATE TABLE gerente (" +
            "gerenteid INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "email TEXT NOT NULL UNIQUE, " +
            "senha TEXT NOT NULL, " +
            "departamento TEXT NOT NULL, " +
            "FOREIGN KEY (gerenteid) REFERENCES usuario(userid)" +
            ");";

    private static final String CREATE_TABLE_PASSAGEIRO = "CREATE TABLE passageiro (" +
            "passageiroid INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "email TEXT NOT NULL UNIQUE, " +
            "senha TEXT NOT NULL, " +
            "metododepagamento TEXT NOT NULL, " +
            "FOREIGN KEY (passageiroid) REFERENCES usuario(userid)" +
            ");";

    private static final String CREATE_TABLE_CORRIDA = "CREATE TABLE corrida (" +
            "corridaid INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "startlocalizacao TEXT NOT NULL, " +
            "endlocalizacao TEXT NOT NULL, " +
            "corridastatus TEXT NOT NULL, " +
            "tarifa REAL NOT NULL, " +
            "motoristaid INTEGER NOT NULL, " +
            "passageiroid INTEGER NOT NULL, " +
            "FOREIGN KEY (motoristaid) REFERENCES motorista(userid), " +
            "FOREIGN KEY (passageiroid) REFERENCES passageiro(userid)" +
            ");";

    private static final String CREATE_TABLE_TARIFA = "CREATE TABLE tarifa (" +
            "tarifaid INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tarifabase REAL NOT NULL, " +
            "tarifadistancia REAL NOT NULL, " +
            "tarifatotal REAL NOT NULL, " +
            "corridaid INTEGER NOT NULL, " +
            "FOREIGN KEY (corridaid) REFERENCES corrida(corridaid)" +
            ");";

    // Construtor da classe DatabaseHelper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Criação das tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_MOTORISTA);
        db.execSQL(CREATE_TABLE_GERENTE);
        db.execSQL(CREATE_TABLE_PASSAGEIRO);
        db.execSQL(CREATE_TABLE_CORRIDA);
        db.execSQL(CREATE_TABLE_TARIFA);
    }

    // Atualização do banco de dados (caso a versão seja incrementada)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tarifa");
        db.execSQL("DROP TABLE IF EXISTS corrida");
        db.execSQL("DROP TABLE IF EXISTS passageiro");
        db.execSQL("DROP TABLE IF EXISTS gerente");
        db.execSQL("DROP TABLE IF EXISTS motorista");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }
}
