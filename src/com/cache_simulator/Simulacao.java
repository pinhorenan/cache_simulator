package com.cache_simulator;

import java.util.List;

public class Simulacao {
    private final Cache cache;
    private int acessos;
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
        this.acessos = 0;
    }

    // Métodos

    public List<Integer> getEnderecos(List<Integer> enderecos) {
        return enderecos;
    }

    public void acessarEndereco(int endereco) {
        // Calcular tag e index
        int tag = endereco >> (cache.getNumeroBitsIndex() + cache.getNumeroBitsOffset());
        int index = (endereco >> cache.getNumeroBitsOffset()) & ((1 << cache.getNumeroBitsIndex()) - 1);
    
        // Verificar se há hit ou miss
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
    
        // LOGS P/ DEBUG APENAS. PODEM SER REMOVIDOS.
        if (hit) {
            System.out.println("HIT: Endereço " + endereco + " encontrado na cache.");
        } else if (missCompulsorio) {
            System.out.println("MISS COMPULSÓRIO: Endereço " + endereco + " adicionado à cache.");
        } else if (missConflito) {
            System.out.println("MISS DE CONFLITO: Substituindo bloco no conjunto.");
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
        return (double) missesCompulsorios / (acessos);
    }

    public double getTaxaMissCapacidade() {
        return (double) missesCapacidade / (acessos);
    }

    public double getTaxaMissConflito() {
        return (double) missesConflito / (acessos);
    }
}
