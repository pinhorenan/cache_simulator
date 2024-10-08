package br.edu.ufpel.cachesimulator.controller;

import br.edu.ufpel.cachesimulator.config.Configuration;
import br.edu.ufpel.cachesimulator.model.Cache;
import br.edu.ufpel.cachesimulator.policies.*;

public class CacheFactory {
    public static Cache createCache(Configuration config) {
        ReplacementPolicy policy;
        switch (config.getReplacementPolicy()) {
            case "FIFO":
                policy = new FIFOPolicy();
                break;
            case "LRU":
                policy = new LRUReplacementPolicy();
                break;
            default:
                throw new IllegalArgumentException("Política de substituição inválida");
        }
        return new Cache(config.getNumSets(), config.getBlockSize(), config.getAssociativity(), policy);
    }
}

