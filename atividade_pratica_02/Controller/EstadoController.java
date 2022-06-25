package Controller;

import Entity.Estado;
import Entity.Pais;
import Entity.Regiao;
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
            info.nextElement(); //Nome da região
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Estado");
        String nome = scanner.next();
        System.out.println("Digite a sigla do Estado");
        String sigla = scanner.next();
        System.out.println("Digite o nome da capital do Estado");
        String capital = scanner.next();
        System.out.println("Digite o nome da regiao do Estado");
        String nomeRegiao = scanner.next();
        System.out.println("Digite a area do Estado");
        double area = scanner.nextDouble();
        System.out.println("Digite a população do Estado");
        int populacao = scanner.nextInt();
        System.out.println("Digite o pib do Estado");
        float pib = scanner.nextFloat();
        System.out.println("Digite o idh do Estado");
        float idh = scanner.nextFloat();
        System.out.println("Digite a sigla do pais do Estado");
        String siglaPais = scanner.next();

        Iterator iteratorPaises = paises.iterator();

        while (iteratorPaises.hasNext()) {
            Pais pais = (Pais) iteratorPaises.next();

            if (pais.getSigla().equals(siglaPais)) {
                Iterator iteratorRegioes = pais.getRegioes().iterator();

                while (iteratorRegioes.hasNext()) {
                    Regiao regiao = (Regiao) iteratorRegioes.next();

                    if (regiao.getNome().equals(nomeRegiao)) {
                        Estado estado = new Estado(nome, sigla, capital, area, populacao, pib, idh);
                        Regiao regiaoAtualizada = regiao;
                        regiaoAtualizada.addEstado(estado);
                        regiaoAtualizada.setArea(regiaoAtualizada.getArea() + estado.getArea());
                        regiaoAtualizada.setPopulacao(regiaoAtualizada.getPopulacao() + estado.getPopulacao());
                        regiaoAtualizada.calculaPib();
                        regiaoAtualizada.calculaIdh();

                        ArrayList<Regiao> regioes = pais.getRegioes();

                        regioes.set(regioes.indexOf(regiao), regiaoAtualizada);

                        Pais paisAtualizado = pais;
                        paisAtualizado.setRegioes(regioes);
                        paisAtualizado.setArea(paisAtualizado.getArea() + regiaoAtualizada.getArea());
                        paisAtualizado.setPopulacao(paisAtualizado.getPopulacao() + regiaoAtualizada.getPopulacao());
                        paisAtualizado.calculaPib();
                        paisAtualizado.calculaIdh();

                        paises.set(paises.indexOf(pais), paisAtualizado);

                        for (Pais paisE: paises
                             ) {
                            for (Regiao regiaoE: pais.getRegioes()
                                 ) {
                                regiao.getInformacao();
                            }
                        }
                        return paises;
                    }
                }
            }
        }

        return paises;
    }
}
