package br.edu.ufpel.cache_simulator.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    // Lê a configuração de um arquivo .cfg e retorna um mapa de chaves e valores
    public static Map<String, String> readConfigurationFromFile(String configFile) throws IOException {
        Map<String, String> configValues = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue; // Ignora linhas vazias e comentários
                }
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    configValues.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
        return configValues;
    }
}

