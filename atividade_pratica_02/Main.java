import Controller.*;
import Entity.Estado;
import Entity.Pais;
import Entity.Regiao;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pais> paises = new ArrayList<>();

        MenuController menu = new MenuController();
        MainController mainController = new MainController();

        RegiaoController regiaoController = new RegiaoController();
        EstadoController estadoController = new EstadoController();
        PaisController paisController = new PaisController();


        boolean exit = false;

        while (!exit) {
            int opcao = menu.getOpcao();

            switch (opcao) {
                case 1:
                    paises = paisController.cadastraPais(paises);
                    break;
                case 2:
                    paises = regiaoController.cadastrarRegiao(paises);
                    break;
                case 3:
                    paises = estadoController.cadastraEstado(paises);
                    break;
                case 4:
                    paises = mainController.carregarArquivo(paises);
                    estadoController.definirEstadosSimilares(paises);

                    break;
                case 5:
                    Estado estado = estadoController.buscarEstado(paises);

                    if (estado != null) {
                        System.out.println(estado.getInformacao());
                    } else {
                        System.out.println("Estado não encontrado.");
                    }
                    break;
                case 6:
                    Regiao regiao = regiaoController.buscarRegiao(paises);

                    if (regiao != null) {
                        System.out.println(regiao.getInformacao());
                    } else {
                        System.out.println("Regiao nao encontrada.");
                    }
                    break;
                case 7:
                    Pais pais = paisController.buscarPais(paises);

                    if (pais != null) {
                        System.out.println(pais.getInformacao());
                    } else {
                        System.out.println("Pais nao encontrado.");
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}
