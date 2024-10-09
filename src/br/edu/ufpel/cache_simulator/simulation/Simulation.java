package br.edu.ufpel.cache_simulator.simulation;

import java.io.IOException;
import java.util.List;

import br.edu.ufpel.cache_simulator.config.FileHandler;
import br.edu.ufpel.cache_simulator.model.*;

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
        boolean hit = false;
        boolean compulsoryMiss = false;
        boolean conflictMiss = false;
        boolean capacityMiss = false;

        statistics.incrementAccesses();
    
        int offset = cache.getOffset();
        int indexLength = cache.getIndex();

        int tag = address >> (offset + indexLength);
        int index = (address >> offset) & ((1 << indexLength) - 1);
    
        Set set = cache.getSet(index);
    
       // findBlockByTag() só retorna o bloco caso ele esteja válido e a tag seja igual, logo haverá um hit
        Block block = set.findBlockByTag(tag);
        if (block != null) {
            hit = true;
            statistics.incrementsHit();
            set.getReplacementPolicy().update(block);
        } 
        else {
            // Caso de miss - aplicar a política de substituição
            statistics.incrementsMiss();

            Block blockToReplace = set.applyReplacementPolicy(tag);
    
            // Identificar o tipo de miss
            if (!blockToReplace.isValid()) {
                compulsoryMiss = true;  // Miss compulsório: primeira vez que acessa
                statistics.incrementCompulsoryMiss();
            } else if (set.isConflictMiss()) {
                conflictMiss = true;    // Miss de conflito
                statistics.incrementConflictMiss();
            } else {
                capacityMiss = true;    // Miss de capacidade
                statistics.incrementCapacityMiss();
            }
        }
    
        // Debug: Imprimir detalhes do acesso (opcional)
        System.out.println("Access: " + address + ", Tag: " + tag + ", Index: " + index 
            + ", Hit: " + hit + ", Compulsory Miss: " + compulsoryMiss 
            + ", Conflict Miss: " + conflictMiss + ", Capacity Miss: " + capacityMiss);
    }
    
    
    
}
