package Controller;

import Entity.Pais;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PaisController {
    public ArrayList<Pais> cadastraPais(ArrayList<Pais> paises) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do pais");
        String nomePais = scanner.next();
        System.out.println("Digite a sigla do pais");
        String siglaPais = scanner.next();

        Pais pais = new Pais(nomePais, siglaPais);

        return this.save(paises, pais);
    }

    public Pais buscarPais(ArrayList<Pais> paises) {
        System.out.println("Digite a sigla do pais: ");
        Scanner scanner = new Scanner(System.in);

        String sigla = scanner.next();

        return this.get(paises, sigla);
    }

    public Pais get(ArrayList<Pais> paises, String sigla) {
        Iterator iteratorPaises = paises.iterator();

        while (iteratorPaises.hasNext()) {
            Pais pais = (Pais) iteratorPaises.next();

            if (sigla.equals(pais.getSigla())) {
                return pais;
            }
        }

        return null;
    }

    public ArrayList<Pais> save(ArrayList<Pais> paises, Pais pais) {
        paises.add(pais);
        return paises;
    }
}
