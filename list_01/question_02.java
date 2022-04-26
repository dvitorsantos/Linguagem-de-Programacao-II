//question 02
package list_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n_linhas = scanner.nextInt();

        System.out.println("(a)");
        for (int i = 0; i < n_linhas; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\n(b)");
        for (int i = n_linhas; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\n(c)");
        for (int i = 0; i < n_linhas; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = i; j < n_linhas; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\n(d)");
        for (int i = 0; i < n_linhas + 1; i++) {
            for (int j = 0; j < n_linhas - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}