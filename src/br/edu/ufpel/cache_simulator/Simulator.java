package br.edu.ufpel.cache_simulator;

import java.io.*;
import java.util.List;
import java.util.Map;

import br.edu.ufpel.cache_simulator.config.Configuration;
import br.edu.ufpel.cache_simulator.controller.InputOutputController;
import br.edu.ufpel.cache_simulator.model.Cache;
import br.edu.ufpel.cache_simulator.simulation.Simulation;
import br.edu.ufpel.cache_simulator.simulation.Statistics;
import br.edu.ufpel.cache_simulator.utils.CacheFactory;

public class Simulator {
    
    public static void main(String[] args) throws IOException {

        int nsets = 0;
        int bsize = 0;
        int assoc = 0;
        String subPolicy = "";
        boolean outputFlag = false;
        String inputFile = "";
        
        // Verifica se o primeiro argumento é um arquivo .cfg
        if (args.length == 2 && args[0].endsWith(".cfg")) {
            // Modo arquivo de configuração
            String configFilePath = args[0];
            Map<String, String> configValues = InputOutputController.readConfigurationFromFile(configFilePath);

            // Leitura das configurações do arquivo .cfg
            nsets = Integer.parseInt(configValues.get("nsets"));
            bsize = Integer.parseInt(configValues.get("bsize"));
            assoc = Integer.parseInt(configValues.get("assoc"));
            subPolicy = configValues.get("substitutionPolicy");
            outputFlag = configValues.get("flag_saida").equals("1");
            inputFile = args[1];

        } else if (args.length >= 6) {
            // Modo parâmetros diretamente
            nsets = Integer.parseInt(args[0]);
            bsize = Integer.parseInt(args[1]);
            assoc = Integer.parseInt(args[2]);
            subPolicy = args[3];
            outputFlag = args[4].equals("1");
            inputFile = args[5];
        } else {
            // Se o número de argumentos for insuficiente
            System.out.println("Uso: cache_simulator <nsets> <bsize> <assoc> <substitutionPolicy> <flag_saida> <inputFile>");
            System.out.println("Ou: cache_simulator <configFile> <inputFile>");
            return;
        }

        // Leitura dos endereços do arquivo
        List<Integer> addresses = InputOutputController.readAddressesFromFile(inputFile);

        Configuration configuration = new Configuration(nsets, bsize, assoc, subPolicy); // Por enquanto, está sempre com verbosidade 0.

        Cache cache = CacheFactory.createCache(configuration);

        Simulation simulation = new Simulation(cache, addresses);

        // Roda a simulação
        simulation.runSimulation();

        Statistics results = simulation.getStatistics();

        if(outputFlag) {
            results.printStatistics();
        } else {
            System.out.println("Cache Simulator");
            System.out.println();
            System.out.println("Configuration:");
            System.out.println("Cache Size: " + cache.getCacheSize());
            System.out.println("Number of Sets: " + cache.getNsets());
            System.out.println("Associativity: " + cache.getAssociativity());
            System.out.println("Block Size: " + cache.getBlockSize());
            System.out.println("Substitution Policy: " + cache.getPolicy().getName());
            System.out.println();
            System.out.println("Results:");
            System.out.println("Total accesses: " + results.getTotalAccesses());
            System.out.println("Hits: " + results.getTotalHits());
            System.out.println("Misses: " + results.getTotalMisses());
            System.out.println("Compulsory Misses: " + results.getCompulsoryMisses());
            System.out.println("Conflict Misses: " + results.getConflictMisses());
            System.out.println("Capacity Misses: " + results.getCapacityMisses());
            System.out.println("Hit Rate: " + results.getHitRate());
            System.out.println("Miss Rate: " + results.getMissRate());
            System.out.println("Compulsory Miss Rate: " + results.getCompulsoryMissRate());
            System.out.println("Conflict Miss Rate: " + results.getConflictMissRate());
            System.out.println("Capacity Miss Rate: " + results.getCapacityMissRate());
            

        }
    }
}
