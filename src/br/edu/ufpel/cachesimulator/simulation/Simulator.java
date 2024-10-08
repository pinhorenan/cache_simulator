package br.edu.ufpel.cachesimulator.simulation;

import java.io.*;
import java.util.*;

import br.edu.ufpel.cachesimulator.config.Configuration;
import br.edu.ufpel.cachesimulator.model.Cache;

public class Simulator {
    private List<Integer> addresses;
     
    public Simulator() {
        addressArray = new ArrayList<>();
    }
    
    public static void main(String[] args) {

        Simulator simulator = new Simulator();

        if (args.length < 6) {
            System.out.println("Uso: cache_simulator <nsets> <bsize> <assoc> <substitutionPolicy> <flag_saida> <inputFile>");
            return;
        }

        int nsets = Integer.parseInt(args[0]);
        int bsize = Integer.parseInt(args[1]);
        int assoc = Integer.parseInt(args[2]);
        String subPolicy = args[3];
        boolean outputFlag = args[4].equals("1");
        String inputFile = args[5];

        Configuration configuration = new Configuration(nsets, bsize, assoc, subPolicy);
        Cache cache = new Cache(configuration);
        Simulation simulation = new Simulation(cache);
        
        try {
            generateOutputFile(simulation.runSimulation(inputFile), outputFlag);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo de entrada: " + e.getMessage());
        }
    }

    private void generateOutputFile(Statistics statistics, boolean outputFlag) {
        // INCOMPLETO: Implementar a geração do arquivo de saída

        if (outputFlag) {
            verboseOutput();
        } else {
            simpleOutput();
        }
    }

    private void verboseOutput() {
        System.out.println("Total de acessos: " + simulacao.getAcessos());
        System.out.println("Total de hits: " + simulacao.getHits());
        System.out.println("Total de misses: " + simulacao.getMisses());
        System.out.println("Total de misses compulsórios: " + simulacao.getMissesCompulsorios());
        System.out.println("Total de misses de capacidade: " + simulacao.getMissesCapacidade());
        System.out.println("Total de misses de conflito: " + simulacao.getMissesConflito());    
        System.out.println("Taxa de hit: " + simulacao.getTaxaHit());
        System.out.println("Taxa de miss: " + simulacao.getTaxaMiss());
        System.out.println("Taxa de miss compulsório: " + simulacao.getTaxaMissCompulsorio());
        System.out.println("Taxa de miss de capacidade: " + simulacao.getTaxaMissCapacidade());
        System.out.println("Taxa de miss conflito: " + simulacao.getTaxaMissConflito());
    }

    private void simpleOutput() {
        // A ordem deverá ser: Total de acessos, Taxa de hit, Taxa de miss, Taxa de miss compulsório, Taxa de miss de capacidade, Taxa de miss conflito.
        // Ex.: 100000, 0.95, 0.06, 0.17, 0.33, 0.50

        System.out.println(simulacao.getAcessos() + ", " + simulacao.getTaxaHit() + ", " + simulacao.getTaxaMiss() + ", " + simulacao.getTaxaMissCompulsorio() + ", " + simulacao.getTaxaMissCapacidade() + ", " + simulacao.getTaxaMissConflito());

        // OBSERVAÇÃO: A saída nesse caso está ambigua na questão da vírgula. Os primeiros valores ele botou virgula separando um do outro, depois não tem mais vírgula, pode atrapalhar em testes automaticos, CHECAR COM ELE!
    }
    
}
