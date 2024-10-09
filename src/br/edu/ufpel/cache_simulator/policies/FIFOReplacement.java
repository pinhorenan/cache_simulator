package br.edu.ufpel.cache_simulator.policies;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.edu.ufpel.cache_simulator.model.Block;

public class FIFOReplacement implements ReplacementPolicy {
    private Queue<Block> fifoQueue = new LinkedList<>();

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        return fifoQueue.poll();
    }

    @Override
    public void update(Block block) {
        fifoQueue.add(block);
    }
}