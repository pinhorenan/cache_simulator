package br.edu.ufpel.cache_simulator;

import java.io.*;
import java.util.List;

import br.edu.ufpel.cache_simulator.config.Configuration;
import br.edu.ufpel.cache_simulator.controller.InputOutputController;
import br.edu.ufpel.cache_simulator.model.Cache;
import br.edu.ufpel.cache_simulator.simulation.Simulation;
import br.edu.ufpel.cache_simulator.simulation.Statistics;
import br.edu.ufpel.cache_simulator.utils.CacheFactory;

public class Simulator {
    
    public static void main(String[] args) throws IOException {

        // Mensagem para "bad args"
        if (args.length < 6) {
            System.out.println("Uso: cache_simulator <nsets> <bsize> <assoc> <substitutionPolicy> <flag_saida> <inputFile>");
            return;
        }
        
        // Captura das configurações da cache
        int nsets = Integer.parseInt(args[0]);
        int bsize = Integer.parseInt(args[1]);
        int assoc = Integer.parseInt(args[2]);
        String subPolicy = args[3];
        boolean outputFlag = args[4].equals("1");
        String inputFile = args[5];

        // Leitura dos endereços do arquivo
        List<Integer> addresses = InputOutputController.readAddressesFromFile(inputFile);

        Configuration configuration = new Configuration(nsets, bsize, assoc, subPolicy); // Por enquanto, está sempre com verbosidade 0.

        Cache cache = CacheFactory.createCache(configuration);

        Simulation simulation = new Simulation(cache, addresses);

        // Roda a simulação
        simulation.runSimulation();

        Statistics results = simulation.getStatistics();

        if(outputFlag) {
            System.out.println("Cache Size: " + cache.getCacheSize());
            System.out.println("Number of Sets: " + nsets);
            System.out.println("Associativity: " + assoc);
            System.out.println("Block Size: " + bsize);
            System.out.println("Substitution Policy: " + subPolicy);
            System.out.println("Total accesses: " + results.getTotalAccesses());
            System.out.println("Hits: " + results.getTotalHits());
            System.out.println("Compulsory Misses: " + results.getCompulsoryMisses());
            System.out.println("Conflict Misses: " + results.getConflictMisses());
            System.out.println("Capacity Misses: " + results.getCapacityMisses());
            System.out.println("Hit Rate: " + results.getHitRate());
            System.out.println("Miss Rate: " + results.getMissRate());
        } else results.printStatistics();
    }
}
