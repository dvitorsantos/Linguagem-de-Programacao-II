package Controller;

import Entity.Estado;
import Entity.Pais;
import Entity.Regiao;
import Helpers.Keyboard;
import Model.EstadoModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EstadoController {
    EstadoModel estadoModel;

    public EstadoController() {
        this.estadoModel = new EstadoModel();
    }

    public ArrayList<Estado> findAll() {
        String data = this.estadoModel.findAll();

        StringTokenizer estadosString = new StringTokenizer(data, "|");
        ArrayList<Estado> estados = new ArrayList<Estado>();

        while (estadosString.hasMoreElements()) {
            StringTokenizer info = new StringTokenizer((String) estadosString.nextElement(), ";");

            String nome = info.nextElement().toString();
            String sigla = info.nextElement().toString().replaceAll("\\s","");
            String capital = info.nextElement().toString();
            String regiao =  info.nextElement().toString().replaceAll("\\s","");
            double area = Double.parseDouble(info.nextElement().toString().replaceAll("\\s",""));
            int populacao = Integer.parseInt(info.nextElement().toString().replaceAll("\\s",""));
            float pib = Float.parseFloat(info.nextElement().toString().replaceAll("\\s",""));
            float idh = Float.parseFloat(info.nextElement().toString().replaceAll("\\s",""));

            Estado estado = new Estado(nome, sigla, capital, area, populacao, pib, idh);
            estados.add(estado);
        }

        return estados;
    }

    public ArrayList<Pais> cadastraEstado(ArrayList<Pais> paises) {
        PaisController paisController = new PaisController();
        RegiaoController regiaoController = new RegiaoController();

        String siglaPais = Keyboard.forceGetString("Digite a sigla do pais do Estado");
        if (paisController.get(paises, siglaPais) == null) {
            System.err.println("Erro ao cadastrar regiao: Pais nao encontrado");
            return paises;
        }

        String siglaRegiao = Keyboard.forceGetString("Digite a sigla da regiao do Estado");
        if (regiaoController.get(paises, siglaRegiao) == null) {
            System.err.println("Erro ao cadastrar regiao: Regiao nao encontrada");
            return paises;
        }

        String nome = Keyboard.forceGetString("Digite o nome do Estado");
        String sigla = Keyboard.forceGetString("Digite a sigla do Estado");
        String capital = Keyboard.forceGetString("Digite o nome da capital do Estado");
        double area = Keyboard.forceGetDouble("Digite a area do Estado");
        int populacao = Keyboard.forceGetInt("Digite a populacao do Estado");
        float pib = Keyboard.forceGetFloat("Digite o pib do Estado");
        float idh = Keyboard.forceGetFloat("Digite o idh do Estado");

        Estado estado = new Estado(nome, sigla, capital, area, populacao, pib, idh);

        return this.save(paises, estado, siglaPais, siglaRegiao);
    }

    public ArrayList<Pais> save(ArrayList<Pais> paises, Estado estado, String siglaPais, String siglaRegiao) {
        Iterator iteratorPaises = paises.iterator();
        while (iteratorPaises.hasNext()) {
            Pais pais = (Pais) iteratorPaises.next();

            if (pais.getSigla().equals(siglaPais)) {
                Iterator iteratorRegioes = pais.getRegioes().iterator();

                while (iteratorRegioes.hasNext()) {
                    Regiao regiao = (Regiao) iteratorRegioes.next();

                    if (regiao.getSigla().equals(siglaRegiao)) {
                        Regiao regiaoAtualizada = regiao;
                        regiaoAtualizada.addEstado(estado);
                        regiaoAtualizada.setArea(regiaoAtualizada.getArea() + estado.getArea());
                        regiaoAtualizada.setPopulacao(regiaoAtualizada.getPopulacao() + estado.getPopulacao());
                        regiaoAtualizada.calculaPib();
                        regiaoAtualizada.calculaIdh();

                        ArrayList<Regiao> regioesAtualizadas = pais.getRegioes();

                        regioesAtualizadas.set(regioesAtualizadas.indexOf(regiao), regiaoAtualizada);

                        Pais paisAtualizado = pais;
                        paisAtualizado.setRegioes(regioesAtualizadas);
                        paisAtualizado.setArea(paisAtualizado.getArea() + regiaoAtualizada.getArea());
                        paisAtualizado.setPopulacao(paisAtualizado.getPopulacao() + regiaoAtualizada.getPopulacao());
                        paisAtualizado.calculaPib();
                        paisAtualizado.calculaIdh();

                        paises.set(paises.indexOf(pais), paisAtualizado);
                        return paises;
                    }
                }
            }
        }
        return paises;
    }

    public Estado buscarEstado(ArrayList<Pais> paises) {
        System.out.println("Digite a sigla do estado");
        Scanner scanner = new Scanner(System.in);

        String sigla = scanner.next();

        Iterator iteratorPaises = paises.iterator();

        while (iteratorPaises.hasNext()) {
            Pais pais = (Pais) iteratorPaises.next();

            Iterator iteratorRegioes = pais.getRegioes().iterator();

            while (iteratorRegioes.hasNext()) {
                Regiao regiao = (Regiao) iteratorRegioes.next();

                Iterator iteratorEstados = regiao.getEstados().iterator();

                while (iteratorEstados.hasNext()) {
                    Estado estado = (Estado) iteratorEstados.next();
                    if (sigla.equals(estado.getSigla())) {
                        return estado;
                    }
                }
            }
        }

        return null;
    }

    public ArrayList<Pais> definirEstadosSimilares(ArrayList<Pais> paises) {
        for (int i = 0; i < paises.size(); i++) {
            for (int j = 0; j < paises.get(i).getRegioes().size(); j++) {
                for (int k = 0; k < paises.get(i).getRegioes().get(j).getEstados().size(); k++) {
                    Estado estado1 = paises.get(i).getRegioes().get(j).getEstados().get(k);
                    for (int l = 0; l < paises.size(); l++) {
                        for (int m = 0; m < paises.get(l).getRegioes().size(); m++) {
                            for (int n = 0; n < paises.get(l).getRegioes().get(m).getEstados().size(); n++) {
                                Estado estado2 = paises.get(l).getRegioes().get(m).getEstados().get(n);

                                if (!estado1.equals(estado2)) {
                                    if (estado1.getEstadoSimilar() == null) {
                                        estado1.definirEstadoSimilar(estado2);
                                    } else {
                                        if (calcularDistanciaEuclidiana(estado1, estado2) < calcularDistanciaEuclidiana(estado1, estado1.getEstadoSimilar())) {
                                            estado1.definirEstadoSimilar(estado2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    paises.get(i).getRegioes().get(j).getEstados().set(k, estado1);
                }
            }
        }

        return paises;
    }
    public double calcularDistanciaEuclidiana(Estado estado1, Estado estado2) {
        return (Math.sqrt(
                Math.pow(estado1.getIdh() - estado2.getIdh(), 2) +
                Math.pow(estado1.getPib() - estado2.getPib(), 2) +
                Math.pow(estado1.getDensidade() - estado2.getDensidade(), 2)));
    }
}
