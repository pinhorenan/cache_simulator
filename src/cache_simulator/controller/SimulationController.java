package cache_simulator.controller;

import java.util.List;

import cache_simulator.model.*;
import cache_simulator.logging.Statistics;

public class SimulationController {
    private final Cache cache;
    private final CacheController cacheController;
    private final List<Integer> addresses;
    private final Statistics statistics;
    
    public SimulationController(Cache cache, List<Integer> addresses) {
        this.cache = cache;
        this.addresses = addresses;
        this.statistics = new Statistics();
        this.cacheController = new CacheController(cache, statistics);
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Cache getCache() {
        return cache;
    }

    public List<Integer> getAddresses() {
        return addresses;
    }

    public void runSimulation() {
        for (int address : addresses) {
            cacheController.accessAddress(address);
        }
    }
}
