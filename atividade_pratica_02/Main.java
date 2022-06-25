package com.company;

public class Main {

    public static void main(String[] args) {
        Estado estado = new Estado("Maranhão", "MA", 100, 1000,
                1, 1);

        Pais pais = new Pais("Brasil", "BR", 100, 1000,
                1, 1);

        System.out.println(estado.getInformacao());
        System.out.println(pais.getInformacao());
    }
}
