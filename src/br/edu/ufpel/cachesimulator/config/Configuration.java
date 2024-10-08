package br.edu.ufpel.cachesimulator.config;

public class Configuration {
    private int nsets;
    private int bsize;
    private int assoc;
    private String policy;

    // Métodos de acesso...

    public Configuration(int nsets, int bsize, int assoc, String policy) {
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.policy = policy;
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

    public String getPolicy() {
        return policy;
    }
}

