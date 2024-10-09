package br.edu.ufpel.cachesimulator.simulation;

import java.io.IOException;
import java.util.List;

import br.edu.ufpel.cachesimulator.config.FileHandler;
import br.edu.ufpel.cachesimulator.model.*;

public class Simulation {
    private final Cache cache;
    private final String inputFile;
    private final Statistics statistics;
    
    public Simulation(Cache cache, String inputFile) {
        this.cache = cache;
        this.inputFile = inputFile;
        this.statistics = new Statistics();
    }

    public void runSimulation() throws IOException {
        List<Integer> addressesArray = FileHandler.readAddressesFromFile(inputFile);

        for (int address : getAddresses(addressesArray)) {
            accessAddress(address);
        }

        statistics.printStatistics();
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public List<Integer> getAddresses(List<Integer> addresses) {
        return addresses;
    }

    public void accessAddress(int address) {
        int offset = cache.getOffset();
        int indexLength = cache.getIndex();
        
        int tag = address >> (offset + indexLength); 
        int index = (address >> offset) & ((1 << indexLength) - 1);
        
        Set set = cache.getSet(index);
        
        if (set.accessBlock(tag)) {
            statistics.incrementsHit();
        }
    }
    
}
