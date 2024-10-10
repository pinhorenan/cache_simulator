package cache_simulator;

public class Configuration {
    private int nsets;
    private int bsize;
    private int assoc;
    private String policy;


    // Construtor completo
    public Configuration(int nsets, int bsize, int assoc, String policy) {
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.policy = policy;
    }

    public Configuration() {
        this.nsets = 0;
        this.bsize = 0;
        this.assoc = 0;
        this.policy = "";
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

    public String getReplacementPolicy() {
        return policy;
    }

}
