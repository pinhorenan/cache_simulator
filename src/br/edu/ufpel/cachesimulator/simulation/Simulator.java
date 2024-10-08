package br.edu.ufpel.cachesimulator.simulation;

import java.io.*;

import br.edu.ufpel.cachesimulator.config.Configuration;
import br.edu.ufpel.cachesimulator.config.FileHandler;
import br.edu.ufpel.cachesimulator.controller.CacheFactory;
import br.edu.ufpel.cachesimulator.model.Cache;

public class Simulator {
    
    public static void main(String[] args) {

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

        // Instanceamento do FileHandler
        FileHandler fileHandler = new FileHandler();

        // Instanceamento de configuration com os argumentos recebidos
        Configuration configuration = new Configuration(nsets, bsize, assoc, subPolicy);

        // Nova instância de Cache utilizando Configuration
        Cache cache = CacheFactory.createCache(configuration);

        // Nova instância de Simulation utilizando a Cache instanciada
        Simulation simulation = new Simulation(cache);
        
        // Tentativa de rodar a simulação.
        try {
            fileHandler.writeOutputToFile(simulation.runSimulation(cache, inputFile), outputFlag);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo de entrada: " + e.getMessage());
        }
    }  
}
