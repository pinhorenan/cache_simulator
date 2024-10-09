package br.edu.ufpel.cache_simulator.simulation;

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


    // Métodos para calcular taxas
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

    // Imprime as estatísticas seguindo o padrão especificado quando flag_saída = 1
    public void printStatistics() {
        System.out.println(totalAccesses + getHitRate() + getMissRate() + getCompulsoryMissRate() + getCapacityMissRate() + getConflictMissRate());
    }
}
