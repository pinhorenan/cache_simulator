package br.edu.ufpel.cache_simulator.controller;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputOutputController {

    // Lê os endereços de memória de um arquivo binário Big Endian
    public static List<Integer> readAddressesFromFile(String inputFile) throws IOException {
        List<Integer> addresses = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(inputFile))) {
            while (in.available() > 0) {
                addresses.add(in.readInt());
            }
        }
        return addresses;
    }


    // Ainda não é utilizado
    public static String[] readConfigurationFromFile(String inputFile) throws IOException {
        String[] configuration = new String[6];
        try (DataInputStream in = new DataInputStream(new FileInputStream(inputFile))) {
            for (int i = 0; i < 6; i++) {
                configuration[i] = in.readUTF();
            }
        }
        return configuration;
    }
}

