package br.edu.ufpel.cachesimulator.config;

public class Configuration {
    private int blockSize;
    private int numSets;
    private int associativity;
    private String replacementPolicy;

    // Métodos de acesso...

    public Configuration(int blockSize, int numSets, int associativity, String replacementPolicy) {
        this.blockSize = blockSize;
        this.numSets = numSets;
        this.associativity = associativity;
        this.replacementPolicy = replacementPolicy;
    }

    public int getBlockSize() {
        return blockSize;
    }

    // Outros getters...
}

