//question 03
package list_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n_linhas = scanner.nextInt();
        int middle = n_linhas / 2;

        if (n_linhas > 0 && n_linhas < 20 && (n_linhas % 2 != 0)) {
            for (int i = 0; i < middle; i++) {
                for (int j = 0; j < n_linhas; j++) {
                    if (j == (middle - i)) {
                        for (int k = 0; k < i + 1; k++) {
                            System.out.print("*");
                        }
                        for (int l = 0; l < i; l++) {
                            System.out.print("*");
                        }
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            for (int i = 0; i < n_linhas; i++) {
                System.out.print("*");
            }
            System.out.println();
            for (int i = 0; i < middle; i++) {
                for (int j = 0; j < i + 1; j++) {
                    System.out.print(" ");
                }
                for (int j = i; j < middle; j++) {
                    System.out.print("*");
                }
                for (int k = middle - i - 1; k > 0; k--) {
                    System.out.print("*");
                }
                System.out.println();
            }
            System.out.println();
        } else {
            System.out.println("Erro na entrada de dados.");
        }
    }
}