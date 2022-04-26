//question 01
package list_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.format("%d  %d  %d  %d\n", i, 10*i, 100*i, 1000*i);
        }
    }
}