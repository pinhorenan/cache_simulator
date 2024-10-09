package br.edu.ufpel.cache_simulator.config;

public class Configuration {
    private int nsets;
    private int bsize;
    private int assoc;
    private int verbosity;
    private String policy;

    public Configuration(int nsets, int bsize, int assoc, String policy, int verbosity) {
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.policy = policy;
        this.verbosity = verbosity;
    }

    // Getters

    public int getNumberSets() {
        return nsets;
    }

    public int getBlockSize() {
        return bsize;
    }

    public int getAssociativity() {
        return assoc;
    }

    public int getVerbosity() {
        return verbosity;
    }

    public String getReplacementPolicy() {
        return policy;
    }

}

