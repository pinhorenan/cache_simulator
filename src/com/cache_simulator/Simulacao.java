package com.cache_simulator;

public class Simulacao {
    private final Cache cache;
    private int escritas;
    private int leituras;
    private int hits;
    private int missesCompulsorios;
    private int missesCapacidade;
    private int missesConflito;
    
    // Construtor

    public Simulacao(Cache cache) {
        this.cache = cache;
        this.missesCompulsorios = 0;
        this.missesCapacidade = 0;
        this.missesConflito = 0;
        this.hits = 0;
        this.leituras = 0;
        this.escritas = 0;
    }

    // Métodos

    public void acessarEndereco(int endereco) {
        // Recebe um endereço e verifica se o bloco correspondente está na cache.


        // PRECISO SABER SE É LEITURA OU ESCRITA!


        // Calcular tag e index
        int tag = endereco >> (cache.getNumeroBitsIndex() + cache.getNumeroBitsOffset());
        int index = (endereco >> cache.getNumeroBitsOffset()) & ((1 << cache.getNumeroBitsIndex()) - 1); // REVISAR ESSAS COISAS!!!!!!!!!

        // Verificar se há hit ou miss

        if (cache.getValidade(index) == 0) {
            // Se o bloco não está na cache, é um miss compulsório
            incrementaMissCompulsorio();
            cache.setValido(index);
            cache.setTag(index, tag);
            return;
        } 
        else if (cache.getTag(index) == tag) {
            // Se o bloco está na cache, é um hit
            incrementaHit();
            return;
        } 
        else {
            // Se o bloco não está na cache, é um miss de conflito ou capacidade, preciso dar um jeito de diferenciar!!!
            incrementaMissConflito(); // PROVISORIO
            incrementaMissCapacidade(); // PROVISORIO
            cache.setTag(index, tag);
            return; // Miss
        }
    }

    // Incrementadores

    public void incrementaMissCompulsorio() {
        this.missesCompulsorios++;
    }

    public void incrementaMissCapacidade() {
        this.missesCapacidade++;
    }
    
    public void incrementaMissConflito() {
        this.missesConflito++;
    }

    public void incrementaHit() {
        this.hits++;
    }

    public void incrementaLeitura() {
        this.leituras++;
    }

    public void incrementaEscrita() {
        this.escritas++;
    }

    // Getters

    public int getLeituras() {
        return leituras;
    }

    public int getEscritas() {
        return escritas;
    }

    public int getAcessos() {
        return leituras + escritas;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return missesCompulsorios + missesCapacidade + missesConflito;
    }

    // Getters de Misses por tipo

    public int getMissesCompulsorios() {
        return missesCompulsorios;
    }

    public int getMissesCapacidade() {
        return missesCapacidade;
    }

    public int getMissesConflito() {
        return missesConflito;
    }

    // Getters de Taxas

    public double getTaxaHit() {
        return (double) hits / (leituras + escritas);
    }

    public double getTaxaMiss() {
        return (double) getMisses() / (leituras + escritas);
    }

    public double getTaxaMissCompulsorio() {
        return (double) missesCompulsorios / (leituras + escritas);
    }

    public double getTaxaMissCapacidade() {
        return (double) missesCapacidade / (leituras + escritas);
    }

    public double getTaxaMissConflito() {
        return (double) missesConflito / (leituras + escritas);
    }

    public double getTaxaLeitura() {
        return (double) leituras / (leituras + escritas);
    }

    public double getTaxaEscrita() {
        return (double) escritas / (leituras + escritas);
    }
}
