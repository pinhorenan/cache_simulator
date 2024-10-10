package cache_simulator.model;

import java.util.List;
import java.util.ArrayList;
import cache_simulator.interfaces.ReplacementPolicy;

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
        // Tenta substituir um bloco inválido primeiro
        for (Block block : blocks) {
            if (!block.isValid()) {
                block.setTag(tag);
                block.setValid();
                replacementPolicy.update(block); // Atualiza a política de substituição
                return; // Substituição concluída
            }
        }

        // Se todos os blocos forem válidos, utiliza a política de substituição
        Block blockToReplace = replacementPolicy.selectBlockToReplace(blocks);
        blockToReplace.setTag(tag);
        blockToReplace.setValid();
        replacementPolicy.update(blockToReplace); // Atualiza a política de substituição
    }

    public void loadNewBlock(int tag) {
        // Encontra o primeiro bloco inválido e o atualiza
        for (Block block : blocks) {
            if (!block.isValid()) {
                block.setTag(tag);
                block.setValid();
                replacementPolicy.update(block); // Atualiza a política de substituição
                return;
            }
        }
    }
}
