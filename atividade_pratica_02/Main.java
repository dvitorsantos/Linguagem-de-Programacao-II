import Controller.*;
import Database.Database;
import Entity.Estado;
import Entity.Pais;
import Entity.Regiao;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pais> paises = new ArrayList<>();

        Database database = new Database();

        MenuController menu = new MenuController();
        MainController mainController = new MainController();
        RegiaoController regiaoController = new RegiaoController();
        EstadoController estadoController = new EstadoController();
        PaisController paisController = new PaisController();

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

                    if (database.save(new Pais(nomePais, siglaPais))) {
                        System.out.println("Salvo com sucesso.");
                    } else {
                        System.out.println("Erro ao salvar.");
                    }
                    break;
                case 2:
                    System.out.println("Digite o nome da regiao");
                    String nomeRegiao = scanner.next();
                    System.out.println("Digite a sigla da regiao");
                    String siglaRegiao = scanner.next();
                    System.out.println("Digite a sigla do pais referente");
                    String siglaPaisRegiao = scanner.next();

                    if (database.save(new Regiao(nomeRegiao, siglaRegiao), siglaPaisRegiao)) {
                        System.out.println("Salvo com sucesso.");
                    } else {
                        System.out.println("Erro ao salvar.");
                    }
                    break;
                case 3:
                    paises = estadoController.cadastraEstado(paises);
                    break;
                case 4:
                    paises = mainController.carregarArquivo(paises);
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
            }
        }
    }
}
