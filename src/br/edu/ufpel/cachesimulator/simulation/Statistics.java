package br.edu.ufpel.cachesimulator.simulation;

public class Statistics {
    private int totalAcesses;
    private int totalHits;
    private int compulsoryMisses;
    private int capacityMisses;
    private int conflictMisses;

    public Statistics() {
        this.totalAcesses = 0;
        this.totalHits = 0;
        this.compulsoryMisses = 0;
        this.capacityMisses = 0;
        this.conflictMisses = 0;
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

    public int getCompulsoryMisses() {
        return compulsoryMisses;
    }

    public int getCapacityMisses() {
        return capacityMisses;
    }

    public int getConflitMisses() {
        return conflictMisses;
    }

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
