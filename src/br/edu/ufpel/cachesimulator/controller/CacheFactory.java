package br.edu.ufpel.cachesimulator.controller;

import br.edu.ufpel.cachesimulator.config.Configuration;
import br.edu.ufpel.cachesimulator.model.Cache;
import br.edu.ufpel.cachesimulator.policies.*;

public class CacheFactory {
    public static Cache createCache(Configuration config) {
        ReplacementPolicy policy;
        switch (config.getReplacementPolicy()) {
            case "fifo":
                policy = new FIFOPolicy();
                break;
            case "lru":
                policy = new LRUPolicy();
                break;
            case "r":
                policy = new RANDOMPolicy();
            default:
                throw new IllegalArgumentException("Política de substituição inválida");
        }
        return new Cache(config.getNumberSets(), config.getBlockSize(), config.getAssociativity(), policy);
    }
}

