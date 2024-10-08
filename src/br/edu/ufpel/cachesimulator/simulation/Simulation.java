package br.edu.ufpel.cachesimulator.simulation;

import java.util.List;

import br.edu.ufpel.cachesimulator.model.Block;
import br.edu.ufpel.cachesimulator.model.Cache;
import br.edu.ufpel.cachesimulator.model.Set;

public class Simulation {
    private final Cache cache;
    
    // Construtor

    public Simulation(Cache cache) {
        this.cache = cache;
    }

    private Statistics runSimulation(Cache cache, boolean outputFlag, String inputFile) throws IOException {
        Statistics statistics = new Statistics();

        readInputFile(inputFile);

        for (int address : getAddresses(adressesArray)) {
            accessAddress(address);
        }

        return statistics;
    }

    // Métodos

    public List<Integer> getAddresses(List<Integer> addresses) {
        return addresses;
    }

    public void accessAddress(int address) {
        int offset = cache.getOffset();
        int indexLength = cache.getIndex();
        
        int tag = address >> (offset + indexLength); 
        int index = (address >> offset) & ((1 << indexLength) - 1);
        
        Set set = cache.getSet(index);
        boolean hit = false;
    
        for (Block block : set.getBlocks()) {
            if (block.isValid() && block.getTag() == tag) {
                hit = true;
                // Atualiza o LRU se necessário
                if (cache.getPolicy().equals("LRU")) {
                    set.applyReplacementPolicy(tag);
                }
                break;
            }
        }
    
        if (!hit) {
            // Caso de miss - aplicar a política de substituição
            set.applyReplacementPolicy(tag);
        }
    
        // Incrementa contadores de hit/miss
    }

}
