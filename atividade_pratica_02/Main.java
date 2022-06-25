import Controller.EstadoController;
import Controller.RegiaoController;
import Entity.Estado;
import Entity.Pais;
import Entity.Regiao;
import Controller.MenuController;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pais> paises = new ArrayList<>();

        MenuController menu = new MenuController();
        RegiaoController regiaoController = new RegiaoController();
        EstadoController estadoController = new EstadoController();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            int opcao = menu.getOpcao();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do pais");
                    String nomePais = scanner.next();
                    System.out.println("Digite a sigla do pais");
                    String siglaPais = scanner.next();

                    Pais pais = new Pais(nomePais, siglaPais);
                    paises.add(pais);
                    break;
                case 2:
                    paises = regiaoController.cadastrarRegiao(paises);

                    for (Pais paisE : paises) {
                        System.out.println(paisE);
                    }
                    break;
                case 3:
                    paises = estadoController.cadastraEstado(paises);
                    for (Pais paisE : paises) {
                        System.out.println(paisE);
                    }
                    break;
                case 0:
                    exit = true;
            }
        }
    }
}
