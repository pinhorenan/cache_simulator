package cache_simulator;

import java.io.*;
import java.util.List;
import java.util.Map;

import cache_simulator.controller.InputOutputController;
import cache_simulator.controller.Simulation;
import cache_simulator.logging.Statistics;
import cache_simulator.model.Cache;
import cache_simulator.utils.CacheFactory;

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

        Configuration configuration = new Configuration(nsets, bsize, assoc, subPolicy);
        Cache cache = CacheFactory.createCache(configuration);
        Simulation simulation = new Simulation(cache, addresses);

        // Roda a simulação
        simulation.runSimulation();
        
        Statistics results = simulation.getStatistics();

        if(outputFlag) {
            results.printConciseResults();
        } else {
            results.printVerboseResults();
        }
    }
}

