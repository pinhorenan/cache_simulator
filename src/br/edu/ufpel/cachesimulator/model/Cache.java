package br.edu.ufpel.cachesimulator.model;
public class Cache {
    private final Set[] sets;

    public Cache(int nsets, int bsize, int assoc, String subPolicy) {
        sets = new Set[nsets];
        for (int i = 0; i < nsets; i++) {
            sets[i] = new Set(assoc, subPolicy);
        }
    }

    public Set getSet(int index) {
        return sets[index];
    }
}