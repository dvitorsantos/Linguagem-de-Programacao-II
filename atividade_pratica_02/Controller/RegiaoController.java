package Controller;

import Entity.Pais;
import Entity.Regiao;

import java.util.ArrayList;
import java.util.Scanner;

public class RegiaoController {
    public ArrayList<Pais> cadastrarRegiao(ArrayList<Pais> paises) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da regiao");
        String nomeRegiao = scanner.next();
        System.out.println("Digite a sigla da regiao");
        String siglaRegiao = scanner.next();
        System.out.println("Digite a sigla do pais referente");
        String siglaPaisReferente = scanner.next();

        Regiao regiao = new Regiao(nomeRegiao, siglaRegiao);

        regiao.getInformacao();

        int index = 0;
        int indexOfPais = -1;
        for (Pais paisRefente : paises) {
            if (paisRefente.getSigla().equals(siglaPaisReferente)) {
                indexOfPais = index;
            } else {
                index++;
            }
        }

        if (indexOfPais >= 0) {
            Pais pais = paises.get(index);
            pais.addRegiao(regiao);
            paises.set(index, pais);
        } else {
            System.out.println("Pais não encontrado. Cadastre primeiramente o pais.");
        }

        return paises;
    }
}
