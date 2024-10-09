package br.edu.ufpel.cachesimulator.model;

import br.edu.ufpel.cachesimulator.policies.ReplacementPolicy;

public class Cache {
    private final Set[] sets;
    private final int offset;
    private final int indexLength;

    // Construtores
    public Cache(int nsets, int bsize, int assoc, ReplacementPolicy subPolicy) {
        this.sets = new Set[nsets];
        for (int i = 0; i < nsets; i++) {
            sets[i] = new Set(assoc, subPolicy);
        }

        this.offset = (int) (Math.log(bsize) / Math.log(2));  // Offset baseado no tamanho do bloco
        this.indexLength = (int) (Math.log(nsets) / Math.log(2));  // Index baseado no número de conjuntos
    }

    // Getters
    public Set getSet(int index) {
        return sets[index];
    }

    public int getOffset() {
        return offset;
    }

    public int getIndex() {
        return indexLength;
    }
}
