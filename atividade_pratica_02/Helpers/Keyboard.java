package Helpers;

import java.util.Scanner;

public class Keyboard {
    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            return "";
        }
    }

    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception MissMatchException) {
            return -1;
        }
    }

    public static double getDouble() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextFloat();
        } catch (Exception MissMatchException) {
            return -1;
        }
    }

    public static float getFloat() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextFloat();
        } catch (Exception MissMatchException) {
            return -1;
        }
    }

    public static boolean getBoolean() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextBoolean();
        } catch (Exception e) {
            return false;
        }
    }

    public static int forceGetInt(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                return scanner.nextInt();
            } catch (Exception MissMatchException) {
                System.err.println("Digite um numero inteiro");
            } finally {
                scanner.nextLine();
            }
        } while (true);
    }

    public static double forceGetDouble(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                double value = scanner.nextDouble();
                return value;
            } catch (Exception MissMatchException) {
                System.err.println("Digite um numero double");
            } finally {
                scanner.nextLine();
            }
        } while (true);
    }

    public static float forceGetFloat(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                return scanner.nextFloat();
            } catch (Exception MissMatchException) {
                System.err.println("Digite um numero float");
            } finally {
                scanner.nextLine();
            }
        } while (true);
    }

    public static boolean forceGetBoolean(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                return scanner.nextBoolean();
            } catch (Exception e) {
                System.err.println("Digite um valor booleano");
            } finally {
                scanner.nextLine();
            }
        } while (true);
    }

    public static String forceGetString(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                return scanner.nextLine();
            } catch (Exception e) {
                System.err.println("Digite um valor string");
            }
        } while (true);
    }
}
