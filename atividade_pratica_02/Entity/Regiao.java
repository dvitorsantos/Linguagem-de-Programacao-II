package Entity;

import Entity.Estado;

import java.util.ArrayList;

public class Regiao extends Territorio {
    private ArrayList <Estado> estados;

    public Regiao(String nome, String sigla, double area, int populacao, float pib, float idh) {
        super(nome, sigla, area, populacao, pib, idh);

        this.estados = new ArrayList<>();
    }

    public Regiao(String nome, String sigla) {
        super(nome, sigla, 0, 0, 0, 0);

        this.estados = new ArrayList<>();
    }

    public void addEstado(Estado estado) {
        this.estados.add(estado);
    }

    @Override
    public String toString() {
        return super.toString() + "\n{" +
                "estados=" + estados +
                '}';
    }
}
