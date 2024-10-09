package br.edu.ufpel.cache_simulator.model;

import java.util.List;
import java.util.ArrayList;

import br.edu.ufpel.cache_simulator.policies.ReplacementPolicy;

public class Set {
    private List<Block> blocks;
    private ReplacementPolicy replacementPolicy;

    // Construtor
    public Set(int bsize, int assoc, ReplacementPolicy policy) {
        this.replacementPolicy = policy;

        blocks = new ArrayList<>(assoc);
        for (int i = 0; i < assoc; i++) {
            blocks.add(new Block(bsize));
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }
    
    public ReplacementPolicy getReplacementPolicy() {
        return replacementPolicy;
    }

    public boolean accessBlock(int tag) {
        for (Block block : blocks) {
            if (block.isValid() && block.getTag() == tag) {
                return true; // Hit
            }
        }
        return false; // Miss
    }

    public void replaceBlock(int tag) {
        // Aqui você pode implementar a lógica de substituição
        // Por exemplo, substituir o primeiro bloco inválido ou aplicar alguma política (LRU, FIFO, etc.)
        for (Block block : blocks) {
            if (!block.isValid()) {
                block.setTag(tag);
                block.setValid();
                return;  // Substituição concluída
            }
        }

        // Se todos os blocos forem válidos, você pode substituir um bloco com base em sua política
        // Exemplo simples: substitui o primeiro bloco
        blocks.get(0).setTag(tag);  // Substitui o primeiro bloco (você pode melhorar essa lógica)
    }
}
