package br.edu.ufpel.cache_simulator.controller;

import br.edu.ufpel.cache_simulator.logging.Statistics;
import br.edu.ufpel.cache_simulator.model.*; 


public class CacheController {
    private final Cache cache;
    private final Statistics statistics;

    public CacheController(Cache cache, Statistics statistics) {
        this.cache = cache;
        this.statistics = statistics;
    }

    public void accessAddress(int address) {
        statistics.incrementAccess();
    
        int offset = cache.getOffsetBits();
        int indexLength = cache.getIndexBits();
    
        // Extrair tag e índice a partir do endereço
        int tag = address >> (offset + indexLength);
        int index = (address >> offset) & ((1 << indexLength) - 1);
    
        // Acessa o conjunto correspondente ao índice
        Set set = cache.getSet(index);

        // Verifica se o bloco com a tag está no conjunto
        boolean hit = set.accessBlock(tag);
    
        // Atualiza as estatísticas de acordo com o resultado do acesso
        updateStatistics(hit, set, tag);
    }

    // Atualiza as estatísticas baseado no resultado do acesso
    private void updateStatistics(boolean hit, Set set, int tag) {
        if (hit) {
            statistics.incrementHit();
        } else {
            // Verifica o tipo de miss e atualiza as estatísticas
            if (isCompulsoryMiss(set, tag)) {
                statistics.incrementCompulsoryMiss();
                set.loadNewBlock(tag); // Chama o método no Set
            } else if (isCapacityMiss(set)) {
                statistics.incrementCapacityMiss();
                set.replaceBlock(tag); // Chama o método no Set
            } else if (isConflictMiss(set, tag)) {
                statistics.incrementConflictMiss();
                set.replaceBlock(tag); // Chama o método no Set
            }
        }
    }
    

    private boolean isCompulsoryMiss(Set set, int tag) {
        // Miss compulsório ocorre quando o bloco é inválido
        for (Block block : set.getBlocks()) {
            if (!block.isValid()) {
                return true;
            }
        }
        return false;
    }

    private boolean isConflictMiss(Set set, int tag) {
        for (Block block : set.getBlocks()) {
            if (block.isValid() && block.getTag() != tag) {
                return true;  // Se há um bloco válido, mas a tag não bate, é um miss de conflito.
            }
        }
        return false;  // Se não encontrou blocos válidos que não correspondem à tag, não é um miss de conflito.
    }
    

    private boolean isCapacityMiss(Set set) {
        // Miss de capacidade ocorre quando a cache está cheia
        for (Block block : set.getBlocks()) {
            if (!block.isValid()) {
                return false;
            }
        }
        return true;
    }
}
