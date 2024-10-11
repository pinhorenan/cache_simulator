package cache_simulator;

public class Configuration {
    public final int nsets;
    public final int bsize;
    public final int assoc;
    public final String policy;

    public Configuration(int nsets, int bsize, int assoc, String policy) {
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.policy = policy;
    }
}
