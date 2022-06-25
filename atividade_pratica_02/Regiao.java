package com.company;

import java.util.ArrayList;

public class Regiao extends Territorio{
    private ArrayList <Estado> estados;

    public Regiao(String nome, String sigla, double area, int populacao, float pib, float idh) {
        super(nome, sigla, area, populacao, pib, idh);
    }

    @Override
    public String toString() {
        return super.toString() + "\n{" +
                "estados=" + estados +
                '}';
    }
}
