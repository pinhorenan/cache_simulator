package br.edu.ufpel.cache_simulator.model;

import br.edu.ufpel.cache_simulator.policies.ReplacementPolicy;
import br.edu.ufpel.cache_simulator.simulation.Statistics;

public class Cache {
    private final Set[] sets;
    private final int offset;
    private final int indexLength;
    private final Statistics statistics = new Statistics();  // Instância de Statistics

    // Construtores
    public Cache(int nsets, int bsize, int assoc, ReplacementPolicy subPolicy) {
        sets = new Set[nsets];
        for (int i = 0; i < nsets; i++) {
            sets[i] = new Set(assoc, subPolicy, statistics);  // Passa a instância de Statistics para cada conjunto
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
