package Controller;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {
    public int getOpcao() {
        System.out.println("1. Cadastrar um pais");
        System.out.println("2. Cadastrar uma regiao");
        System.out.println("3. Cadastrar um estado de um pais");
        System.out.println("4. Carregar informacoes do arquivo");
        System.out.println("5. Obter informacoes de um estado atraves da sigla");
        System.out.println("6. Obter informacoes de uma regiao");
        System.out.println("7. Obter informacoes de um pais");
        System.out.println("0. Sair");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
