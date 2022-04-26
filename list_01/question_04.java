//question 04
package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            int n = scanner.nextInt();

            if (n < 100000) {
                int n_reverso = 0;
                int n_normal = n;

                while (n_normal > 0) {
                    int digito = n_normal % 10;
                    n_reverso = (n_reverso * 10) + digito;
                    n_normal /= 10;
                }

                if (n == n_reverso) {
                    System.out.format("%d é palíndromo!\n", n);
                } else {
                    System.out.format("%d não é palíndromo!\n", n);
                }

                exit = true;
            }
            else {
                System.out.println("Número maior que 5 carateres! Digite outro número.");
            }
        }
    }
}