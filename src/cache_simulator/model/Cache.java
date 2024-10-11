package cache_simulator.model;

import cache_simulator.interfaces.ReplacementPolicy;

public class Cache {
    private final int nsets;
    private final int bsize;
    private final int assoc;
    private final Set[] sets;
    private final ReplacementPolicy policy;

    // Construtor
    public Cache(int nsets, int bsize, int assoc, ReplacementPolicy policy) {
        
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.policy = policy;

        sets = new Set[nsets];
        for (int i = 0; i < nsets; i++) {
            sets[i] = new Set(bsize, assoc, policy);
        }

    }

    // Getters
    
    public int getNumerOfSets() {
        return nsets;
    }

    public int getBlockSize() {
        return bsize;
    }

    public int getAssociativity() {
        return assoc;
    }

    public int getCacheSize() {
        int cacheSize = nsets * assoc * bsize;
        return cacheSize;
    }
    
    public int getOffsetBits() {
        int offsetBits = (int) (Math.log(bsize) / Math.log(2));
        return offsetBits;  
    }
    
    public int getIndexBits() {

        int indexBits = (int) (Math.log(nsets) / Math.log(2));
        
        return indexBits;
    }

    public Set getSet(int setIndex) {
        return sets[setIndex];
    }

    public ReplacementPolicy getSubstitutionPolicy() {
        return policy;
    }

}
