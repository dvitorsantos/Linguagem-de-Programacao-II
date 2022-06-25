package com.company;

public class Estado extends Territorio{
    private Estado estadoSimilar;

    public Estado(String nome, String sigla, double area, int populacao, float pib, float idh) {
        super(nome, sigla, area, populacao, pib, idh);
    }

    @Override
    public String toString() {
        return super.toString() + "\n{" +
                "estadoSimilar=" + estadoSimilar +
                '}';
    }

    @Override
    public float getPibPerCapita() {
        return this.getPib() / this.getPopulacao();
    }

    @Override
    public double getDensidade() {
        return this.getPopulacao() / this.getArea();
    }

    public void definirEstadoSimilar() {

    }
}
