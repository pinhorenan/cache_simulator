package cache_simulator.utils;

import cache_simulator.Configuration;
import cache_simulator.interfaces.*;
import cache_simulator.model.Cache;

public class CacheFactory {
    public static Cache createCache(Configuration config) {
        ReplacementPolicy policy;
        switch (config.policy) {
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
                throw new IllegalArgumentException("Política de substituição inválida. Os parâmetros aceitos são: F, L e R.");
        }
        return new Cache(config.nsets, config.bsize, config.assoc, policy);
    }
}

