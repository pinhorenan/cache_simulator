package br.edu.ufpel.cachesimulator.model;

import br.edu.ufpel.cachesimulator.config.Configuration;

public class Cache {
    private final Set[] sets;

    public Cache(int nsets, int bsize, int assoc, String subPolicy) {
        sets = new Set[nsets];
        for (int i = 0; i < nsets; i++) {
            sets[i] = new Set(assoc, subPolicy);
        }
    }

    public Cache(Configuration configuration) {
        sets = new Set[configuration.getNumberSets()];
        for (int i = 0; i < configuration.getNumberSets(); i++) {
            sets[i] = new Set(configuration.getAssociativity(), configuration.getPolicy());
        }
    }

    public Set getSet(int index) {
        return sets[index];
    }
}