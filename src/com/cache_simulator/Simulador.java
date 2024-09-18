package com.cache_simulator;

import java.io.*;
import java.util.*;

public class Simulador {
    private Cache cache;
    private Simulacao simulacao;
    private List<Integer> listaEnderecos;
     
    public Simulador() {
        listaEnderecos = new ArrayList<>();
    }
    
    public static void main(String[] args) {    
        if (args.length < 6) {
            System.out.println("Uso: cache_simulator <nsets> <bsize> <assoc> <substitutionPolicy> <flag_saida> <inputFile>");
            return;
        }

        int nsets = Integer.parseInt(args[0]);
        int bsize = Integer.parseInt(args[1]);
        int assoc = Integer.parseInt(args[2]);
        String politicaSubstituicao = args[3];
        boolean flagSaida = args[4].equals("1");
        String arquivoEntrada = args[5];

        Simulador simulador = new Simulador();
        
        try {
            simulador.rodarSimulacao(nsets, bsize, assoc, politicaSubstituicao, flagSaida, arquivoEntrada);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo de entrada: " + e.getMessage());
        }
    }

    private void rodarSimulacao(int nsets, int bsize, int assoc, String politicaSubstituicao, boolean flagSaida, String arquivoEntrada) throws IOException {
        cache = new Cache(nsets, bsize, assoc, politicaSubstituicao);

        if (flagSaida) {
            simulacao = new Simulacao(cache, true);
        } else {
            simulacao = new Simulacao(cache, false);
        }

        processarArquivoEntrada(arquivoEntrada, flagSaida);

        for (int endereco : simulacao.getEnderecos(listaEnderecos)) {
            simulacao.acessarEndereco(endereco);
        }

        gerarArquivoSaida(flagSaida);
    }

    private void processarArquivoEntrada(String arquivoEntrada, boolean debugMode) throws IOException {
        try (DataInputStream dataStream = new DataInputStream(new FileInputStream(arquivoEntrada))) {
            while (dataStream.available() > 0) {
                int endereco = dataStream.readInt();

                //DEBUG
                if (debugMode) {
                    System.out.println("Endereço lido: " + endereco);
                }

                listaEnderecos.add(endereco);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao ler o arquivo de entrada: " + e.getMessage());
        }
    }

    private void gerarArquivoSaida(boolean flagSaida) {
        // INCOMPLETO: Implementar a geração do arquivo de saída

        if (flagSaida) {
            saidaVerbosa();
        } else {
            saidaSimples();
        }
    }

    private void saidaVerbosa() {
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

    private void saidaSimples() {
        // A ordem deverá ser: Total de acessos, Taxa de hit, Taxa de miss, Taxa de miss compulsório, Taxa de miss de capacidade, Taxa de miss conflito.
        // Ex.: 100000, 0.95, 0.06, 0.17, 0.33, 0.50

        System.out.println(simulacao.getAcessos() + ", " + simulacao.getTaxaHit() + ", " + simulacao.getTaxaMiss() + ", " + simulacao.getTaxaMissCompulsorio() + ", " + simulacao.getTaxaMissCapacidade() + ", " + simulacao.getTaxaMissConflito());

        // OBSERVAÇÃO: A saída nesse caso está ambigua na questão da vírgula. Os primeiros valores ele botou virgula separando um do outro, depois não tem mais vírgula, pode atrapalhar em testes automaticos, CHECAR COM ELE!
    }
    
}
