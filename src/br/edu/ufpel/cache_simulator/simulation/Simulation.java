package br.edu.ufpel.cache_simulator.simulation;

import java.util.List;

import br.edu.ufpel.cache_simulator.model.*;
import br.edu.ufpel.cache_simulator.controller.*;

public class Simulation {
    private final Cache cache;
    private final List<Integer> addresses;
    private final Statistics statistics;
    
    public Simulation(Cache cache, List<Integer> addresses) {
        this.cache = cache;
        this.addresses = addresses;
        this.statistics = new Statistics();
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
            CacheController.accessAddress(address);
        }
    }
}
