package com.cache_simulator;

import java.util.List;

public class Simulacao {
    private final Cache cache;
    private int acessos;
    private int hits;
    private int missesCompulsorios;
    private int missesCapacidade;
    private int missesConflito;
    private boolean debugMode;
    
    // Construtor

    public Simulacao(Cache cache, boolean debugMode) {
        this.cache = cache;
        this.missesCompulsorios = 0;
        this.missesCapacidade = 0;
        this.missesConflito = 0;
        this.hits = 0;
        this.acessos = 0;
        this.debugMode = debugMode;
    }

    // Métodos

    public List<Integer> getEnderecos(List<Integer> enderecos) {
        return enderecos;
    }

    public void acessarEndereco(int endereco) {
        int bitsOffset = cache.getNumeroBitsOffset();
        int bitsIndex = cache.getNumeroBitsIndex();
        int tag = endereco >> (bitsOffset + bitsIndex); 
        int index = (endereco >> bitsOffset) & ((1 << bitsIndex) - 1);

        //DEBUG
        if(debugMode) {
            System.out.println("Endereço: " + endereco + " | Tag: " + tag + " | Índice: " + index);
        }
        
        boolean hit = false;
        boolean missCompulsorio = false;
        boolean missConflito = false;
        @SuppressWarnings("unused")
        boolean missCapacidade = false; // Não implementado ainda.
    
        // Verifica se há algum bloco válido no conjunto
        boolean blocoValido = cache.getValidade(index) == 1;
    
        incrementaAcesso();

        if (!blocoValido) {
            // MISS COMPULSÓRIO: O bloco nunca foi carregado
            missCompulsorio = true;
            incrementaMissCompulsorio();
            cache.setValido(index);
            cache.setTag(index, tag);
        } else if (cache.getTag(index) == tag) {
            // HIT: O bloco está na cache
            hit = true;
            incrementaHit();
        } else {
            // MISS: O bloco está na cache, mas a tag é diferente
            missConflito = true; // Para agora, vamos tratar todos como miss de conflito.
            incrementaMissConflito();
            cache.setTag(index, tag);
        }
    

        // Logs para DEBUG.
        if(debugMode) {
            if (hit) {
                System.out.println("HIT: Endereço " + endereco + " encontrado na cache.");
            } else if (missCompulsorio) {
                System.out.println("MISS COMPULSÓRIO: Endereço " + endereco + " adicionado à cache.");
            } else if (missConflito) {
                System.out.println("MISS DE CONFLITO: Substituindo bloco no conjunto.");
            }
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

    public void incrementaAcesso() {
        this.acessos++;
    }

    // Getters

    public int getAcessos() {
        return acessos;
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
        return (double) hits / (acessos);
    }

    public double getTaxaMiss() {
        return (double) getMisses() / (acessos);
    }

    public double getTaxaMissCompulsorio() {
        return (double) missesCompulsorios / (getMisses());
    }

    public double getTaxaMissCapacidade() {
        return (double) missesCapacidade / (getMisses());
    }

    public double getTaxaMissConflito() {
        return (double) missesConflito / (getMisses());
    }
}
