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
                "estado similar: " + this.estadoSimilar.getInformacao();

    }

    public void definirEstadoSimilar() {

    }
}
