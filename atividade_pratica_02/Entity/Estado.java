package Entity;

public class Estado extends Territorio {
    private Estado estadoSimilar;
    private String capital;

    public Estado(String nome, String sigla, String capital, double area, int populacao, float pib, float idh) {
        super(nome, sigla, area, populacao, pib, idh);
        this.capital = capital;
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
