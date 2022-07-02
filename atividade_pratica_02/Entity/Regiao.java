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

    public void calculaPib() {
        float novoPIB = 0;
        for (Estado estado : this.getEstados()) {
            novoPIB += estado.getPib();
        }
        novoPIB = novoPIB/ this.getEstados().size();
        this.setPib(novoPIB);
    }

    public void calculaIdh() {
        float novoIdh = 0;
        for (Estado estado : this.getEstados()) {
            novoIdh += estado.getIdh();
        }
        novoIdh = novoIdh/ this.getEstados().size();
        this.setIdh(novoIdh);
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public void addEstado(Estado estado) {
        this.estados.add(estado);
    }

    @Override
    public String toString() {
        return "INFORMACOES DA REGIAO:\n" +
                super.toString();
    }
}
