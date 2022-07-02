package Entity;

public class Estado extends Territorio {
    private Estado estadoSimilar;
    private String capital;

    public Estado(String nome, String sigla, String capital, double area, int populacao, float pib, float idh) {
        super(nome, sigla, area, populacao, pib, idh);
        this.capital = capital;
    }

    @Override
    public String getInformacao() {
        if (this.estadoSimilar != null) {
            return super.getInformacao();
        } else {
            return "INFORMACOES DO ESTADO:\n" +
                    super.toString() +
                    "\nestado similar: vazio";
        }

    }

    @Override
    public String toString() {
        return  "INFORMACOES DO ESTADO:\n" +
                super.toString() +
                "\nEstado similar: " + this.estadoSimilarToString();
    }

    public String estadoSimilarToString() {
        return  "\n\tnome: " + this.estadoSimilar.getNome() +
                "\n\tsigla: " + this.estadoSimilar.getSigla() +
                "\n\tarea: " + this.estadoSimilar.getArea() +
                "\n\tpopulacao: " + this.estadoSimilar.getPopulacao() +
                "\n\tpib: " + this.estadoSimilar.getPib() +
                "\n\tidh: " + this.estadoSimilar.getIdh() +
                "\n\tpib per capita: " + this.estadoSimilar.getPibPerCapita() +
                "\n\tdensidade: " + this.estadoSimilar.getDensidade();
    }

    public void definirEstadoSimilar(Estado estado) {
        this.estadoSimilar = estado;
    }

    public Estado getEstadoSimilar() {
        return estadoSimilar;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
