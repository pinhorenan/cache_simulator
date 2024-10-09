package br.edu.ufpel.cache_simulator.controller;

import br.edu.ufpel.cache_simulator.model.*; 
import br.edu.ufpel.cache_simulator.simulation.*;


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
            } else if (isConflictMiss(set)) {
                statistics.incrementConflictMiss();
            } else if (isCapacityMiss(set)) {
                statistics.incrementCapacityMiss();
            }
            replaceBlock(set, tag); // Substitui o bloco se for miss
        }
    }

    private boolean isCompulsoryMiss(Set set, int tag) {
        for (Block block : set.getBlocks()) {
            if (!block.isValid()) {
                return true;  // Miss compulsório, o bloco nunca foi carregado
            }
        }
        return false;
    }

    private boolean isConflictMiss(Set set) {
        for (Block block : set.getBlocks()) {
            if (block.isValid()) {
                return true;  // Miss de conflito, pois há blocos válidos
            }
        }
        return false;
    }

    private boolean isCapacityMiss(Set set) {
        for (Block block : set.getBlocks()) {
            if (!block.isValid()) {
                return false;  // Não é miss de capacidade se houver espaço vazio
            }
        }
        return true;  // É miss de capacidade se a cache estiver cheia
    }

    // Substitui um bloco no conjunto de acordo com a política de substituição
    private void replaceBlock(Set set, int tag) {
        Block blockToReplace = set.getReplacementPolicy().selectBlockToReplace(set.getBlocks());
        blockToReplace.setTag(tag);
        blockToReplace.setValid();
        set.getReplacementPolicy().update(blockToReplace); // Atualiza a política de substituição
    }
}
