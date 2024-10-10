package br.edu.ufpel.cache_simulator.interfaces;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.edu.ufpel.cache_simulator.model.Block;

public class FIFOReplacement implements ReplacementPolicy {
    private Queue<Block> fifoQueue = new LinkedList<>();

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        // Remove e retorna o bloco mais antigo (primeiro da fila)
        Block block = fifoQueue.poll();
        if (block == null) {
            throw new IllegalStateException("FIFO queue is empty. No block to replace.");
        }
        return block;
    }

    @Override
    public void update(Block block) {
        // Apenas adiciona o bloco ao final da fila, sem remover outros blocos
        if (!fifoQueue.contains(block)) {
            fifoQueue.add(block);
        }
    }

    @Override
    public String getName() {
        return "FIFO";
    }
}
