package br.edu.ufpel.cachesimulator.model;

import br.edu.ufpel.cachesimulator.policies.ReplacementPolicy;

public class Cache {
    private final Set[] sets;

    // Construtores

    public Cache(int nsets, int bsize, int assoc, ReplacementPolicy subPolicy) {
        sets = new Set[nsets];
        for (int i = 0; i < nsets; i++) {
            sets[i] = new Set(assoc, subPolicy);
        }
    }

    // Getters

    public Set getSet(int index) {
        return sets[index];
    }
}