package br.edu.ufpel.cache_simulator.config;

public class Configuration {
    private int nsets;
    private int bsize;
    private int assoc;
    private int verbosity;
    private String policy;


    // Construtor completo
    public Configuration(int nsets, int bsize, int assoc, String policy, int verbosity) {
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.policy = policy;
        this.verbosity = verbosity;
    }
    
    // Construtor sem verbosity
    public Configuration(int nsets, int bsize, int assoc, String policy) {
        this(nsets, bsize, assoc, policy, 0);
    }

    // Construtor default (0, 0, 0, "r", 0)
    public Configuration() {
        this(0, 0, 0, "r", 0);
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

