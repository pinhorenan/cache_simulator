package br.edu.ufpel.cachesimulator.config;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufpel.cachesimulator.simulation.Statistics;

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

    public static Configuration readConfigurationFromFile(String configFile) throws IOException{
        Configuration configuration = new Configuration(0, 0, 0, "fifo");
        return configuration;
        // wip
    }

    public static void writeOutputToFile(Statistics statistics, boolean verbose) {
        return;
    }
    
}
