package Entity;

import Entity.Regiao;
import Entity.Territorio;

import java.util.ArrayList;

public class Pais extends Territorio {
    private ArrayList<Regiao> regioes;

    public Pais(String nome, String sigla, double area, int populacao, float pib, float idh) {
        super(nome, sigla, area, populacao, pib, idh);

        this.regioes = new ArrayList<Regiao>();
    }

    public Pais(String nome, String sigla) {
        super(nome, sigla, 0, 0, 0, 0);

        this.regioes = new ArrayList<Regiao>();
    }

    public void calculaPib() {
        float novoPIB = 0;
        for (Regiao regiao : this.getRegioes()) {
            novoPIB += regiao.getPib();
        }
        novoPIB = novoPIB/ this.getRegioes().size();
        this.setPib(novoPIB);
    }

    public void calculaIdh() {
        float novoIdh = 0;
        for (Regiao regiao : this.getRegioes()) {
            novoIdh += regiao.getIdh();
        }
        novoIdh = novoIdh/ this.getRegioes().size();
        this.setIdh(novoIdh);
    }

    public ArrayList<Regiao> getRegioes() {
        return this.regioes;
    }

    public void setRegioes(ArrayList<Regiao> regioes) {
        this.regioes = regioes;
    }

    public void addRegiao(Regiao regiao) {
        this.regioes.add(regiao);
    }

    @Override
    public String toString() {
        return "INFORMACOES DO PAIS:\n" +
                super.toString();
    }
}
