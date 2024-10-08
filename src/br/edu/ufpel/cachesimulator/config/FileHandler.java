package br.edu.ufpel.cachesimulator.config;

import br.edu.ufpel.cachesimulator.simulation.Simulation;

public class FileHandler {
    public static List<Integer> readAddressesFromFile(String inputFile) throws IOException {
        List<Integer> addresses = new ArrayList<>();
        try (DataInputStream dataStream = new DataInputStream(new FileInputStream(inputFile))) {
            while (dataStream.available() > 0) {
                addresses.add(dataStream.readInt());
            }
        }
        return addresses;
    }

    public static void writeOutputToFile(Simulation simulation, boolean verbose) {
        // Implementar escrita de saída
    }

    
}
