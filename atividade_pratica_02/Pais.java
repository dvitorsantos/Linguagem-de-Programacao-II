package com.company;

import java.util.ArrayList;

public class Pais extends  Territorio{
    private ArrayList<Regiao> regioes;

    public Pais(String nome, String sigla, double area, int populacao, float pib, float idh) {
        super(nome, sigla, area, populacao, pib, idh);
    }

    @Override
    public String toString() {
        return super.toString() + "\n{" +
                "regioes=" + regioes +
                '}';
    }
}
