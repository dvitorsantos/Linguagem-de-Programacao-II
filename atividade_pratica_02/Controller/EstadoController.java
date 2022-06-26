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

        Estado estado = new Estado(nome, sigla, capital, area, populacao, pib, idh);

        return this.save(paises, estado, siglaPais, nomeRegiao);
    }

    public ArrayList<Pais> save(ArrayList<Pais> paises, Estado estado, String siglaPais, String nomeRegiao) {
        Iterator iteratorPaises = paises.iterator();
        while (iteratorPaises.hasNext()) {
            Pais pais = (Pais) iteratorPaises.next();

            if (pais.getSigla().equals(siglaPais)) {
                Iterator iteratorRegioes = pais.getRegioes().iterator();

                while (iteratorRegioes.hasNext()) {
                    Regiao regiao = (Regiao) iteratorRegioes.next();

                    if (regiao.getNome().equals(nomeRegiao)) {
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

    public ArrayList<Pais> carregarArquivo(ArrayList<Pais> paises) {
        String data = this.estadoModel.findAll();

        StringTokenizer estadosString = new StringTokenizer(data, "|");

        System.out.println(data);

        RegiaoController regiaoController = new RegiaoController();
        PaisController paisController = new PaisController();

        while (estadosString.hasMoreElements()) {
            StringTokenizer info = new StringTokenizer((String) estadosString.nextElement(), ";");

            String nome = info.nextElement().toString();
            String sigla = info.nextElement().toString().replaceAll("\\s","");
            String capital = info.nextElement().toString();
            String nomeRegiao =  info.nextElement().toString().replaceAll("\\s","");
            double area = Double.parseDouble(info.nextElement().toString().replaceAll("\\s",""));
            int populacao = Integer.parseInt(info.nextElement().toString().replaceAll("\\s",""));
            float pib = Float.parseFloat(info.nextElement().toString().replaceAll("\\s",""));
            float idh = Float.parseFloat(info.nextElement().toString().replaceAll("\\s",""));

            Regiao regiao = regiaoController.getByName(paises, nomeRegiao);

            if (regiao == null) {
                regiao = new Regiao(nomeRegiao, nomeRegiao);

                Pais pais = paisController.get(paises, "BRA");
                if (pais == null) {
                    pais = new Pais("Brasil", "BRA");
                    paises = paisController.save(paises, pais);
                }

                paises = regiaoController.save(paises, regiao, "BRA");
            }

            Estado estado = new Estado(nome, sigla, capital, area, populacao, pib, idh);

            paises = this.save(paises, estado, "BRA", nomeRegiao);
        }

        return paises;
    }
}
