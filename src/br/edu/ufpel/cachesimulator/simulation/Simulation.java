package br.edu.ufpel.cachesimulator.simulation;

import java.io.IOException;
import java.util.List;

import br.edu.ufpel.cachesimulator.config.FileHandler;
import br.edu.ufpel.cachesimulator.model.Block;
import br.edu.ufpel.cachesimulator.model.Cache;
import br.edu.ufpel.cachesimulator.model.Set;

public class Simulation {
    private final Cache cache;
    private final String inputFile;
    private Statistics statistics;
    
    // Construtor

    public Simulation(Cache cache, String inputFile) {
        this.cache = cache;
        this.inputFile = inputFile;
        statistics = new Statistics();
    }

    public void runSimulation() throws IOException {

        List<Integer> addressesArray = FileHandler.readAddressesFromFile(inputFile);

        for (int address : getAddresses(addressesArray)) {
            accessAddress(address);
        }

        return;
    }

    // Métodos

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
    
        if (set.accessBlock(tag)) {  // Delega o acesso ao Set
            statistics.incrementsHit();
        } else {
            statistics.incrementMiss();  // Você também pode ter um método para incrementar os misses
        }
        if (!hit) {
        // Caso de miss - aplicar a política de substituição
        set.applyReplacementPolicy(tag);
        }

    // Incrementa contadores de hit/miss
    }
}
