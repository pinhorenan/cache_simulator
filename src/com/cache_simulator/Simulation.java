package com.cache_simulator;

import java.util.List;

public class Simulation {
    private final Cache cache;
    private int totalAcesses;
    private int totalHits;
    private int compulsoryMisses;
    private int capacityMisses;
    private int conflictMisses;
    private boolean debugMode;
    
    // Construtor

    public Simulation(Cache cache, boolean debugMode) {
        this.cache = cache;
        this.debugMode = debugMode;
        this.compulsoryMisses = 0;
        this.capacityMisses = 0;
        this.conflictMisses = 0;
        this.totalHits = 0;
        this.totalAcesses = 0;
    }

    // Métodos

    public List<Integer> getAdresses(List<Integer> adresses) {
        return addresses;
    }

    public void accessAdress(int address) {
        int offset = cache.getOffset();
        int index = cache.getIndex();
    
        // Calcula Tag e Índice do endereço
        int tag = address >> (offset + index); 
        int index = (address >> offset) & ((1 << index) - 1);
    
        // DEBUG LOG
        if (debugMode) {
            System.out.println("Endereço: " + address + " | Tag: " + tag + " | Índice: " + index);
        }
    
        boolean hit = false;
        boolean compulsoryMiss = false;
        boolean conflictMiss = false;
        boolean capacityMiss = false;
    
        // Incrementa acesso
        incrementsaAccess();
    
        // Verifica se o bloco está na cache
        boolean validBlock = cache.checkValid(index);  // Alterei para retornar boolean ao invés de 1/0
        int tagCache = cache.getTag(index);  // Obtém a tag armazenada no índice da cache
    
        // HIT: Bloco presente na cache
        if (validBlock && tagCache == tag) {
            hit = true;
            incrementsHit();
    
            // Atualiza a política a lista do LRU.
            if (cache.getPolicy().equals("LRU")) {
                cache.refreshLRU(index, tag);
            }
    
        } else {  // MISS: Classifica o tipo de miss
            if (!validBlock) {
                // MISS COMPULSÓRIO: O bloco nunca foi carregado antes
                compulsoryMiss = true;
                incrementsCompulsoryMiss();
            } else if (cache.fullSet(index)) {
                // MISS DE CAPACIDADE: Todos os blocos do conjunto estão preenchidos e o bloco a ser substituído é válido
                capacityMiss = true;
                incrementsCapacityMiss();
            } else {
                // MISS DE CONFLITO: A tag está errada, mas o conjunto tem espaço livre
                conflictMiss = true;
                incrementsConflictMiss();
            }
    
            // Aplica a política de substituição, dependendo da configuração
            if (cache.getPolicy().equals("FIFO")) {
                cache.applyFIFO(index, tag);
            } else if (cache.getPolicy().equals("LRU")) {
                cache.applyLRU(index, tag);
            }
    
            // Atualiza a cache com o novo bloco
            cache.setTag(index, tag);
            cache.setValid(index);  // Marca o bloco como válido
        }
    
        // Logs para DEBUG
        if (debugMode) {
            if (hit) {
                System.out.println("HIT: Endereço " + address + " encontrado na cache.");
            } else if (compulsoryMiss) {
                System.out.println("MISS COMPULSÓRIO: Endereço " + address + "adicionado à cache.");
            } else if (conflictMiss) {
                System.out.println("MISS DE CONFLITO: Substituindo bloco no conjunto.");
            } else if (capacityMiss) {
                System.out.println("MISS DE CAPACIDADE: Substituindo bloco por falta de espaço no conjunto.");
            }
        }
    }
    
    
    // Incrementadores

    public void incrementsCompulsoryMiss() {
        this.compulsoryMisses++;
    }

    public void incrementsCapacityMiss() {
        this.capacityMisses++;
    }
    
    public void incrementsConflictMiss() {
        this.conflictMisses++;
    }

    public void incrementsHit() {
        this.totalHits++;
    }

    public void incrementsAccess() {
        this.totalAcesses++;
    }

    // Getters

    public int getTotalAcesses() {
        return totalAcesses;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getTotalMisses() {
        return (compulsoryMisses + capacityMisses + conflictMisses);
    }

    // Getters de Misses por tipo

    public int getCompulsoryMisses() {
        return compulsoryMisses;
    }

    public int getCapacityMisses() {
        return capacityMisses;
    }

    public int getConflitMisses() {
        return conflictMisses;
    }

    // Getters de Taxas

    public double getHitRate() {
        return (double) totalHits / (totalAcesses);
    }

    public double getMissRate() {
        return (double) getTotalMisses() / (totalAcesses);
    }

    public double getCompulsoryMissRate() {
        return (double) compulsoryMisses / (getTotalMisses());
    }

    public double getCapacityMissRate() {
        return (double) capacityMisses / (getTotalMisses());
    }

    public double getConflictMissRate() {
        return (double) conflictMisses / (getTotalMisses());
    }
}
