package com.company;

public class Territorio {
    private String nome;
    private String sigla;
    private double area;
    private int populacao;
    private float pib;
    private float idh;

    public Territorio(String nome, String sigla, double area, int populacao, float pib, float idh) {
        this.nome = nome;
        this.sigla = sigla;
        this.area = area;
        this.populacao = populacao;
        this.pib = pib;
        this.idh = idh;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public float getPib() {
        return pib;
    }

    public void setPib(float pib) {
        this.pib = pib;
    }

    public float getIdh() {
        return idh;
    }

    public void setIdh(float idh) {
        this.idh = idh;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", area=" + area +
                ", populacao=" + populacao +
                ", pib=" + pib +
                ", idh=" + idh +
                '}';
    }

    //implementar
    public float getPibPerCapita() {
        return this.pib / this.populacao;
    }

    //implementar
    public double getDensidade() {
        return this.populacao / this.area;
    }

    //implementar
    public String getInformacao() {
        return this.toString();
    }
}
