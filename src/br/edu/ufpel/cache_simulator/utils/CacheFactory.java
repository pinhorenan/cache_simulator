package br.edu.ufpel.cache_simulator.utils;

import br.edu.ufpel.cache_simulator.Configuration;
import br.edu.ufpel.cache_simulator.interfaces.*;
import br.edu.ufpel.cache_simulator.model.Cache;

public class CacheFactory {
    public static Cache createCache(Configuration config) {
        ReplacementPolicy policy;
        switch (config.getReplacementPolicy()) {
            case "F":
                policy = new FIFOReplacement();
                break;
            case "L":
                policy = new LRUReplacement();
                break;
            case "R":
                policy = new RandomReplacement();
                break;
            default:
                throw new IllegalArgumentException("Política de substituição inválida");
        }
        return new Cache(config.getNumberSets(), config.getBlockSize(), config.getAssociativity(), policy);
    }
}

