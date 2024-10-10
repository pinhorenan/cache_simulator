package br.edu.ufpel.cache_simulator.interfaces;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.edu.ufpel.cache_simulator.model.Block;

public class FIFOReplacement implements ReplacementPolicy {
    private Queue<Block> fifoQueue = new LinkedList<>();

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        // Inicializa a fila FIFO se estiver vazia
        if (fifoQueue.isEmpty()) {
            fifoQueue.addAll(blocks);  // Preenche a fila com todos os blocos
        }

        // Remove e retorna o bloco mais antigo (primeiro da fila)
        Block block = fifoQueue.poll();  // Retorna null se a fila estiver vazia
        if (block == null) {
            throw new IllegalStateException("FIFO queue is empty. No block to replace.");
        }
        return block;
    }

    @Override
    public void update(Block block) {
        // Adiciona o bloco ao final da fila FIFO
        fifoQueue.add(block);
    }

    @Override
    public String getName() {
        return "FIFO";
    }

}