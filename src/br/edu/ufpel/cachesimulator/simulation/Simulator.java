package br.edu.ufpel.cachesimulator.simulation;

import java.io.*;

import br.edu.ufpel.cachesimulator.config.Configuration;
import br.edu.ufpel.cachesimulator.controller.CacheFactory;
import br.edu.ufpel.cachesimulator.model.Cache;

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

        // Inicializa uma de configuração
        Configuration configuration = new Configuration(nsets, bsize, assoc, subPolicy);

        // Cria uma Cache de acordo com a configuração
        Cache cache = CacheFactory.createCache(configuration);

        // Cria uma Simulação com a Cache
        Simulation simulation = new Simulation(cache, inputFile);

        // Roda a simulação
        simulation.runSimulation();

        Statistics results = simulation.getStatistics();

        if(outputFlag) {
            results.getTotalAcesses();
            results.getTotalHits();
            results.getTotalMisses();

            // WIP
        }

    }
}
