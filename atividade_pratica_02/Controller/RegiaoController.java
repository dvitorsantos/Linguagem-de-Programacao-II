package Controller;

import Entity.Estado;
import Entity.Pais;
import Entity.Regiao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class RegiaoController {
    public ArrayList<Pais> cadastrarRegiao(ArrayList<Pais> paises) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da regiao");
        String nomeRegiao = scanner.next();
        System.out.println("Digite a sigla da regiao");
        String siglaRegiao = scanner.next();
        System.out.println("Digite a sigla do pais referente");
        String siglaPais = scanner.next();

        Regiao regiao = new Regiao(nomeRegiao, siglaRegiao);

        regiao.getInformacao();

        return this.save(paises, regiao, siglaPais);
    }

    public ArrayList<Pais> save(ArrayList<Pais> paises, Regiao regiao, String siglaPais) {
        Iterator iteratorPaises = paises.iterator();

        while (iteratorPaises.hasNext()) {
            Pais pais = (Pais) iteratorPaises.next();

            if (siglaPais.equals(pais.getSigla())) {
                pais.addRegiao(regiao);
                paises.set(paises.indexOf(pais), pais);
            }
        }

        return paises;
    }

    public Regiao buscarRegiao(ArrayList<Pais> paises) {
        System.out.println("Digite a sigla da regiao: ");
        Scanner scanner = new Scanner(System.in);

        String sigla = scanner.next();

        return this.get(paises, sigla);
    }

    public Regiao get(ArrayList<Pais> paises, String sigla) {
        Iterator iteratorPaises = paises.iterator();

        while (iteratorPaises.hasNext()) {
            Pais pais = (Pais) iteratorPaises.next();

            Iterator iteratorRegioes = pais.getRegioes().iterator();

            while (iteratorRegioes.hasNext()) {
                Regiao regiao = (Regiao) iteratorRegioes.next();

                if (sigla.equals(regiao.getSigla())) {
                    return regiao;
                }
            }
        }

        return null;
    }

    public Regiao getByName(ArrayList<Pais> paises, String nome) {
        Iterator iteratorPaises = paises.iterator();

        while (iteratorPaises.hasNext()) {
            Pais pais = (Pais) iteratorPaises.next();

            Iterator iteratorRegioes = pais.getRegioes().iterator();

            while (iteratorRegioes.hasNext()) {
                Regiao regiao = (Regiao) iteratorRegioes.next();

                if (nome.equals(regiao.getNome())) {
                    return regiao;
                }
            }
        }

        return null;
    }
}
