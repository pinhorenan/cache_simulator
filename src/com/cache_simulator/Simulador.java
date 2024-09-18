package com.cache_simulator;

import java.io.IOException;

public class Simulador {

    private Cache cache;
    private Simulacao simulacao;
    
    public static void main(String[] args) {    
        if (args.length < 6) {
            System.out.println("Uso: cache_simulator <nsets> <bsize> <assoc> <substitutionPolicy> <flag_saida> <inputFile>");
            return;
        }

        // Capturar os parâmetros da linha de comando
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
        simulacao = new Simulacao(cache);

        processarArquivoEntrada(arquivoEntrada);

        gerarArquivoSaida(flagSaida);
    }

    private void processarArquivoEntrada(String arquivoEntrada) {
        // Implementar leitura do arquivo de entrada
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
        // Formato livre para a opção mais verbosa, com informações detalhadas sobre a simulação.

        System.out.println("Total de acessos: " + simulacao.getAcessos());
        System.out.println("Total de leituras: " + simulacao.getLeituras());
        System.out.println("Total de escritas: " + simulacao.getEscritas());
        System.out.println("Total de hits: " + simulacao.getHits());
        System.out.println("Total de misses: " + simulacao.getMisses());
        System.out.println("Total de misses compulsórios: " + simulacao.getMissesCompulsorios());
        System.out.println("Total de misses de capacidade: " + simulacao.getMissesCapacidade());
        System.out.println("Total de misses de conflito: " + simulacao.getMissesConflito());    


        // Taxas
        System.out.println("Porcentagem de acessos que foram leituras: " + simulacao.getTaxaLeitura());
        System.out.println("Porcentagem de acessos que foram escritas: " + simulacao.getTaxaEscrita());
        System.out.println("Taxa de hit: " + simulacao.getTaxaHit());
        System.out.println("Taxa de miss: " + simulacao.getTaxaMiss());
        System.out.println("Taxa de miss compulsório: " + simulacao.getTaxaMissCompulsorio());
        System.out.println("Taxa de miss de capacidade: " + simulacao.getTaxaMissCapacidade());
        System.out.println("Taxa de miss conflito: " + simulacao.getTaxaMissConflito());
    }

    private void saidaSimples() {
        // Formato resumido com ordem fixada pelo professor (Provavelmente vai ser processada por um teste automatizado).
            // A ordem deverá ser: Total de acessos, Taxa de hit, Taxa de miss, Taxa de miss compulsório, Taxa de miss de capacidade, Taxa de miss conflito.
            // Ex.: 100000, 0.95, 0.06, 0.17, 0.33, 0.50

            System.out.println(simulacao.getAcessos() + ", " + simulacao.getTaxaHit() + ", " + simulacao.getTaxaMiss() + ", " + simulacao.getTaxaMissCompulsorio() + ", " + simulacao.getTaxaMissCapacidade() + ", " + simulacao.getTaxaMissConflito());

            // OBSERVAÇÃO: A saída nesse caso está ambigua na questão da vírgula. Os primeiros valores ele botou virgula separando um do outro, depois não tem mais vírgula, pode atrapalhar em testes automaticos, CHECAR COM ELE!
    }
    
}
