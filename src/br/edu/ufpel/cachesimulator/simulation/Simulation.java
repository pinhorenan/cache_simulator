package br.edu.ufpel.cachesimulator.simulation;

import java.util.List;

import br.edu.ufpel.cachesimulator.model.Block;
import br.edu.ufpel.cachesimulator.model.Cache;
import br.edu.ufpel.cachesimulator.model.Set;

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

    public List<Integer> getAddresses(List<Integer> addresses) {
        return addresses;
    }

    public void accessAddress(int address) {
        int offset = cache.getOffset();
        int indexLength = cache.getIndex();
        
        int tag = address >> (offset + indexLength); 
        int index = (address >> offset) & ((1 << indexLength) - 1);
        
        Set set = cache.getSet(index);
        boolean hit = false;
    
        for (Block block : set.getBlocks()) {
            if (block.isValid() && block.getTag() == tag) {
                hit = true;
                // Atualiza o LRU se necessário
                if (cache.getPolicy().equals("LRU")) {
                    set.applyReplacementPolicy(tag);
                }
                break;
            }
        }
    
        if (!hit) {
            // Caso de miss - aplicar a política de substituição
            set.applyReplacementPolicy(tag);
        }
    
        // Incrementa contadores de hit/miss
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
