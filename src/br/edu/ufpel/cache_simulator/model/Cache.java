package br.edu.ufpel.cache_simulator.model;

import br.edu.ufpel.cache_simulator.interfaces.ReplacementPolicy;

public class Cache {
    private final int offsetBits;
    private final int indexBits;
    private final Set[] sets;
    private final int cacheSize;
    private final int associativity;
    private final int blockSize;
    private final int nsets;
    private final ReplacementPolicy policy;

    // Construtor
    public Cache(int nsets, int bsize, int assoc, ReplacementPolicy policy) {
        
        this.nsets = nsets;
        this.blockSize = bsize;
        this.associativity = assoc;
        this.policy = policy;

        // Inicializa e preenche o array de conjuntos da cache
        sets = new Set[nsets];
        for (int i = 0; i < nsets; i++) {
            sets[i] = new Set(bsize, assoc, policy);
        }

        // Calcula o número de bits para offset e índice
        offsetBits = (int) (Math.log(bsize) / Math.log(2));
        indexBits = (int) (Math.log(nsets) / Math.log(2));

        // Calcula o tamanho da cache
        cacheSize = nsets * assoc * bsize;
    }

    // Getters

    public int getAssociativity() {
        return associativity;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getNsets() {
        return nsets;
    }

    public ReplacementPolicy getPolicy() {
        return policy;
    }

    public int getCacheSize() {
        return cacheSize;
    }
    
    public Set getSet(int setIndex) {
        return sets[setIndex];
    }

    public int getOffsetBits() {
        return offsetBits;
    }

    public int getIndexBits() {
        return indexBits;
    }

}
