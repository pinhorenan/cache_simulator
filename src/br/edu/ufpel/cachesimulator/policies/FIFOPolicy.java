package br.edu.ufpel.cachesimulator.policies;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.edu.ufpel.cachesimulator.model.Block;

public class FIFOPolicy implements ReplacementPolicy {
    private Queue<Block> fifoQueue = new LinkedList<>();

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        return fifoQueue.poll();  // Remove e retorna o bloco mais antigo
    }

    @Override
    public void update(Block block) {
        fifoQueue.add(block);  // Adiciona o novo bloco ao final da fila
    }
}

