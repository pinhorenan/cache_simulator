package br.edu.ufpel.cache_simulator.logging;

public class Statistics {
    private int totalAccesses;
    private int totalHits;
    private int compulsoryMisses;
    private int conflictMisses;
    private int capacityMisses;

    public Statistics() {
        this.totalHits = 0;
        this.totalAccesses = 0;
        this.compulsoryMisses = 0;
        this.conflictMisses = 0;
        this.capacityMisses = 0;
    }

    public void incrementAccess() {
        totalAccesses++;
    }

    public void incrementHit() {
        totalHits++;
    }

    public void incrementCompulsoryMiss() {
        compulsoryMisses++;
    }

    public void incrementConflictMiss() {
        conflictMisses++;
    }

    public void incrementCapacityMiss() {
        capacityMisses++;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getTotalAccesses() {
        return totalAccesses;
    }   

    public int getTotalMisses() {
        return compulsoryMisses + conflictMisses + capacityMisses;
    }

    public int getCompulsoryMisses() {
        return compulsoryMisses;
    }

    public int getConflictMisses() {
        return conflictMisses;
    }

    public int getCapacityMisses() {
        return capacityMisses;
    }

    public double getHitRate() {
        return (double) totalHits / totalAccesses;
    }

    public double getMissRate() {
        return (double) getTotalMisses() / totalAccesses;
    }

    public double getCompulsoryMissRate() {
        return (double) compulsoryMisses / getTotalMisses();
    }

    public double getConflictMissRate() {
        return (double) conflictMisses / getTotalMisses();
    }

    public double getCapacityMissRate() {;
        return (double) capacityMisses / getTotalMisses();
    }

    // Exibição livre
    public void printVerboseResults() {
        System.out.println("Results:");
        System.out.println("Total accesses: " + getTotalAccesses());
        System.out.println("Hits: " + getTotalHits());
        System.out.println("Misses: " + getTotalMisses());
        System.out.println("Compulsory Misses: " + getCompulsoryMisses());
        System.out.println("Conflict Misses: " + getConflictMisses());
        System.out.println("Capacity Misses: " + getCapacityMisses());
        System.out.println("Hit Rate: " + getHitRate());
        System.out.println("Miss Rate: " + getMissRate());
        System.out.println("Compulsory Miss Rate: " + getCompulsoryMissRate());
        System.out.println("Conflict Miss Rate: " + getConflictMissRate());
        System.out.println("Capacity Miss Rate: " + getCapacityMissRate());    
    }

    // Exibição de acordo com o especificado no enunciado 
    public void printConciseResults() {
        System.out.println(getTotalAccesses() + getHitRate() +  getMissRate() + getCompulsoryMissRate() + getCapacityMissRate() + getConflictMissRate());
    }
}
