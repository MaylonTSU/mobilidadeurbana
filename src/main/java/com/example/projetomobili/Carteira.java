package com.example.projetomobili;

public class Carteira {
    private String nome;
    private String genero;
    private int idade;
    private String statusVacina;

    public Carteira(String nome, String genero, int idade, String statusVacina) {
        this.nome = nome;
        this.genero = genero;
        this.idade = idade;
        this.statusVacina = statusVacina;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public int getIdade() {
        return idade;
    }

    public String getStatusVacina() {
        return statusVacina;
    }
}
