package br.edu.ufpel.cachesimulator.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufpel.cachesimulator.policies.ReplacementPolicy;

public class Set {
    private List<Block> blocks;
    private ReplacementPolicy replacementPolicy;

    // Construtor
    public Set(int assoc, ReplacementPolicy policy) {
        blocks = new ArrayList<>(assoc);
        for (int i = 0; i < assoc; i++) {
            blocks.add(new Block());  // Inicializa os blocos do conjunto
        }
        this.replacementPolicy = policy;
    }
    
    // Métodos de acesso
    public void accessBlock(int tag) {
        Block block = findBlockByTag(tag);
        if (block != null) {
            // Cache hit
            replacementPolicy.update(block);
        } else {
            // Cache miss, substitui um bloco
            Block blockToReplace = replacementPolicy.selectBlockToReplace(blocks);
            blockToReplace.setTag(tag);
            blockToReplace.setValid();
            replacementPolicy.update(blockToReplace);
        }
    }

    private Block findBlockByTag(int tag) {
        for (Block block : blocks) {
            if (block.isValid() && block.getTag() == tag) {
                return block;
            }
        }
        return null;
    }
}   