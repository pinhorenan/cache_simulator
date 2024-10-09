package br.edu.ufpel.cachesimulator.model;

import java.util.List;
import java.util.ArrayList;
import br.edu.ufpel.cachesimulator.policies.ReplacementPolicy;
import br.edu.ufpel.cachesimulator.simulation.Statistics;

public class Set {
    private List<Block> blocks;
    private ReplacementPolicy replacementPolicy;
    private Statistics statistics;  // Adiciona uma referência para Statistics

    // Construtor
    public Set(int assoc, ReplacementPolicy policy, Statistics statistics) {
        blocks = new ArrayList<>(assoc);
        for (int i = 0; i < assoc; i++) {
            blocks.add(new Block());  // Inicializa os blocos do conjunto
        }
        this.replacementPolicy = policy;
        this.statistics = statistics;  // Inicializa a referência para Statistics
    }

    // Método principal para acessar um bloco
    public boolean accessBlock(int tag) {
        Block block = findBlockByTag(tag);
        
        if (block != null) {
            // Cache hit
            replacementPolicy.update(block);
            return true;  // Hit
        } else {
            // Cache miss, diferenciar o tipo de miss
            if (isCompulsoryMiss(tag)) {
                statistics.incrementCompulsoryMiss();  // Incrementa miss compulsório
            } else if (isConflictMiss()) {
                statistics.incrementConflictMiss();    // Incrementa miss de conflito
            } else if (isCapacityMiss()) {
                statistics.incrementCapacityMiss();    // Incrementa miss de capacidade
            }
            
            // Substitui o bloco
            Block blockToReplace = replacementPolicy.selectBlockToReplace(blocks);
            blockToReplace.setTag(tag);
            blockToReplace.setValid();
            replacementPolicy.update(blockToReplace);
            return false;  // Miss
        }
    }

    // Método para encontrar o bloco pela tag
    private Block findBlockByTag(int tag) {
        for (Block block : blocks) {
            if (block.isValid() && block.getTag() == tag) {
                return block;
            }
        }
        return null;
    }

    // Verifica se é um miss compulsório
    private boolean isCompulsoryMiss(int tag) {
        for (Block block : blocks) {
            if (!block.isValid()) {
                return true;  // Miss compulsório, o bloco nunca foi carregado
            }
        }
        return false;
    }

    // Verifica se é um miss de conflito
    private boolean isConflictMiss() {
        for (Block block : blocks) {
            if (block.isValid()) {
                return true;  // Miss de conflito, pois há blocos válidos
            }
        }
        return false;
    }

    // Verifica se é um miss de capacidade
    private boolean isCapacityMiss() {
        boolean isFull = true;
        for (Block block : blocks) {
            if (!block.isValid()) {
                isFull = false;  // Não é miss de capacidade se houver espaço vazio
                break;
            }
        }
        return isFull;  // É miss de capacidade se a cache estiver cheia
    }
}
