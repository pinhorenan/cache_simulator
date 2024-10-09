package br.edu.ufpel.cache_simulator.utils;

import br.edu.ufpel.cache_simulator.config.Configuration;
import br.edu.ufpel.cache_simulator.model.Cache;
import br.edu.ufpel.cache_simulator.policies.*;

public class CacheFactory {
    public static Cache createCache(Configuration config) {
        ReplacementPolicy policy;
        switch (config.getReplacementPolicy()) {
            case "fifo":
                policy = new FIFOReplacement();
                break;
            case "lru":
                policy = new LRUReplacement();
                break;
            case "r":
                policy = new RandomReplacement();
                break;
            default:
                throw new IllegalArgumentException("Política de substituição inválida");
        }
        return new Cache(config.getNumberSets(), config.getBlockSize(), config.getAssociativity(), policy);
    }
}

